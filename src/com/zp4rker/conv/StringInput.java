package com.zp4rker.conv;

import org.bukkit.Material;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StringInput extends StringPrompt {
    private Inventory inventory;

    public StringInput(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return "Display name?";
    }

    @Override
    public Prompt acceptInput(ConversationContext conversationContext, String s) {
        ItemStack itemStack = new ItemStack(Material.COMMAND);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(s);
        itemStack.setItemMeta(meta);
        inventory.setItem(0, itemStack);
        ((Player) conversationContext.getForWhom()).openInventory(inventory);
        return null;
    }

}
