package nexus.slime.somebot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.concurrent.TimeUnit

class SomeBot(private val jda: JDA) {
    fun start() {
        jda.addEventListener(object : ListenerAdapter() {
            override fun onMessageReceived(event: MessageReceivedEvent) = handleGuildMessage(event)
        })

        jda.presence.setPresence(OnlineStatus.ONLINE, Activity.watching("@someone"))
    }

    private fun handleGuildMessage(event: MessageReceivedEvent) {
        if (!event.isFromGuild) return

        val mentionedUsers = event.message.mentions.users

        if (mentionedUsers.contains(jda.selfUser) && event.member != null) {
            SomeoneMessage.send(event.channel.asTextChannel(), event.member!!)
            event.message.delete().queueAfter(100, TimeUnit.MILLISECONDS)
        }
    }

    fun shutdown() = jda.shutdown()

    fun countServers() = jda.guilds.size
}
