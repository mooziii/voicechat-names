package com.github.mooziii.voicechatnamesaddon;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.LoggerFactory;

public class GroupNamesAddonClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LoggerFactory.getLogger("groupnames").info("Voice Chat Group Names has been initialized.");
    }
}
