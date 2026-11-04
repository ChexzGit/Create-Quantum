package net.chexz.create_quantum.networking.packet;

import net.chexz.create_quantum.CreateQuantum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public record PowerAbilityKeyC2S(ItemStack heldItem) implements CustomPacketPayload {
    public static final Type<PowerAbilityKeyC2S> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CreateQuantum.MOD_ID,
            "power_ability_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PowerAbilityKeyC2S> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC,
            PowerAbilityKeyC2S::heldItem,

            PowerAbilityKeyC2S::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
