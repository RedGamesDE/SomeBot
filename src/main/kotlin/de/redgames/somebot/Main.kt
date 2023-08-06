@file:JvmName("Main")

package de.redgames.somebot

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.requests.GatewayIntent
import java.io.BufferedReader
import java.io.InputStreamReader

private const val token = "NDgzMjE4MzE2NjIyODIzNDM0.DmQP1g.hmfdCi1L5QO69f1uoWmxCrQgXrw"

fun main() {
    val jda = JDABuilder.create(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
        .setToken(token)
        .setAutoReconnect(true)
        .setStatus(OnlineStatus.INVISIBLE)
        .build()

    println("[*] Starting...")

    val bot = SomeBot(jda)
    bot.start()

    println("[*] Bot started! (Running on ${bot.countServers()} servers)")

    BufferedReader(InputStreamReader(System.`in`)).use {
        while (true) {
            when (it.readLine().toLowerCase()) {
                "shutdown", "stop" -> break
                "servercount" -> println("[*] Running on ${bot.countServers()} servers!")
                "help" -> println("[*] Available commands: shutdown, servercount")
            }
        }
    }

    println("[*] Shutting down...")

    bot.shutdown()

    println("[*] Shutdown complete!")
}
