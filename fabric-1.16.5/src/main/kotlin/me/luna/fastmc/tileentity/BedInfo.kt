package me.luna.fastmc.tileentity

import me.luna.fastmc.TileEntityBed
import me.luna.fastmc.shared.renderbuilder.tileentity.info.IBedInfo
import net.minecraft.block.BedBlock
import net.minecraft.block.enums.BedPart

class BedInfo : HDirectionalTileEntityInfo<TileEntityBed>(BedBlock.FACING), IBedInfo<TileEntityBed> {
    override val color: Int
        get() = entity.color.id

    override val isHead: Boolean
        get() = blockState?.get(BedBlock.PART) != BedPart.FOOT
}