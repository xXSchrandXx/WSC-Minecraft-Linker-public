package de.xxschrandxx.wsc.universal;

import java.util.HashMap;

public interface IWSCLinkerEvent {

    /**
     * Returns the sender of.
     * @return Given sender.
     */
    public Object getSender();

    /**
     * Sends a message to the sender.
     * Use SendResult to return!
     * @param message Message to send.
     */
    public void sendMessage(String message);

    /**
     * Sends the result to the sender.
     * @param result Result to send.
     */
    public void sendResult(HashMap<String, Object> result);

    /**
     * Returns the type of the command.
     * @return The of the command.
     */
    public String getType();

    /**
     * Returns the content from the command.
     * @return The content.
     */
    public HashMap<String, String> getContent();

    /**
     * Gets a value of the content from the command.
     * @param key The key for getting the content.
     * @return The value for the given key.
     */
    public String getString(String key);

}
