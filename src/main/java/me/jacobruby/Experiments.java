package me.jacobruby;

import me.jacobruby.packets.CPlayerConnection;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Experiments extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        registerListeners(this);
    }

    public void registerListeners(Listener... listeners) {
        PluginManager pm = Bukkit.getPluginManager();

        for (Listener listener : listeners) {
            pm.registerEvents(listener, this);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        EntityPlayer ep = ((CraftPlayer) player).getHandle();

        ep.playerConnection = new CPlayerConnection(MinecraftServer.getServer(), ep.playerConnection.networkManager, ep);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        if (message.matches("set [0-9]+")) {
            String arg = message.split(" ")[1];

            CPlayerConnection.value = Integer.parseInt(arg);

            Bukkit.broadcastMessage("[Experiments] Set entity value to " + arg);
        }
    }
}
