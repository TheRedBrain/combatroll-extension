package com.github.theredbrain.combatrollextension;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class CombatRollExtensionClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientPlayNetworking.registerGlobalReceiver(CombatRollExtension.ServerConfigSync.ID, (client, handler, buf, responseSender) -> {
            CombatRollExtension.serverConfig = CombatRollExtension.ServerConfigSync.read(buf);
        });
    }
}
