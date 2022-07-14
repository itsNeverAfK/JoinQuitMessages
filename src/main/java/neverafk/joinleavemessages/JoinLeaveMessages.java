package neverafk.joinleavemessages;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinLeaveMessages extends JavaPlugin implements Listener {

    String joinMessage = null;
    Boolean sendJoinMessage = null;

    String quitMessage = null;
    Boolean sendQuitMessage = null;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        this.saveDefaultConfig();

        joinMessage = this.getConfig().getString("join.message");

        if(this.getConfig().getString("join.sending").equals("true")) {
            sendJoinMessage = true;
        }

        quitMessage = this.getConfig().getString("quit.message");

        if(this.getConfig().getString("quit.sending").equals("true")) {
            sendQuitMessage = true;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(sendJoinMessage) {
            event.setJoinMessage(joinMessage.replace("{ColorCode}", "§").replace("{player}", event.getPlayer().getName()).replace("{online}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("{max_online}", String.valueOf(Bukkit.getMaxPlayers())));
        } else {
            event.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if(sendQuitMessage) {
            event.setQuitMessage(quitMessage.replace("{ColorCode}", "§").replace("{player}", event.getPlayer().getName()).replace("{online}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("{max_online}", String.valueOf(Bukkit.getMaxPlayers())));
        } else {
            event.setQuitMessage(null);
        }
    }
}
