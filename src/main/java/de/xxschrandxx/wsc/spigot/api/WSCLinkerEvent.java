package de.xxschrandxx.wsc.spigot.api;

import java.util.HashMap;

import com.google.gson.Gson;

import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.xxschrandxx.wsc.universal.IWSCLinkerEvent;

public class WSCLinkerEvent extends Event implements IWSCLinkerEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private final RemoteConsoleCommandSender sender;

    public RemoteConsoleCommandSender getSender() {
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

    public WSCLinkerEvent(RemoteConsoleCommandSender sender, String type, HashMap<String, String> content) {
        super(false);
        this.sender = sender;
        this.type = type;
        this.content = content;
    }

}
