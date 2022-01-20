package de.xxschrandxx.wsc.bukkit.listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.xxschrandxx.wsc.bukkit.api.WSCLinkerEvent;

public class PlayerInfoListener implements Listener {

    @EventHandler
    public void onCommandType(WSCLinkerEvent event) {
        if (!event.getType().equals("playerinfo")) {
            return;
        }
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (event.getContent() == null) {
            result.put("error", true);
            result.put("message", "No content given.");
            event.sendResult(result);
            return;
        }
        // Test uuid
        if (event.getString("uuid") == null) {
            result.put("error", true);
            result.put("message", "No uuid given.");
        }
        else if (event.getString("uuid").isBlank()) {
            result.put("error", true);
            result.put("message", "Given uuid empty or blank.");
        }
        UUID uuid = null;
        try {
            uuid = UUID.fromString(event.getString("uuid"));
        }
        catch (IllegalArgumentException e) {
            result.put("error", true);
            result.put("message", "Given String is not a valid UUID");
        }
        if (result.isEmpty()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                result.put("error", false);
                HashMap<String, Object> infos = new HashMap<String, Object>();
                infos.put("Address", player.getAddress().getAddress().getHostName());
                infos.put("CustomName", player.getCustomName());
                infos.put("DisplayName", player.getDisplayName());
                infos.put("Exp", player.getExp());
                infos.put("FirstPlayed", player.getFirstPlayed());
                infos.put("Gamemode", player.getGameMode().toString());
                infos.put("LastPlayed", player.getLastPlayed());
                infos.put("Level", player.getLevel());
                infos.put("Locale", player.getLocale());
                HashMap<String, Object> location = new HashMap<String, Object>();
                location.put("World", player.getLocation().getWorld().getName());
                location.put("X", player.getLocation().getX());
                location.put("Y", player.getLocation().getY());
                location.put("Z", player.getLocation().getZ());
                location.put("Yaw", player.getLocation().getYaw());
                location.put("Pitch", player.getLocation().getPitch());
                infos.put("Location", location);
                infos.put("Name", player.getName());
                infos.put("Ping", player.getPing());
                infos.put("PlayerTime", player.getPlayerTime());
                infos.put("Banned", player.isBanned());
                infos.put("Online", player.isOnline());
                infos.put("Op", player.isOp());
                infos.put("Whitelisted", player.isWhitelisted());
                result.put("message", infos);
            }
            else {
                OfflinePlayer offlinePlayer =  Bukkit.getOfflinePlayer(uuid);
                result.put("error", false);
                HashMap<String, Object> infos = new HashMap<String, Object>();
                infos.put("FirstPlayed", offlinePlayer.getFirstPlayed());
                infos.put("LastPlayed", offlinePlayer.getLastPlayed());
                infos.put("Name", offlinePlayer.getName());
                infos.put("Banned", offlinePlayer.isBanned());
                infos.put("Online", offlinePlayer.isOnline());
                infos.put("Op", offlinePlayer.isOp());
                infos.put("Whitelisted", offlinePlayer.isWhitelisted());
                result.put("message", infos);
            }
        }
        event.sendResult(result);
    }

}
