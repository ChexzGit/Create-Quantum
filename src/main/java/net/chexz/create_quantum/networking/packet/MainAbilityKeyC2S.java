package net.chexz.create_quantum.networking.packet;

import net.chexz.create_quantum.CreateQuantum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.IdentityHashMap;

public record MainAbilityKeyC2S(ItemStack heldItem) implements CustomPacketPayload {
    public static final Type<MainAbilityKeyC2S> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CreateQuantum.MOD_ID,
            "main_ability_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, MainAbilityKeyC2S> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC,
            MainAbilityKeyC2S::heldItem,

            MainAbilityKeyC2S::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
