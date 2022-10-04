package dev.ajqm.logger

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {

    val prefix = this.config.getString("Config.Prefix")

    fun loadConfig() {
        this.config.options().copyDefaults(true)
        saveConfig()
    }

    override fun onEnable() {
        val color = server.consoleSender
        color.sendMessage(ChatColor.GREEN.toString() + "Plugin has been enabled! ")
        server.pluginManager.registerEvents(this, this)
        loadConfig()
    }

    override fun onDisable() {
        val color = server.consoleSender
        color.sendMessage(ChatColor.RED.toString() + "Plugin has been disabled! ")
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val p = event.player
        val msg = this.config.getString("Config.onLogin")?.replace("%player%".toRegex(), p.name.toString())
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + msg));
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val p = event.player
        val msg = this.config.getString("Config.onQuit")?.replace("%player%".toRegex(), p.name.toString())
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + msg));
    }
}
