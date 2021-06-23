package ru.ruscalworld.coodahchanel.core;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class ProvidedCommand {
    private final Object provider;
    private final Method method;
    private final Command command;

    public ProvidedCommand(Object provider, Method method) {
        this.provider = provider;
        this.method = method;
        this.command = method.getAnnotation(Command.class);
    }

    public void execute(SlashCommandEvent event) {
        try {
            this.getMethod().invoke(this.getProvider(), event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            CommandDispatcher.getLogger().error("Unable to invoke command method:", e);
        }
    }

    public String getName() {
        return this.getMethod().getName().toLowerCase(Locale.ROOT);
    }

    public Object getProvider() {
        return provider;
    }

    public Method getMethod() {
        return method;
    }

    public Command getInfo() {
        return command;
    }
}
