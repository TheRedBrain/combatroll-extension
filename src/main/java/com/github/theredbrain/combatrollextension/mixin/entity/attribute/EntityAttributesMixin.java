package com.github.theredbrain.combatrollextension.mixin.entity.attribute;

import com.github.theredbrain.combatrollextension.CombatRollExtension;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityAttributes.class)
public class EntityAttributesMixin {
	static {
		CombatRollExtension.ROLL_INVULNERABLE_TICKS = Registry.registerReference(Registries.ATTRIBUTE, CombatRollExtension.identifier("generic.roll_invulnerable_ticks"), new ClampedEntityAttribute("attribute.name.generic.roll_invulnerable_ticks", 0.0, 0.0, 1024.0).setTracked(true));
		CombatRollExtension.ROLL_STAMINA_COST = Registry.registerReference(Registries.ATTRIBUTE, CombatRollExtension.identifier("generic.roll_stamina_cost"), new ClampedEntityAttribute("attribute.name.generic.roll_stamina_cost", 1.0, 0.0, 1024.0).setTracked(true));
	}
}
