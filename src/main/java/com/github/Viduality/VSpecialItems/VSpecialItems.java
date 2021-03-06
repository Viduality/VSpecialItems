package com.github.Viduality.VSpecialItems;

/*
 * VSpecialItems
 * Copyright (C) 2020, Viduality
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import com.github.Viduality.VSpecialItems.Items.ChangeBiomeItem;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class VSpecialItems extends JavaPlugin {

    private static VSpecialItems instance;

    public static NamespacedKey KEY;

    public static HashMap<String, ItemStack> specialItems;

    public void onEnable() {
        instance = this;

        KEY = new NamespacedKey(this, "VSpecialItem");
        specialItems = new HashMap<>();

        ConfigHandler.reloadAllConfigs();

        SpecialItemsCommand specialItemsCommand = new SpecialItemsCommand(this);
        getCommand("VSpecialItems").setExecutor(specialItemsCommand);

        final PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ChangeBiomeItem(), this);

        new ChangeBiomeItem().createItem();
    }

    public void onDisable() {
    }

    /**
     * Provides an instance of the plugin.
     * @return  instance of SpecialItems.
     */
    public static VSpecialItems getInstance() {
        return instance;
    }

    /**
     * Sends a message to a command sender.
     *
     * @param target  The target of the command.
     * @param message  The String from the lang config that should be used.
     */
    public void sendMessage(CommandSender target, String message) {
        ConfigHandler.messagefromString(target, message);
    }

    /**
     * Sends a message to a command sender.
     *
     * @param target  The target of the command.
     * @param message  The String from the lang config that should be used.
     * @param placeholder  The string in the config to be replaced. For example %placeholder%.
     * @param replacement  The replacement.
     */
    public void sendMessage(CommandSender target, String message, String placeholder, String replacement) {
        ConfigHandler.messagefromString(target, message, placeholder, replacement);
    }

}
