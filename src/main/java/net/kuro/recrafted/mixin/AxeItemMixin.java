package net.kuro.recrafted.mixin;

import net.kuro.recrafted.structure.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.tick.TickPriority;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;syncWorldEvent(Lnet/minecraft/entity/player/PlayerEntity;ILnet/minecraft/util/math/BlockPos;I)V", ordinal = 0))
    public void dropPatina(ItemUsageContext context, CallbackInfoReturnable<ItemStack> cir) {
        World world = context.getWorld();
        BlockPos pos = BlockPos.ofFloored(context.getHitPos());
        double randomProbability = world.random.nextDouble();
        int resultCount;
        if (randomProbability < 0.75) {
            resultCount = 1;
        } else {
            resultCount = 0;
        }
        int j = world.random.nextInt(2);
        Block.dropStack(world, pos, new ItemStack(ModItems.PATINA, resultCount));
    }
}