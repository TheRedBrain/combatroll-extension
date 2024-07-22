package com.github.theredbrain.combatrollextension.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(
        name = "server"
)
public class ServerConfig implements ConfigData {
    @Comment("When true, stamina must be above 0 for rolling.")
    public boolean rolling_requires_stamina = false;
    @Comment("""
            When true, invulnerable ticks on roll are determined by the attribute 'generic.roll_invulnerable_ticks'.
            This overrides the 'invulnerable_ticks_upon_roll' setting in the combat roll server config.
            """)
    public boolean is_roll_invulnerable_ticks_attribute_active = false;
    @Comment("The stamina cost determined by the 'generic.roll_stamina_cost' attribute is multiplied by this value.")
    public float global_rolling_stamina_cost_multiplier = 1.0f;
    public ServerConfig() {
    }
}
