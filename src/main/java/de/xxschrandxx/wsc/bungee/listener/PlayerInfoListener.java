package de.xxschrandxx.wsc.bungee.listener;

import java.util.HashMap;
import java.util.UUID;


import de.xxschrandxx.wsc.bungee.api.WSCLinkerEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

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
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(uuid);
            if (player != null) {
                result.put("error", false);
                HashMap<String, Object> infos = new HashMap<String, Object>();
                infos.put("Address", player.getPendingConnection().getVirtualHost().getAddress().getHostName());
                infos.put("DisplayName", player.getDisplayName());
                infos.put("Locale", player.getLocale());
                infos.put("Name", player.getName());
                infos.put("Ping", player.getPing());
                infos.put("Server", player.getServer().getInfo().getName());
                infos.put("Connected", player.isConnected());
                result.put("message", infos);
            }
            else {
                result.put("error", true);
                result.put("message", "Player not found.");
            }
        }
        event.sendResult(result);
    }

}
