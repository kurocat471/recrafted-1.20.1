package net.kuro.recrafted.structure.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kuro.recrafted.Recrafted;
import net.kuro.recrafted.structure.block.ModBlocks;
import net.kuro.recrafted.structure.block.entity.custom.AnvilBlockEntity;
import net.kuro.recrafted.structure.block.entity.custom.PotionCauldronBlockEntity;
import net.kuro.recrafted.structure.block.entity.custom.WaterBarrelBlockEntity;
import net.kuro.recrafted.structure.block.entity.custom.WaterCauldronBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<WaterCauldronBlockEntity> RESISTOR_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Recrafted.MOD_ID, "resistor_block_entity"),
                    FabricBlockEntityTypeBuilder.create(WaterCauldronBlockEntity::new,
                            ModBlocks.RESISTOR,
                            ModBlocks.EXPOSED_RESISTOR,
                            ModBlocks.WEATHERED_RESISTOR,
                            ModBlocks.OXIDIZED_RESISTOR,
                            ModBlocks.WAXED_RESISTOR,
                            ModBlocks.WAXED_EXPOSED_RESISTOR,
                            ModBlocks.WAXED_WEATHERED_RESISTOR,
                            ModBlocks.WAXED_OXIDIZED_RESISTOR
                    ).build(null));

    public static final BlockEntityType<AnvilBlockEntity> ANVIL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Recrafted.MOD_ID, "anvil_block_entity"),
                    FabricBlockEntityTypeBuilder.create(AnvilBlockEntity::new,
                            ModBlocks.COPPER_ANVIL,
                            ModBlocks.EXPOSED_COPPER_ANVIL,
                            ModBlocks.WEATHERED_COPPER_ANVIL,
                            ModBlocks.OXIDIZED_COPPER_ANVIL,
                            ModBlocks.WAXED_COPPER_ANVIL,
                            ModBlocks.WAXED_EXPOSED_COPPER_ANVIL,
                            ModBlocks.WAXED_WEATHERED_COPPER_ANVIL,
                            ModBlocks.WAXED_OXIDIZED_COPPER_ANVIL,
                            ModBlocks.BRONZE_ANVIL,
                            ModBlocks.BISMUTH_BRONZE_ANVIL,
                            ModBlocks.BLACK_BRONZE_ANVIL,
                            ModBlocks.WHITE_BRONZE_ANVIL,
                            ModBlocks.IRON_ANVIL,
                            ModBlocks.STEEL_ANVIL
                    ).build(null));

    public static final BlockEntityType<PotionCauldronBlockEntity> POTION_CAULDRON_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Recrafted.MOD_ID, "potion_cauldron_block_entity"),
                    FabricBlockEntityTypeBuilder.create(PotionCauldronBlockEntity::new,
                            ModBlocks.POTION_CAULDRON).build(null));

    public static final BlockEntityType<WaterCauldronBlockEntity> WATER_CAULDRON_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Recrafted.MOD_ID, "water_cauldron_block_entity"),
                    FabricBlockEntityTypeBuilder.create(WaterCauldronBlockEntity::new,
                            Blocks.WATER_CAULDRON).build(null));

    public static final BlockEntityType<WaterBarrelBlockEntity> WATER_BARREL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Recrafted.MOD_ID, "water_barrel_block_entity"),
                    FabricBlockEntityTypeBuilder.create(WaterBarrelBlockEntity::new,
                            ModBlocks.SPRUCE_WATER_BARREL,
                            ModBlocks.BIRCH_WATER_BARREL
                    ).build(null));

    public static void registerBlockEntities() {
        Recrafted.LOGGER.info("Registering Block Entities for " + Recrafted.MOD_ID);
    }
}
