package net.kuro.recrafted;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.MaterialFinder;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.kuro.recrafted.structure.block.ModBlockColors;
import net.kuro.recrafted.structure.block.ModBlocks;
import net.kuro.recrafted.structure.block.connectedtextures.api.model.DefaultModelTypes;
import net.kuro.recrafted.structure.block.connectedtextures.api.model.RecraftedModelTypeRegistry;
import net.kuro.recrafted.structure.block.connectedtextures.api.predicate.RecraftedPredicateRegistry;
import net.kuro.recrafted.structure.block.connectedtextures.api.texture.DefaultTextureTypes;
import net.kuro.recrafted.structure.block.connectedtextures.api.texture.RecraftedTextureTypeRegistry;
import net.kuro.recrafted.structure.block.connectedtextures.api.texture.data.ConnectingTextureData;
import net.kuro.recrafted.structure.block.connectedtextures.model.ModelTypeRegistryImpl;
import net.kuro.recrafted.structure.block.connectedtextures.predicate.PredicateRegistryImpl;
import net.kuro.recrafted.structure.block.connectedtextures.texture.RecraftedTextureMetadataSection;
import net.kuro.recrafted.structure.block.connectedtextures.texture.TextureTypeRegistryImpl;
import net.kuro.recrafted.structure.block.entity.ModBlockEntities;
import net.kuro.recrafted.structure.block.entity.renderer.AnvilBlockEntityRenderer;
import net.kuro.recrafted.structure.block.entity.renderer.PotionCauldronBlockEntityRenderer;
import net.kuro.recrafted.structure.block.entity.renderer.WaterBarrelBlockEntityRenderer;
import net.kuro.recrafted.structure.block.entity.renderer.WaterCauldronBlockEntityRenderer;
import net.kuro.recrafted.networking.ClientNetworking;
import net.kuro.recrafted.networking.ModMessages;
import net.kuro.recrafted.structure.particle.ModParticleTypes;
import net.kuro.recrafted.structure.screen.AnvilScreen;
import net.kuro.recrafted.structure.screen.ModScreenHandlers;
import net.kuro.recrafted.util.BlockRenderLayerRegistries;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.texture.SpriteLoader;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import net.kuro.recrafted.structure.block.connectedtextures.predicate.*;

public class RecraftedClient implements ClientModInitializer {

    private static final RenderMaterial[] RENDER_MATERIALS = new RenderMaterial[ConnectingTextureData.RenderType.values().length];

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ANVIL_SCREEN_HANDLER, AnvilScreen::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ANVIL_BE, AnvilBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.POTION_CAULDRON_BE, PotionCauldronBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.WATER_CAULDRON_BE, WaterCauldronBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.WATER_BARREL_BE, WaterBarrelBlockEntityRenderer::new);
        BlockRenderLayerRegistries.registerBlockRenderLayers();
        ModMessages.registerS2CPackets();
        ClientNetworking.registerEvents();
        ModParticleTypes.registerParticleTypesClientSide();
        ModBlockColors.registerBlockColors();


        // Register default texture types
        RecraftedTextureTypeRegistry.registerTextureType(new Identifier("mc-recrafted", "vanilla"), DefaultTextureTypes.VANILLA);
        RecraftedTextureTypeRegistry.registerTextureType(new Identifier("mc-recrafted", "connecting"), DefaultTextureTypes.CONNECTING);
        RecraftedTextureTypeRegistry.registerTextureType(new Identifier("mc-recrafted", "scrolling"), DefaultTextureTypes.SCROLLING);
        // Register default model types
        RecraftedModelTypeRegistry.registerModelType(new Identifier("mc-recrafted", "unknown"), DefaultModelTypes.UNKNOWN);
        RecraftedModelTypeRegistry.registerModelType(new Identifier("mc-recrafted", "vanilla"), DefaultModelTypes.VANILLA);
        RecraftedModelTypeRegistry.registerModelType(new Identifier("mc-recrafted", "connecting"), DefaultModelTypes.CONNECTING);
        // Register default connection predicates
        RecraftedPredicateRegistry.registerConnectionPredicate(new Identifier("mc-recrafted", "and"), AndConnectionPredicate.SERIALIZER);
        RecraftedPredicateRegistry.registerConnectionPredicate(new Identifier("mc-recrafted", "or"), OrConnectionPredicate.SERIALIZER);
        RecraftedPredicateRegistry.registerConnectionPredicate(new Identifier("mc-recrafted", "not"), NotConnectionPredicate.SERIALIZER);
        RecraftedPredicateRegistry.registerConnectionPredicate(new Identifier("mc-recrafted", "is_same_block"), IsSameBlockConnectionPredicate.SERIALIZER);
        RecraftedPredicateRegistry.registerConnectionPredicate(new Identifier("mc-recrafted", "is_same_state"), IsSameStateConnectionPredicate.SERIALIZER);
        RecraftedPredicateRegistry.registerConnectionPredicate(new Identifier("mc-recrafted", "match_block"), MatchBlockConnectionPredicate.SERIALIZER);

        // Add mod's metadata section
        SpriteLoader.METADATA_READERS = ImmutableSet.<ResourceMetadataReader<?>>builder()
                .addAll(SpriteLoader.METADATA_READERS)
                .add(RecraftedTextureMetadataSection.INSTANCE)
                .build();

        // Finalize registration
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> TextureTypeRegistryImpl.finalizeRegistration());
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> ModelTypeRegistryImpl.finalizeRegistration());
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> PredicateRegistryImpl.finalizeRegistration());
    }

    public static RenderMaterial getRenderTypeMaterial(ConnectingTextureData.RenderType renderType){
        RenderMaterial material = RENDER_MATERIALS[renderType.ordinal()];
        if(material == null){
            MaterialFinder materialFinder = RendererAccess.INSTANCE.getRenderer().materialFinder();
            for(ConnectingTextureData.RenderType value : ConnectingTextureData.RenderType.values()){
                BlendMode mode = value == ConnectingTextureData.RenderType.OPAQUE ? BlendMode.SOLID
                        : value == ConnectingTextureData.RenderType.CUTOUT ? BlendMode.CUTOUT
                        : value == ConnectingTextureData.RenderType.TRANSLUCENT ? BlendMode.TRANSLUCENT : null;
                RENDER_MATERIALS[value.ordinal()] = materialFinder.blendMode(mode).find();
            }
            material = RENDER_MATERIALS[renderType.ordinal()];
        }
        return material;
    }
}
