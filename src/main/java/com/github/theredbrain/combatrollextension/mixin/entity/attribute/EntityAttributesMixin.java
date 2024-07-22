package com.github.theredbrain.combatrollextension.mixin.entity.attribute;

import com.github.theredbrain.combatrollextension.CombatRollExtension;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityAttributes.class)
public class EntityAttributesMixin {
	@Shadow
	private static EntityAttribute register(String id, EntityAttribute attribute) {
		throw new AssertionError();
	}

	static {
		CombatRollExtension.ROLL_INVULNERABLE_TICKS = register(CombatRollExtension.MOD_ID + ":generic.roll_invulnerable_ticks", new ClampedEntityAttribute("attribute.name.generic.roll_invulnerable_ticks", 0.0, 0.0, 1024.0).setTracked(true));
		CombatRollExtension.ROLL_STAMINA_COST = register(CombatRollExtension.MOD_ID + ":generic.roll_stamina_cost", new ClampedEntityAttribute("attribute.name.generic.roll_stamina_cost", 1.0, 0.0, 1024.0).setTracked(true));
	}
}
