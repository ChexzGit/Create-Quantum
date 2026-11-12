package net.chexz.create_quantum.networking;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import com.mojang.serialization.Codec;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, "createquantum");

    //Cooldowns
    public static final Supplier<AttachmentType<Float>> HEARTBLOW_COOLDOWN = ATTACHMENTS.register(
            "heartblow_cooldown",
            () -> AttachmentType.builder(() -> 0.0f)
                    .serialize(Codec.FLOAT)
                    .copyOnDeath()
                    .build()
    );
    public static final Supplier<AttachmentType<Float>> HEAVYBOLT_COOLDOWN = ATTACHMENTS.register(
            "heavybolt_cooldown",
            () -> AttachmentType.builder(() -> 0.0f)
                    .serialize(Codec.FLOAT)
                    .copyOnDeath()
                    .build()
    );


    //Misc attachments
    public static final Supplier<AttachmentType<Float>> SACREDSHOT_DAMAGE = ATTACHMENTS.register(
            "sacredshot_damage",
            () -> AttachmentType.builder(() -> 0.0f)
                    .serialize(Codec.FLOAT)
                    .copyOnDeath()
                    .build()
    );

    public static void register(IEventBus eventBus){
        ATTACHMENTS.register(eventBus);
    }
}

