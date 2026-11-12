package net.chexz.create_quantum.networking.packet;

import net.chexz.create_quantum.CreateQuantum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public record RequestCooldownS2C(ItemStack heldItem) implements CustomPacketPayload {
    public static final Type<RequestCooldownS2C> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CreateQuantum.MOD_ID,
            "request_ability_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, RequestCooldownS2C> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC,
            RequestCooldownS2C::heldItem,

            RequestCooldownS2C::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
