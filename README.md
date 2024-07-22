# Combat Roll Extension

This is an extension to the [Combat Roll](https://modrinth.com/mod/combat-roll) mod by Daedelus. It adds support for stamina provided by the mod [Stamina Attributes](https://modrinth.com/mod/stamina-attributes).

It adds two new entity attributes that control the amount of invulnerable ticks on rolling and the stamina cost for rolling.

## Server config

When 'rolling_requires_stamina' is set to true, the players stamina must be above 0 to be able to roll.

The stamina cost for rolling is multiplied by 'global_rolling_stamina_cost_multiplier'.

'is_roll_invulnerable_ticks_attribute_active' controls where invulnerable ticks are set.

When false, the value set in the 'Combat Roll' server config is used.\
When true, the entity attribute value is used.