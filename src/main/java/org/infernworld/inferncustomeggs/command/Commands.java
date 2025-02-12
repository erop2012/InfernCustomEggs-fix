package org.infernworld.inferncustomeggs.command;

import lombok.val;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.infernworld.inferncustomeggs.InfernCustomEggs;
import org.infernworld.inferncustomeggs.gui.Menu;
import org.infernworld.inferncustomeggs.item.Item;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {
    private final InfernCustomEggs plugin;
    public Commands(InfernCustomEggs plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        val player = (Player) sender;
            if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
                if (player.hasPermission("inferncustomeggs.give")) {
                    Item items = new Item(plugin);
                    ItemStack item = items.items();
                    int amount;
                    try {
                        amount = Integer.parseInt(args[2]);
                        item.setAmount(amount);
                        player.getInventory().addItem(item);
                    } catch (NumberFormatException e) {
                        player.sendMessage("неверный симвло!");
                    }
                    return true;
                }
            } else {
                player.openInventory(new Menu(plugin).getInventory());
                return true;
            }
        return false;
    }
}
