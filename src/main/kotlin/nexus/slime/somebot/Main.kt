@file:JvmName("Main")

package nexus.slime.somebot

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.requests.GatewayIntent
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val token = System.getenv("BOT_TOKEN")

    if (token == null) {
        System.err.println("Environment variable BOT_TOKEN must be set to the bot token!")
        return
    }

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
            when (it.readLine().lowercase()) {
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
