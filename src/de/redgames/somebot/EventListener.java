package de.redgames.somebot;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EventListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        List<User> userList = event.getMessage().getMentionedUsers();
        if(userList.contains(event.getJDA().getSelfUser())) {
            SomeoneMessage.sendSomeoneMessage(event.getChannel(), event.getMember());
            event.getMessage().delete().queueAfter(100, TimeUnit.MILLISECONDS);
        }
    }
}
