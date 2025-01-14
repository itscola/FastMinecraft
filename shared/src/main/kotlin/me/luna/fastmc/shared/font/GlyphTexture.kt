package me.luna.fastmc.shared.font

import me.luna.fastmc.shared.texture.ITexture
import me.luna.fastmc.shared.opengl.glBindTexture
import me.luna.fastmc.shared.opengl.glDeleteTextures

class GlyphTexture(
    val id: Int,
    val internalID: Int
) : ITexture {
    override val resourceName = "fontRenderer/$internalID"

    override fun bind() {
        glBindTexture(id)
    }

    override fun destroy() {
        glDeleteTextures(id)
    }
}