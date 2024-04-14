package ru.aloyenz.effectsapi;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aloyenz.effectsapi.events.CommandsRegister;

@Mod(EffectsAPI.MOD_ID)
public class EffectsAPI {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "effectsapi";

    private static EffectsAPI INSTANCE;

    private final SettingsManager settingsManager;

    public static EffectsAPI getInstance() {
        return INSTANCE;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public EffectsAPI() {
        MinecraftForge.EVENT_BUS.register(new CommandsRegister());

        INSTANCE = this;
        settingsManager = new SettingsManager();
    }
}
