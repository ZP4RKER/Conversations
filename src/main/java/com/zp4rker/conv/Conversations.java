package com.zp4rker.conv;

import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Conversations extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                openGUI(event.getPlayer());
            }
        });
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (event.getInventory().getName().equalsIgnoreCase("GUI")) {
            if (event.getCurrentItem().getType() == Material.COMMAND) {
                ConversationFactory cf = new ConversationFactory(this);
                Conversation conv = cf.withFirstPrompt(new StringInput(event.getClickedInventory()))
                        .withLocalEcho(true)
                        .buildConversation(((Player) event.getWhoClicked()));
                conv.begin();
            }
        }
    }

    public void openGUI(Player player) {
        Inventory inventory = this.getServer().createInventory(null, 9, "GUI");
        inventory.addItem(new ItemStack(Material.COMMAND));
        player.openInventory(inventory);
    }

}
