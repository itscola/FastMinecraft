package me.xiaro.fastmc.util

import me.xiaro.fastmc.accessor.AccessorMinecraft
import net.minecraft.client.Minecraft

val Minecraft.partialTicks: Float
    get() = if (this.isGamePaused) (this as AccessorMinecraft).renderPartialTicksPaused
    else this.renderPartialTicks