package com.jackzdev.smartcmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.apache.logging.log4j.core.jmx.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Smartcmds implements ModInitializer {
    public static final String MOD_ID = "smartcmds";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Things the mod does the second it's initialised.
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerAliases(dispatcher);
        });
    }

    // Function for registering aliases, you can add your own aliases if you want.
    private void registerAliases(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("gm")
                        .then(CommandManager.argument("mode", StringArgumentType.word())
                                .executes(context -> {
                                    String input = StringArgumentType.getString(context, "mode").toLowerCase();
                                    ServerCommandSource source = context.getSource();
                                    String gamemode;

                                    switch (input) {
                                        case "1", "creative" -> gamemode = "creative";
                                        case "0", "survival", "2" -> gamemode = "survival";
                                        case "3", "adventure" -> gamemode = "adventure";
                                        case "spectator", "4" -> gamemode = "spectator";
                                        default -> {
                                            source.sendFeedback(() -> Text.literal("Invalid input, use default gamemode names or 1 till 4."), false);
                                            return 0;
                                        }
                                    }

                                    source.getServer().getCommandManager()
                                            .executeWithPrefix(source, STR."gamemode \{gamemode} \{source.getName()}");

                                    return 1;
                                }))
        );

        dispatcher.register(
                CommandManager.literal("heal")
                        .executes(commandContext ->  {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."effect give \{source.getName()} minecraft:instant_health 1 252 true");
                            source.sendFeedback(() -> Text.literal("You have been healed by the angels above."), false);
                            return 1;
                        })
        );
    // Time toggles for day, night, etc.
        dispatcher.register(
                CommandManager.literal("day")
                        .executes(commandContext ->   {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."time set day");
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("night")
                        .executes(commandContext -> {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."time set night");
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("noon")
                        .executes(commandContext ->  {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."time set noon");
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("sunset")
                        .executes(commandContext -> {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."time set sunset");
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("midnight")
                        .executes(commandContext -> {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."time set midnight");
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("suicide")
                        .executes(commandContext ->  {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."kill @s");
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("fly")
                        .executes(commandContext ->  {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."ability @s mayfly true");
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("nofly")
                        .executes(commandContext ->   {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."ability @s mayfly false");
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("rainbowsheep")
                        .executes(commandContext ->   {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."summon sheep ~ ~ ~ {CustomName:'jeb_'}");
                            return 1;
                        })
        );

        dispatcher.register(
                CommandManager.literal("ping")
                        .executes(commandContext -> {
                            ServerCommandSource source = commandContext.getSource();
                            source.getServer().getCommandManager()
                                    .executeWithPrefix(source, STR."me , I'm watching you, be careful, I'm looking at every single step you take. Watch out.");
                            return 1;
                        })
        );

    }}
