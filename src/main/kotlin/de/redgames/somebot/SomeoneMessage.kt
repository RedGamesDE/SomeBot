package de.redgames.somebot

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.TextChannel
import java.awt.Color
import java.util.*

object SomeoneMessage {
    private val smileys = arrayOf(
        "(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ. o ･ ｡ﾟ",
        "༼ つ ◕_◕ ༽つ",
        "ヽ༼ ಠ益ಠ ༽ﾉ",
        "(◕‿◕✿)",
        "¯(°_o)/¯",
        "¯\\_(ツ)_/¯",
        "（✿ ͡◕ ᴗ◕)つ━━✫・o。",
        "ಠ_ಠ",
        "(⁄ ⁄•⁄ω⁄•⁄ ⁄)",
        "(╯°□°）╯︵ ┻━┻"
    )

    private fun generateSmiley(random: Random) = smileys[random.nextInt(smileys.size)]

    fun send(channel: TextChannel, sender: Member) {
        val members = channel.members
            .filter { it.onlineStatus != OnlineStatus.OFFLINE }
            .filter { !it.user.isBot }
            .filter { it != sender }
            .toMutableList()

        println(members)

        val random = Random()
        val member = members[random.nextInt(members.size)]

        val embedBuilder = EmbedBuilder()
        embedBuilder.setAuthor(sender.effectiveName)
        embedBuilder.setDescription("${channel.jda.selfUser.asMention} **${generateSmiley(random)}** ${member.asMention}")
        embedBuilder.setColor(Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)))

        channel.sendMessage(embedBuilder.build()).queue()
    }
}