package me.xiaro.fastmc.tileentity

import me.xiaro.fastmc.TileEntityChest
import me.xiaro.fastmc.shared.renderbuilder.tileentity.info.IChestInfo
import net.minecraft.block.ChestBlock
import net.minecraft.block.entity.TrappedChestBlockEntity

class ChestInfo : HDirectionalTileEntityInfo<TileEntityChest>(ChestBlock.FACING), IChestInfo<TileEntityChest> {
    override val isTrap: Boolean
        get() = entity is TrappedChestBlockEntity

    override val prevLidAngle: Float
        get() = entity.getAnimationProgress(0.0f)

    override val lidAngle: Float
        get() = entity.getAnimationProgress(1.0f)
}