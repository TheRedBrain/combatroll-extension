package com.github.theredbrain.combatrollextension;

import com.github.theredbrain.combatrollextension.config.ServerConfig;
import com.github.theredbrain.combatrollextension.config.ServerConfigWrapper;
import com.github.theredbrain.combatrollextension.entity.DuckLivingEntityMixin;
import com.github.theredbrain.staminaattributes.StaminaAttributes;
import com.github.theredbrain.staminaattributes.entity.StaminaUsingEntity;
import com.google.gson.Gson;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.combatroll.api.RollInvulnerable;
import net.combatroll.api.event.ServerSideRollEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CombatRollExtension implements ModInitializer {
	public static final String MOD_ID = "combatrollextension";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ServerConfig serverConfig;
	private static PacketByteBuf serverConfigSerialized = PacketByteBufs.create();

	public static EntityAttribute ROLL_STAMINA_COST;
	public static EntityAttribute ROLL_INVULNERABLE_TICKS;

	@Override
	public void onInitialize() {
		LOGGER.info("Combat Roll was extended!");

		// Config
		AutoConfig.register(ServerConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		serverConfig = ((ServerConfigWrapper) AutoConfig.getConfigHolder(ServerConfigWrapper.class).getConfig()).server;

		// Events
		serverConfigSerialized = ServerConfigSync.write(serverConfig);
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			sender.sendPacket(StaminaAttributes.ServerConfigSync.ID, serverConfigSerialized);
		});
		ServerSideRollEvents.PLAYER_START_ROLLING.register((serverPlayerEntity, vec3d) -> {
			if (!serverPlayerEntity.isCreative()) {
				((StaminaUsingEntity) serverPlayerEntity).staminaattributes$addStamina(-(((DuckLivingEntityMixin) serverPlayerEntity).combatrollextension$getRollStaminaCost() * serverConfig.global_rolling_stamina_cost_multiplier));
				if (serverConfig.is_roll_invulnerable_ticks_attribute_active) {
					((RollInvulnerable) serverPlayerEntity).setRollInvulnerableTicks((int) ((DuckLivingEntityMixin) serverPlayerEntity).combatrollextension$getRollInvulnerableTicks());
				}
			}
		});

	}

	public static class ServerConfigSync { // TODO 1.20.6 port to packet
		public static Identifier ID = identifier("server_config_sync");

		public static PacketByteBuf write(ServerConfig serverConfig) {
			var gson = new Gson();
			var json = gson.toJson(serverConfig);
			var buffer = PacketByteBufs.create();
			buffer.writeString(json);
			return buffer;
		}

		public static ServerConfig read(PacketByteBuf buffer) {
			var gson = new Gson();
			var json = buffer.readString();
			return gson.fromJson(json, ServerConfig.class);
		}
	}

	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}

}