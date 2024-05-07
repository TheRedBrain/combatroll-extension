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
    @Comment("Stamina cost for each roll.")
    public float rolling_stamina_cost = 0.0f;
    public ServerConfig() {
    }
}
