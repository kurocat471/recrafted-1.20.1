package net.kuro.recrafted.structure.item.custom;

import net.kuro.recrafted.Recrafted;
import net.kuro.recrafted.sound.ModSoundEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.OxidizableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.tick.TickPriority;

import java.util.Optional;

public class PatinaItem extends Item {

    public PatinaItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() == null) return super.useOnBlock(context);
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(context.getBlockPos());
        Block block = state.getBlock();
        ItemStack stack = context.getStack();
        Hand hand = context.getHand();
        BlockPos pos = context.getBlockPos();
        Optional<BlockState> nextOxidizedState = Oxidizable.getIncreasedOxidationBlock(block).map(bloc -> bloc.getStateWithProperties(state));

        if (block instanceof Oxidizable && nextOxidizedState.isPresent()) {

            world.playSound(player, pos, ModSoundEvents.PATINA_CRUMBLES, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, pos, 0);

            if (player instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, pos, stack);
            }
            world.setBlockState(pos, nextOxidizedState.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, nextOxidizedState.get()));
            if (player != null) {
                stack.decrement(1);
            }
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }


}
