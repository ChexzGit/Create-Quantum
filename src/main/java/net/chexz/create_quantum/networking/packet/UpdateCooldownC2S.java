package net.chexz.create_quantum.networking.packet;

import net.chexz.create_quantum.CreateQuantum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public record UpdateCooldownC2S() implements CustomPacketPayload {
    public static final Type<UpdateCooldownC2S> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CreateQuantum.MOD_ID,
            "update_ability_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, UpdateCooldownC2S> STREAM_CODEC = StreamCodec.unit(
            new UpdateCooldownC2S()
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
