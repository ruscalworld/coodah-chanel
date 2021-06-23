package ru.ruscalworld.coodahchanel.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import ru.ruscalworld.coodahchanel.core.Argument;
import ru.ruscalworld.coodahchanel.core.Command;

public class CockChecker {
    @Command(description = "Проверяет, является ли петухом указанный пользователь", arguments = {
            @Argument(
                    type = OptionType.USER, name = "user", required = true,
                    description = "Пользователь, которого необходимо проверить на то"
            )
    })
    public void isCock(SlashCommandEvent event) {
        OptionMapping option = event.getOption("user");
        assert option != null;
        User user = option.getAsUser();
        long id = user.getIdLong();
        System.out.println(id);
        System.out.println(id % 2);
        Message message = event.getHook().sendMessage(id % 2 == 1 ? "Да" : "Нет").complete();
    }
}
