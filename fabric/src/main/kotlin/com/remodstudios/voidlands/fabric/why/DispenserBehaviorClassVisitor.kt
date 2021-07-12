package com.remodstudios.voidlands.fabric.why

import net.fabricmc.loader.api.FabricLoader
import org.objectweb.asm.*


class DispenserBehaviorClassVisitor(cv: ClassVisitor, val registerDefaults: String,
                                    val shulkerBoxBlock: String, val get: String, val hookDesc: String)
    : ClassVisitor(Opcodes.ASM9, cv) {
    companion object {
        const val DISPENSER_BEHAVIOR = "net.minecraft.class_2357"
        const val REGISTER_DEFAULTS = "method_18346"

        // net/minecraft/block/ShulkerBoxBlock.get (Lnet/minecraft/util/DyeColor;)Lnet/minecraft/block/Block;
        const val DYE_COLOR = "net.minecraft.class_1767"
        const val SHULKER_BOX_BLOCK = "net.minecraft.class_2480"
        const val GET = "method_10525"
        const val GET_DESC = "(Lnet.minecraft.class_2248;)Lnet.minecraft.class_2480;" // Block get(DyeColor)

        @JvmStatic
        fun createTransformer(): ClassTransformer {
            val loader = FabricLoader.getInstance();
            val mappings = loader.mappingResolver;

            val dispenserBehavior = mappings.mapClassName("intermediary", DISPENSER_BEHAVIOR)
            val registerDefaults = mappings.mapMethodName("intermediary", DISPENSER_BEHAVIOR, REGISTER_DEFAULTS, "()V")
            val shulkerBoxBlock = mappings.mapClassName("intermediary", SHULKER_BOX_BLOCK)
            val get = mappings.mapMethodName("intermediary", SHULKER_BOX_BLOCK, GET, GET_DESC)
            val hookDesc = "(L${mappings.mapClassName("intermediary", DYE_COLOR)};)Z"

            return ClassTransformer { name, transformedName, bytes ->
                if (name == dispenserBehavior) {
                    val reader = ClassReader(bytes)
                    val writer = ClassWriter(0)
                    val visitor = DispenserBehaviorClassVisitor(writer, registerDefaults, shulkerBoxBlock, get, hookDesc)
                    reader.accept(visitor, 0)
                    return@ClassTransformer writer.toByteArray()
                }
                bytes
            }
        }
    }

    override fun visitMethod(
        access: Int,
        name: String,
        descriptor: String,
        signature: String,
        exceptions: Array<out String>
    ): MethodVisitor {
        if (name == registerDefaults && descriptor == "()V") {
            return object : MethodVisitor(api, super.visitMethod(access, name, descriptor, signature, exceptions)) {
                var currentLabel: Label? = null
                var lastLabel: Label? = null

                override fun visitLabel(label: Label) {
                    lastLabel = currentLabel
                    currentLabel = label
                }

                override fun visitMethodInsn(
                    opcode: Int,
                    owner: String,
                    name: String,
                    descriptor: String,
                    isInterface: Boolean
                ) {
                    if (owner == shulkerBoxBlock && name == get) {
                        val continueLabel = Label()
                        super.visitMethodInsn(
                            Opcodes.INVOKESTATIC,
                            "com/remodstudios/voidlands/fabric/HatefulHooks",
                            "notMyDyeColor",
                            hookDesc,
                            false
                        )
                        super.visitJumpInsn(Opcodes.IFEQ, continueLabel)
                        super.visitIincInsn(6, 1)
                        // unless some other shmuck already modified this method,
                        //  the label _before_ the current label should be the start of the "for" loop
                        //  I would've liked to simply jump to the actual "advance" statement, but I have no idea
                        //  how to get the label for that, so this janky workaround will do instead
                        //  -ADCLeo
                        super.visitJumpInsn(Opcodes.GOTO, lastLabel!!)
                        super.visitLabel(continueLabel)
                    }
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                }
            }
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }
}