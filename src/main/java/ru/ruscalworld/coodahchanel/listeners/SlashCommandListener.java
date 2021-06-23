package ru.ruscalworld.coodahchanel.listeners;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ru.ruscalworld.coodahchanel.CoodahChanel;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        CoodahChanel.getInstance().getCommandDispatcher().dispatch(event);
    }
}
