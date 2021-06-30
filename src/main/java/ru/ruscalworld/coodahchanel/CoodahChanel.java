package ru.ruscalworld.coodahchanel;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import ru.ruscalworld.coodahchanel.commands.CockChecker;
import ru.ruscalworld.coodahchanel.commands.InfoCommand;
import ru.ruscalworld.coodahchanel.core.CommandDispatcher;
import ru.ruscalworld.coodahchanel.listeners.SlashCommandListener;

import javax.security.auth.login.LoginException;

public class CoodahChanel {
    private static CoodahChanel instance;
    private final CommandDispatcher commandDispatcher;
    private final JDA jda;

    public CoodahChanel(JDA jda) {
        this.jda = jda;
        this.commandDispatcher = new CommandDispatcher();
        this.onStart();
    }

    public static void main(String[] args) throws LoginException, InterruptedException {
        String token = System.getenv("CC_BOT_TOKEN");
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.addEventListeners(new SlashCommandListener());
        instance = new CoodahChanel(builder.build().awaitReady());
    }

    public void onStart() {
        CommandDispatcher dispatcher = this.getCommandDispatcher();
        dispatcher.registerCommands(new CockChecker());
        dispatcher.registerCommands(new InfoCommand());
        dispatcher.updateBotCommands(this.getJDA());
    }

    public static CoodahChanel getInstance() {
        return instance;
    }

    public CommandDispatcher getCommandDispatcher() {
        return commandDispatcher;
    }

    public JDA getJDA() {
        return jda;
    }
}
