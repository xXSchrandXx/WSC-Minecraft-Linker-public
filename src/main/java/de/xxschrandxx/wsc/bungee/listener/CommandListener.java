package de.xxschrandxx.wsc.bungee.listener;

import java.util.HashMap;

import de.xxschrandxx.wsc.spigot.api.WSCLinkerEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

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
            ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), event.getString("command"));
            result.put("error", false);
        }
        event.sendResult(result);
    }

}
