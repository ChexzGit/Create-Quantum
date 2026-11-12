package net.chexz.create_quantum.event;

import net.chexz.create_quantum.CreateQuantum;
import net.chexz.create_quantum.networking.ClientPayloadHandler;
import net.chexz.create_quantum.networking.packet.*;
import net.minecraft.client.resources.server.PackLoadFeedback;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = CreateQuantum.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar("1").executesOn(HandlerThread.MAIN);
        registrar.playToServer(MainAbilityKeyC2S.TYPE, MainAbilityKeyC2S.STREAM_CODEC, ClientPayloadHandler::handleTestPacket);
        registrar.playToServer(UtilAbilityKeyC2S.TYPE, UtilAbilityKeyC2S.STREAM_CODEC, ClientPayloadHandler::handleTestPacket);
        registrar.playToServer(PowerAbilityKeyC2S.TYPE, PowerAbilityKeyC2S.STREAM_CODEC, ClientPayloadHandler::handleTestPacket);
        registrar.playToServer(RequestCooldownS2C.TYPE, RequestCooldownS2C.STREAM_CODEC, ClientPayloadHandler::handleTestPacket);
        registrar.playToServer(UpdateCooldownC2S.TYPE, UpdateCooldownC2S.STREAM_CODEC, ClientPayloadHandler::handleTestPacket);
        registrar.playToServer(StrikeTargetwLightningS2C.TYPE, StrikeTargetwLightningS2C.STREAM_CODEC, ClientPayloadHandler::handleTestPacket);
    }
}
