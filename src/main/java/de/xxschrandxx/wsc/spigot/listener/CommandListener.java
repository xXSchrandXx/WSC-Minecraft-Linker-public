package de.xxschrandxx.wsc.spigot.listener;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.xxschrandxx.wsc.spigot.api.WSCLinkerEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommandType(WSCLinkerEvent event) {
        if (!event.getType().equals("command")) {
            return;
        }
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (event.getContent() == null) {
            result.put("error", true);
            result.put("message", "No content given.");
            event.sendResult(result);
            return;
        }
        // Test Command
        if (event.getString("command") == null) {
            result.put("error", true);
            result.put("message", "No command given.");
        }
        else if (event.getString("command").isBlank()) {
            result.put("error", true);
            result.put("message", "Given command empty or blank.");
        }
        if (result.isEmpty()) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), event.getString("command"));
            result.put("error", false);
        }
        event.sendResult(result);
    }

}
