package com.github.theredbrain.combatrollextension.config;

import com.github.theredbrain.combatrollextension.CombatRollExtension;
import me.fzzyhmstrs.fzzy_config.config.Config;

public class ServerConfig extends Config {

	public ServerConfig() {
		super(CombatRollExtension.identifier("server"));
	}

	public boolean rolling_requires_stamina = false;
	public boolean rolling_requires_stamina_cost = false;
	public float global_rolling_stamina_cost_multiplier = 1.0f;
	public boolean is_roll_invulnerable_ticks_attribute_active = false;
}
