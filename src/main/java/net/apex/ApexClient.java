package net.lunaire;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class LunaireClient implements ClientModInitializer {
    public static final String CLIENT_NAME = "Lunaire";
    public static boolean zoomActive = false;

    @Override
    public void onInitializeClient() {
        MinecraftClient mc = MinecraftClient.getInstance();

        // Отрисовка HUD
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            if (mc.player == null || mc.options.hudHidden) return;
            
            // Название клиента (Голубой префикс)
            context.drawText(mc.textRenderer, "§bL§funaire", 5, 5, -1, true);
        });

        // Бинды
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            
            // Zoom на клавишу C
            zoomActive = InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_C);
        });
    }
}
