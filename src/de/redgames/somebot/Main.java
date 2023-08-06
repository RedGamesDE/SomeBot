package de.redgames.somebot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.managers.Presence;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Main {
    public static JDA jda;

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);

        builder.addEventListener(new EventListener());
        builder.setAutoReconnect(true);
        builder.setStatus(OnlineStatus.INVISIBLE);
        builder.setToken("NDgzMjE4MzE2NjIyODIzNDM0.DmQP1g.hmfdCi1L5QO69f1uoWmxCrQgXrw");

        System.out.println("Starting ...");

        jda = builder.buildBlocking();

        Presence presence = jda.getPresence();
        presence.setGame(Game.watching("@someone"));
        presence.setStatus(OnlineStatus.ONLINE);

        System.out.println("Started!");
        System.out.println("Running on " + jda.getSelfUser().getMutualGuilds().size() + " servers!");

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String cmd = scanner.nextLine();
            if(cmd.equalsIgnoreCase("shutdown")) break;
            if(cmd.equalsIgnoreCase("stop")) break;
            if(cmd.equalsIgnoreCase("servercount")) System.out.println("Running on " + jda.getSelfUser().getMutualGuilds().size() + " servers!");
        }

        System.out.println("Shutting down!");

        presence.setStatus(OnlineStatus.OFFLINE);
        jda.shutdown();
    }
}
