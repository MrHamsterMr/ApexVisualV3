package net.apex.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    // В 1.21.4 здесь меняется логика рендера оверлея урона
    @Inject(method = "getJumpVelocity", at = @At("HEAD"))
    private void onHit(CallbackInfoReturnable<Float> cir) {
        // Логика изменения цвета при ударе
    }
}
