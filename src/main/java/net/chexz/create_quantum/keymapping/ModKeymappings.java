package net.chexz.create_quantum.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.common.util.Lazy;
import org.checkerframework.checker.units.qual.K;
import org.lwjgl.glfw.GLFW;

public class ModKeymappings {
    private static final KeyMapping KEY_MAPPING_ABILITYMAIN = new KeyMapping("key.create_quantum.main",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_1,
            KeyMapping.CATEGORY_GAMEPLAY);

    public static final Lazy<KeyMapping> PRESS_ABILITYMAIN = Lazy.of(() -> KEY_MAPPING_ABILITYMAIN);

    public static void register(){

    }
}
