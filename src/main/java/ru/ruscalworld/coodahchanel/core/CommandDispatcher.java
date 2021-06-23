package ru.ruscalworld.coodahchanel.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

public class CommandDispatcher {
    private final static Logger logger = LoggerFactory.getLogger("CommandDispatcher");
    private final HashMap<String, ProvidedCommand> registeredCommands = new HashMap<>();

    public static Logger getLogger() {
        return logger;
    }

    public void dispatch(SlashCommandEvent event) {
        ProvidedCommand command = this.getRegisteredCommand(event.getName());
        if (command == null) return;
        event.deferReply().queue();
        command.execute(event);
    }

    public void registerCommands(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Command.class)) continue;
            ProvidedCommand command = new ProvidedCommand(object, method);
            this.registeredCommands.put(command.getName(), command);
        }
    }

    public void updateBotCommands(JDA jda) {
        CommandListUpdateAction commands = jda.updateCommands();
        for (ProvidedCommand command : this.getRegisteredCommands()) {
            CommandData data = new CommandData(command.getName(), command.getInfo().description());
            for (Argument argument : command.getInfo().arguments()) {
                data.addOption(argument.type(), argument.name(), argument.description(), argument.required());
            }
            commands = commands.addCommands(data);
        }
        commands.queue();
    }

    public @Nullable ProvidedCommand getRegisteredCommand(String id) {
        return this.registeredCommands.get(id.toLowerCase(Locale.ROOT));
    }

    public Collection<ProvidedCommand> getRegisteredCommands() {
        return registeredCommands.values();
    }
}
