package com.github.theredbrain.combatrollextension;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class CombatRollExtensionClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientPlayNetworking.registerGlobalReceiver(CombatRollExtension.ServerConfigSyncPacket.PACKET_ID, (payload, context) -> {
            CombatRollExtension.serverConfig = payload.serverConfig();
        });
    }
}
