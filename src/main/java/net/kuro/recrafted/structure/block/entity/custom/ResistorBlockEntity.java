package net.kuro.recrafted.structure.block.entity.custom;

import net.kuro.recrafted.structure.block.entity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class ResistorBlockEntity
        extends BlockEntity {
    private int outputSignal;

    public ResistorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESISTOR_BE, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("OutputSignal", this.outputSignal);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.outputSignal = nbt.getInt("OutputSignal");
    }

    public int getOutputSignal() {
        return this.outputSignal;
    }

    public void setOutputSignal(int outputSignal) {
        this.outputSignal = outputSignal;
    }
}

