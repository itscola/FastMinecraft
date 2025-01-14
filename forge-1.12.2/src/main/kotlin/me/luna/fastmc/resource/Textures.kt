package me.luna.fastmc.resource

import me.luna.fastmc.shared.texture.BedTexture
import me.luna.fastmc.shared.texture.DefaultTexture
import me.luna.fastmc.shared.texture.ITexture
import me.luna.fastmc.shared.texture.TextureUtils
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureUtil
import net.minecraft.item.EnumDyeColor
import net.minecraft.util.ResourceLocation
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ResourceLocationTexture(
    private val mc: Minecraft,
    override val resourceName: String,
    private val resourceLocation: ResourceLocation
) : ITexture {
    override fun bind() {
        mc.renderEngine.bindTexture(resourceLocation)
    }

    override fun destroy() {

    }
}

fun ResourceLocation.readImage(mc: Minecraft): BufferedImage {
    return TextureUtil.readBufferedImage(mc.resourceManager.getResource(this).inputStream)
}