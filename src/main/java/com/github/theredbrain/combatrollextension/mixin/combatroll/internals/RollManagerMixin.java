package com.github.theredbrain.combatrollextension.mixin.combatroll.internals;

import com.github.theredbrain.combatrollextension.CombatRollExtension;
import com.github.theredbrain.combatrollextension.entity.DuckLivingEntityMixin;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.combat_roll.internals.RollManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RollManager.class)
public abstract class RollManagerMixin {

	@ModifyReturnValue(
			method = "isRollAvailable(Lnet/minecraft/entity/player/PlayerEntity;)Z",
			at = @At("RETURN")
	)
	public boolean combatrollextension$isRollAvailable(boolean original, PlayerEntity playerEntity) {
		return original && (!CombatRollExtension.SERVER_CONFIG.rolling_requires_stamina || playerEntity.isCreative() || CombatRollExtension.getCurrentStamina(playerEntity) >= ((DuckLivingEntityMixin) playerEntity).combatrollextension$getActualRollStaminaCost() || (!CombatRollExtension.SERVER_CONFIG.rolling_requires_stamina_cost && CombatRollExtension.getCurrentStamina(playerEntity) > 0));
	}
}
