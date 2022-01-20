package de.xxschrandxx.wsc.bukkit.listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.Statistic.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.xxschrandxx.wsc.bukkit.api.WSCLinkerEvent;

public class PlayerStatisticListener implements Listener {

    @EventHandler
    public void onCommandType(WSCLinkerEvent event) {
        if (!event.getType().equals("playerstatistik")) {
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
        // Test statistic
        if (event.getString("statistic") == null) {
            result.put("error", true);
            result.put("message", "No statistic given.");
        }
        else if (event.getString("statistic").isBlank()) {
            result.put("error", true);
            result.put("message", "Given statistic empty or blank.");
        }
        Statistic statistic = Statistic.valueOf(event.getString("statistic"));
        if (statistic == null) {
            result.put("error", true);
            result.put("message", "Given statistic cannot be found.");
        }
        if (result.isEmpty()) {
            OfflinePlayer offlinePlayer =  Bukkit.getOfflinePlayer(uuid);
            if (statistic.getType() == Type.BLOCK) {
                // Test block
                if (event.getString("block") == null) {
                    result.put("error", true);
                    result.put("message", "No block given.");
                }
                else if (event.getString("block").isBlank()) {
                    result.put("error", true);
                    result.put("message", "Given block empty or blank.");
                }
                Material block = Material.valueOf(event.getString("block"));
                if (block == null) {
                    result.put("error", true);
                    result.put("message", "Given block cannot be found.");
                }
                if (!block.isBlock()) {
                    result.put("error", true);
                    result.put("message", "Given material is not a block.");
                }
                if (result.isEmpty()) {
                    result.put("error", false);
                    result.put("message", offlinePlayer.getStatistic(statistic, block));
                }
            }
            else if (statistic.getType() == Type.ENTITY) {
                // Test entity
                if (event.getString("entity") == null) {
                    result.put("error", true);
                    result.put("message", "No entity given.");
                }
                else if (event.getString("entity").isBlank()) {
                    result.put("error", true);
                    result.put("message", "Given entity empty or blank.");
                }
                EntityType entity = EntityType.valueOf(event.getString("entity"));
                if (entity == null) {
                    result.put("error", true);
                    result.put("message", "Given entity cannot be found.");
                }
                if (result.isEmpty()) {
                    result.put("error", false);
                    result.put("message", offlinePlayer.getStatistic(statistic, entity));
                }
            }
            else if (statistic.getType() == Type.ITEM) {
                // Test item
                if (event.getString("item") == null) {
                    result.put("error", true);
                    result.put("message", "No item given.");
                }
                else if (event.getString("item").isBlank()) {
                    result.put("error", true);
                    result.put("message", "Given item empty or blank.");
                }
                Material item = Material.valueOf(event.getString("item"));
                if (item == null) {
                    result.put("error", true);
                    result.put("message", "Given item cannot be found.");
                }
                if (!item.isItem()) {
                    result.put("error", true);
                    result.put("message", "Given material is not an item.");
                }
                if (result.isEmpty()) {
                    result.put("error", false);
                    result.put("message", offlinePlayer.getStatistic(statistic, item));
                }
            }
   
        }
        event.sendResult(result);
    }

}
