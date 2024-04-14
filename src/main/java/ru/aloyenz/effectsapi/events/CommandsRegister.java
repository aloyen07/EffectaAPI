package ru.aloyenz.effectsapi.events;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.aloyenz.effectsapi.commands.EffectsCommand;

@Mod.EventBusSubscriber
public class CommandsRegister {

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        EffectsCommand.register(event.getDispatcher());
    }
}
