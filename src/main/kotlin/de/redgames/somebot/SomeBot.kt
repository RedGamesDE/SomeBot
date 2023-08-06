package de.redgames.somebot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.concurrent.TimeUnit

class SomeBot(private val jda: JDA) {
    fun start() {
        jda.addEventListener(object : ListenerAdapter() {
            override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) = handleGuildMessage(event)
        })

        jda.presence.setPresence(OnlineStatus.ONLINE, Activity.watching("@someone"))
    }

    private fun handleGuildMessage(event: GuildMessageReceivedEvent) {
        val mentionedUsers = event.message.mentionedUsers

        if (mentionedUsers.contains(jda.selfUser) && event.member != null) {
            SomeoneMessage.send(event.channel, event.member!!)
            event.message.delete().queueAfter(100, TimeUnit.MILLISECONDS)
        }
    }

    fun shutdown() = jda.shutdown()

    fun countServers() = jda.guilds.size
}
