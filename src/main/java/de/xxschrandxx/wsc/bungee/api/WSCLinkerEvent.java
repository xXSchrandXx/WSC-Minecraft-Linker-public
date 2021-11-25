package de.xxschrandxx.wsc.bungee.api;

import java.util.HashMap;

import com.google.gson.Gson;

import de.xxschrandxx.wsc.universal.IWSCLinkerEvent;
import net.borlcand.rcon.commandsender.RconCommandSender;
import net.md_5.bungee.api.plugin.Event;

public class WSCLinkerEvent extends Event implements IWSCLinkerEvent {

    private final RconCommandSender sender;

    public RconCommandSender getSender() {
        return this.sender;
    }

    public void sendMessage(String message) {
        this.sender.sendMessage(message);
    }

    private final Gson gson = new Gson();

    public void sendResult(HashMap<String, Object> result) {
        this.sendMessage(this.gson.toJson(result));
    }

    private final String type;

    public String getType() {
        return this.type;
    }

    private final HashMap<String, String> content;

    public HashMap<String, String> getContent() {
        return this.content;
    }

    public String getString(String key) {
        return this.content.get(key);
    }

    public WSCLinkerEvent(RconCommandSender sender, String type, HashMap<String, String> content) {
        this.sender = sender;
        this.type = type;
        this.content = content;
    }

}
