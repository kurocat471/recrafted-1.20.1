package net.kuro.recrafted.mixin;

import net.kuro.recrafted.Recrafted;
import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.tick.TickPriority;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Degradable.class)
public interface DegradableMixin {
    @Inject(method = "tryDegrade", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Degradable;getDegradationResult(Lnet/minecraft/block/BlockState;)Ljava/util/Optional;", shift = At.Shift.BY, by = 2))
    default void updateNewBlock(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        Recrafted.LOGGER.info("ah");
        world.scheduleBlockTick(pos, world.getBlockState(pos).getBlock(), 2, TickPriority.HIGH);
    }
}
