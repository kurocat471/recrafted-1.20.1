package net.kuro.recrafted.util;

import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.kuro.recrafted.structure.block.ModBlocks;

public class ModRegistries {
    public static void registerModRegistries() {
        registerOxidizables();
    }

    private static void registerOxidizables() {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_BLOCK, ModBlocks.EXPOSED_COPPER_BLOCK);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_BLOCK, ModBlocks.WEATHERED_COPPER_BLOCK);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_BLOCK, ModBlocks.OXIDIZED_COPPER_BLOCK);

        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.COPPER_BLOCK, ModBlocks.WAXED_COPPER_BLOCK);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_BLOCK, ModBlocks.WAXED_EXPOSED_COPPER_BLOCK);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_BLOCK, ModBlocks.WAXED_WEATHERED_COPPER_BLOCK);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_BLOCK, ModBlocks.WAXED_OXIDIZED_COPPER_BLOCK);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_GRATE, ModBlocks.EXPOSED_COPPER_GRATE);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_GRATE, ModBlocks.WEATHERED_COPPER_GRATE);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_COPPER_GRATE, ModBlocks.OXIDIZED_COPPER_GRATE);

        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.COPPER_GRATE, ModBlocks.WAXED_COPPER_GRATE);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_COPPER_GRATE, ModBlocks.WAXED_EXPOSED_COPPER_GRATE);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_COPPER_GRATE, ModBlocks.WAXED_WEATHERED_COPPER_GRATE);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_COPPER_GRATE, ModBlocks.WAXED_OXIDIZED_COPPER_GRATE);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.CHISELED_COPPER, ModBlocks.EXPOSED_CHISELED_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_CHISELED_COPPER, ModBlocks.WEATHERED_CHISELED_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_CHISELED_COPPER, ModBlocks.OXIDIZED_CHISELED_COPPER);

        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.CHISELED_COPPER, ModBlocks.WAXED_CHISELED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_CHISELED_COPPER, ModBlocks.WAXED_EXPOSED_CHISELED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_CHISELED_COPPER, ModBlocks.WAXED_WEATHERED_CHISELED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_CHISELED_COPPER, ModBlocks.WAXED_OXIDIZED_CHISELED_COPPER);
    }
}