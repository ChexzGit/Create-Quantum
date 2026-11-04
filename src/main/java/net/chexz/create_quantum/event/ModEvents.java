package net.chexz.create_quantum.event;

import net.chexz.create_quantum.CreateQuantum;
import net.chexz.create_quantum.networking.ClientPayloadHandler;
import net.chexz.create_quantum.networking.packet.MainAbilityKeyC2S;
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
    }

}
