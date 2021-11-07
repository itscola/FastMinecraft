package me.xiaro.fastmc.resource

import me.xiaro.fastmc.model.Model
import me.xiaro.fastmc.model.tileentity.ModelBed
import me.xiaro.fastmc.model.tileentity.ModelChest
import me.xiaro.fastmc.model.tileentity.ModelLargeChest
import me.xiaro.fastmc.model.tileentity.ModelShulkerBox
import me.xiaro.fastmc.opengl.ITexture
import me.xiaro.fastmc.tileentity.TileEntityRenderBuilder
import net.minecraft.client.Minecraft
import net.minecraft.util.ResourceLocation

class ResourceManager(mc: Minecraft) : IResourceManager {
    override val model: ResourceProvider<Model> = ResourceProvider(
        ModelBed().apply { init() },
        ModelChest().apply { init() },
        ModelLargeChest().apply { init() },
        ModelShulkerBox().apply { init() },
    )

    override val tileEntityShader: ResourceProvider<TileEntityRenderBuilder.Shader> = ResourceProvider(
        TileEntityRenderBuilder.Shader(
            "tileEntity/EnderChest",
            "/assets/shaders/tileentity/EnderChest.vsh",
            "/assets/shaders/tileentity/Default.fsh"
        ),
        TileEntityRenderBuilder.Shader(
            "tileEntity/Bed",
            "/assets/shaders/tileentity/Bed.vsh",
            "/assets/shaders/tileentity/Default.fsh"
        ),
        TileEntityRenderBuilder.Shader(
            "tileEntity/ShulkerBox",
            "/assets/shaders/tileentity/ShulkerBox.vsh",
            "/assets/shaders/tileentity/Default.fsh"
        ),
        TileEntityRenderBuilder.Shader(
            "tileEntity/Chest",
            "/assets/shaders/tileentity/Chest.vsh",
            "/assets/shaders/tileentity/Default.fsh"
        )
    )

    override val texture: ResourceProvider<ITexture> = ResourceProvider(
        ResourceLocationTexture(mc, "tileEntity/EnderChest", ResourceLocation("textures/entity/chest/ender.png")),
        bedTexture(mc),
        shulkerTexture(mc),
        smallChestTexture(mc),
        largeChestTexture(mc)
    )
}