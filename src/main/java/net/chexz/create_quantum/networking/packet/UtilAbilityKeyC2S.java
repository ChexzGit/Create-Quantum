package net.chexz.create_quantum.networking.packet;

import net.chexz.create_quantum.CreateQuantum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public record UtilAbilityKeyC2S(ItemStack heldItem) implements CustomPacketPayload {
    public static final Type<UtilAbilityKeyC2S> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CreateQuantum.MOD_ID,
            "util_ability_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, UtilAbilityKeyC2S> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC,
            UtilAbilityKeyC2S::heldItem,

            UtilAbilityKeyC2S::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
