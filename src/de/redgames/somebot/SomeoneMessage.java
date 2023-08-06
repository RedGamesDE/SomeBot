package de.redgames.somebot;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SomeoneMessage {
    public static void sendSomeoneMessage(TextChannel channel, Member sender) {
        Stream<Member> memberStream = channel.getMembers().stream();

        memberStream = memberStream.filter(s -> s.getOnlineStatus() != OnlineStatus.OFFLINE);
        memberStream = memberStream.filter(s -> !s.getUser().isBot());
        memberStream = memberStream.filter(s -> s != sender);

        List<Member> memberList = memberStream.collect(Collectors.toList());
        Random random = new Random();
        Member member = memberList.get(random.nextInt(memberList.size()));


        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(sender.getEffectiveName());
        embedBuilder.setDescription(channel.getJDA().getSelfUser().getAsMention() + " **" + generateSmiley(random) + "** " + member.getAsMention());
        embedBuilder.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

        channel.sendMessage(embedBuilder.build()).queue();
    }

    private static String generateSmiley(Random random) {
        switch(random.nextInt(10)) {
            case(0): return "(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ. o ･ ｡ﾟ";
            case(1): return "༼ つ ◕_◕ ༽つ";
            case(2): return "ヽ༼ ಠ益ಠ ༽ﾉ";
            case(3): return "(◕‿◕✿)";
            case(4): return "¯(°_o)/¯";
            case(5): return "¯\\_(ツ)_/¯";
            case(6): return "（✿ ͡◕ ᴗ◕)つ━━✫・o。";
            case(7): return "ಠ_ಠ";
            case(8): return "(⁄ ⁄•⁄ω⁄•⁄ ⁄)";
            case(9): return "(╯°□°）╯︵ ┻━┻";
        }
        return "";
    }
}
