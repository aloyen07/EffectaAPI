package ru.aloyenz.effectsapi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.math.vector.Vector3f;
import ru.aloyenz.effectsapi.EffectsAPI;

public class EffectsCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("rendereffects")
                .then(Commands.literal("chromatic_aberration")
                        .then(Commands.argument("multiplier", FloatArgumentType.floatArg(-1F, 1F)).executes((context) -> {
                            EffectsAPI.getInstance().getSettingsManager().setAberrationMultiplier(
                                    context.getArgument("multiplier", Float.class));
                            return 1;
                        })))
                .then(Commands.literal("linear_chromatic_aberration")
                        .then(Commands.argument("multiplier", FloatArgumentType.floatArg(-1F, 1F)).executes((context) -> {
                            EffectsAPI.getInstance().getSettingsManager().setLinearAberrationMultiplier(
                                    context.getArgument("multiplier", Float.class));
                            return 1;
                        })))
                .then(Commands.literal("black_and_white")
                        .then(Commands.argument("multiplier", FloatArgumentType.floatArg(0F, 1F)).executes((context) -> {
                            EffectsAPI.getInstance().getSettingsManager().setWhiteAndBlackMultiplier(
                                    context.getArgument("multiplier", Float.class)
                            );
                            return 1;
                        })))
                .then(Commands.literal("color_multiplier")
                        .then(Commands.argument("r_multiplier", FloatArgumentType.floatArg(0F, 4F))
                                .then(Commands.argument("g_multiplier", FloatArgumentType.floatArg(0F, 4F))
                                        .then(Commands.argument("b_multiplier", FloatArgumentType.floatArg(0F, 4F)).executes((context) -> {
                                            EffectsAPI.getInstance().getSettingsManager().setColorMultiplier(
                                                    new Vector3f(context.getArgument("r_multiplier", Float.class),
                                                            context.getArgument("g_multiplier", Float.class),
                                                            context.getArgument("b_multiplier", Float.class))
                                            );

                                            return 1;
                                        }))))
                        )
                .then(Commands.literal("color_add")
                        .then(Commands.argument("r_add", FloatArgumentType.floatArg(-1F, 1F))
                                .then(Commands.argument("g_add", FloatArgumentType.floatArg(-1F, 1F))
                                        .then(Commands.argument("b_add", FloatArgumentType.floatArg(-1F, 1F)).executes((context) -> {
                                            EffectsAPI.getInstance().getSettingsManager().setColorAdd(
                                                    new Vector3f(context.getArgument("r_add", Float.class),
                                                            context.getArgument("g_add", Float.class),
                                                            context.getArgument("b_add", Float.class))
                                            );

                                            return 1;
                                        }))))
                )
        );
    }
}
