package ru.ruscalworld.coodahchanel.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import ru.ruscalworld.coodahchanel.CoodahChanel;
import ru.ruscalworld.coodahchanel.core.Command;

public class InfoCommand {
    // Создаём ссылку для приглашения вручную, т.к. JDA не поддерживает создание ссылок с правами на создание команд
    public static final String INVITE_URL = "https://discord.com/oauth2/authorize?client_id=669603477319843859&scope=applications.commands%20bot";

    @Command(description = "Выводит общую информацию о боте")
    public void info(SlashCommandEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Кудах Шанель");
        builder.setDescription(
                "Нейросеть, позволяющая определить, является ли пользователь Discord петухом. " +
                        "Для достижения максимальной точности проверок бот использует более 50 различных параметров каждого участника " +
                        "и обучена на " + (System.currentTimeMillis() / 0xFFFFFF) + " различных пользователях."
        );

        int guilds = CoodahChanel.getInstance().getJDA().getGuilds().size();
        builder.setFooter("Бот используется на " + guilds + " серверах");

        event.getHook().sendMessageEmbeds(builder.build()).addActionRow(
                Button.link("https://github.com/ruscalworld/coodahchanel", "Исходный код"),
                Button.link(INVITE_URL, "Добавить на сервер")
        ).queue();
    }
}
