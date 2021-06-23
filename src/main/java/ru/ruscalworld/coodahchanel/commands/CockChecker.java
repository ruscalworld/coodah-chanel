package ru.ruscalworld.coodahchanel.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import ru.ruscalworld.coodahchanel.core.Argument;
import ru.ruscalworld.coodahchanel.core.Command;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CockChecker {
    @Command(description = "Проверяет, является ли петухом указанный пользователь", arguments = {
            @Argument(
                    type = OptionType.USER, name = "user", required = true,
                    description = "Пользователь, которого необходимо проверить"
            )
    })
    public void isCock(SlashCommandEvent event) {
        OptionMapping option = event.getOption("user");
        assert option != null;
        User user = option.getAsUser();
        long id = user.getIdLong();

        CompletableFuture.runAsync(() -> {
            Message message = event.getHook().sendMessageEmbeds(makeWaitEmbed(user)).complete();
            Random random = new Random();
            random.setSeed(System.currentTimeMillis());
            int delay = random.nextInt(5000) + 5000;
            try {
                Thread.sleep(delay);
                message.editMessageEmbeds(makeResultEmbed(user, id % 2 == 1, delay)).queue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private MessageEmbed makeWaitEmbed(User user) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setDescription("Нейросеть определяет, является ли " + user.getAsMention() + " петухом. Пожалуйста, подождите. Это может занять до 10 секунд.");
        return builder.build();
    }

    private MessageEmbed makeResultEmbed(User user, boolean cock, double delta) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        double accuracy = random.nextInt(10) + 70 + (user.getIdLong() % 20);
        String cockString = (cock ? "" : "не ") + "петух";

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(user.getAsTag() + " - " + cockString);
        builder.setDescription("В ходе проверки нейросеть определила, что " + user.getAsMention() + " - " + cockString);
        builder.addField("Время обработки", delta / 1000 + " секунд", true);
        builder.addField("Уверенность нейросети", "" + accuracy + "%", true);
        return builder.build();
    }
}
