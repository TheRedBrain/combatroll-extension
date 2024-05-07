package com.github.theredbrain.combatrollextension.mixin.combatroll.client;

import com.github.theredbrain.combatrollextension.CombatRollExtension;
import com.github.theredbrain.staminaattributes.entity.StaminaUsingEntity;
import net.combatroll.client.RollManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RollManager.class)
public abstract class RollManagerMixin {

    @Inject(method = "isRollAvailable(Lnet/minecraft/entity/player/PlayerEntity;)Z", at = @At("RETURN"), cancellable = true)
    public void combatrollextension$isRollAvailable(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() && (!CombatRollExtension.serverConfig.rolling_requires_stamina || ((StaminaUsingEntity)player).staminaattributes$getStamina() > 0 || player.isCreative()));
    }
}
