package net.chexz.create_quantum.networking.packet;

import net.chexz.create_quantum.CreateQuantum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public record StrikeTargetwLightningS2C(ItemStack heldItem) implements CustomPacketPayload {
    public static final Type<StrikeTargetwLightningS2C> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CreateQuantum.MOD_ID,
            "lightingstrike_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StrikeTargetwLightningS2C> STREAM_CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC,
            StrikeTargetwLightningS2C::heldItem,

            StrikeTargetwLightningS2C::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
