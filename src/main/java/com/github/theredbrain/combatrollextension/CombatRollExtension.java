package com.github.theredbrain.combatrollextension;

import com.github.theredbrain.combatrollextension.config.ServerConfig;
import com.github.theredbrain.combatrollextension.entity.DuckLivingEntityMixin;
import com.github.theredbrain.staminaattributes.entity.StaminaUsingEntity;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import net.combat_roll.api.RollInvulnerable;
import net.combat_roll.api.event.ServerSideRollEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CombatRollExtension implements ModInitializer {
	public static final String MOD_ID = "combatrollextension";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ServerConfig SERVER_CONFIG;

	public static RegistryEntry<EntityAttribute> ROLL_STAMINA_COST;
	public static RegistryEntry<EntityAttribute> ROLL_INVULNERABLE_TICKS;

	public static final boolean isStaminaAttributesLoaded = FabricLoader.getInstance().isModLoaded("staminaattributes");

	public static float getCurrentStamina(LivingEntity livingEntity) {
		float currentStamina = 0.0F;
		if (isStaminaAttributesLoaded) {
			currentStamina = ((StaminaUsingEntity) livingEntity).staminaattributes$getStamina();
		}
		return currentStamina;
	}

	public static void addStamina(LivingEntity livingEntity, float amount) {
		if (isStaminaAttributesLoaded) {
			((StaminaUsingEntity) livingEntity).staminaattributes$addStamina(amount);
		}
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Combat Roll was extended!");
		SERVER_CONFIG = ConfigApiJava.registerAndLoadConfig(ServerConfig::new);

		ServerSideRollEvents.PLAYER_START_ROLLING.register((serverPlayerEntity, vec3d) -> {
			if (!serverPlayerEntity.isCreative()) {
				CombatRollExtension.addStamina(serverPlayerEntity, -(((DuckLivingEntityMixin) serverPlayerEntity).combatrollextension$getActualRollStaminaCost()));
				if (SERVER_CONFIG.is_roll_invulnerable_ticks_attribute_active) {
					((RollInvulnerable) serverPlayerEntity).setRollInvulnerableTicks((int) ((DuckLivingEntityMixin) serverPlayerEntity).combatrollextension$getRollInvulnerableTicks());
				}
			}
		});

	}

	public static Identifier identifier(String path) {
		return Identifier.of(MOD_ID, path);
	}
}