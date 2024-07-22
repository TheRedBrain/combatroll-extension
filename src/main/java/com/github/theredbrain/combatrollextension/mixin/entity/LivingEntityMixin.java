package com.github.theredbrain.combatrollextension.mixin.entity;

import com.github.theredbrain.combatrollextension.CombatRollExtension;
import com.github.theredbrain.combatrollextension.entity.DuckLivingEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements DuckLivingEntityMixin {
	@Shadow public abstract double getAttributeValue(EntityAttribute attribute);

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "createLivingAttributes", at = @At("RETURN"))
	private static void bettercombatextension$createLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
		cir.getReturnValue()
				.add(CombatRollExtension.ROLL_INVULNERABLE_TICKS)
				.add(CombatRollExtension.ROLL_STAMINA_COST)
		;
	}

	@Override
	public float combatrollextension$getRollInvulnerableTicks() {
		return (float) this.getAttributeValue(CombatRollExtension.ROLL_INVULNERABLE_TICKS);
	}

	@Override
	public float combatrollextension$getRollStaminaCost() {
		return (float) this.getAttributeValue(CombatRollExtension.ROLL_STAMINA_COST);
	}

}
