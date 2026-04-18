package net.apex;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import java.util.*;

public class ApexClient implements ClientModInitializer {
    public static final MinecraftClient mc = MinecraftClient.getInstance();
    public static boolean freeLook = false;
    public static boolean zoom = false;

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            if (mc.player == null || mc.options.hudHidden) return;
            // Отрисовка FPS (№4) и Брони (№6)
            context.drawText(mc.textRenderer, "§bApex §f| " + mc.getCurrentFps() + " FPS", 5, 5, -1, true);
            renderArmor(context);
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            
            // Зум на C (№9)
            zoom = InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_C);
            
            // Меню на Right Shift
            if (InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT)) {
                // Тут должен быть вызов Screen
            }
        });
    }

    private void renderArmor(net.minecraft.client.gui.DrawContext context) {
        int y = 20;
        for (int i = 3; i >= 0; i--) {
            var stack = mc.player.getInventory().getArmorStack(i);
            if (!stack.isEmpty()) {
                context.drawItem(stack, 5, y);
                int dur = stack.getMaxDamage() - stack.getDamage();
                context.drawText(mc.textRenderer, dur + "", 25, y + 4, 0xFFFFFF, true);
                y += 18;
            }
        }
    }
}
