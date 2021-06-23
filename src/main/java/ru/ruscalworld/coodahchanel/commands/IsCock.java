package ru.ruscalworld.coodahchanel.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import ru.ruscalworld.coodahchanel.core.Argument;
import ru.ruscalworld.coodahchanel.core.Command;

public class IsCock {
    @Command(description = "Проверяет, является ли петухом указанный пользователь", arguments = {
            @Argument(
                    type = OptionType.USER, name = "user", required = true,
                    description = "Пользователь, которого необходимо проверить на то"
            )
    })
    public void isCock(SlashCommandEvent event) {
        Message message = event.getHook().sendMessage("Test message").complete();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        message.editMessage("hm").queue();
    }
}
