package MattTwoPointOh.Automata;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

/**
 * Handle events for all Player related events
 * @author MattTwoPointOh
 */
public class AutomataPlayerListener extends PlayerListener {
    private final Automata automata;

    public AutomataPlayerListener(Automata automata) {
        this.automata = automata;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        /*
         * Here we allow minecarts to be placed on regular surfaces.
         * If placed on a regular surface, we assume then that it's an automaton.
         */

        //only on a right click
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!event.hasItem()) return;
        ItemStack item = event.getItem();
        //only using a storage minecart)
        if (item.getType() != Material.STORAGE_MINECART) return;
        //don't handle being placed on a rail
        if (event.getClickedBlock().getTypeId() == 66) return;
        //can't place anywhere but on top of a block
        if (event.getBlockFace() != BlockFace.UP) return;

        Block targetBlock = event.getClickedBlock().getRelative(BlockFace.UP);
        Location spawnLocation = targetBlock.getLocation().clone();
        spawnLocation.setX(spawnLocation.getX() + 0.5d);
        spawnLocation.setZ(spawnLocation.getZ() + 0.5d);
        spawnLocation.setY(spawnLocation.getY() + 0.5d);

        spawnLocation.setYaw(LocationFunction.getRoundedYaw(event.getPlayer().getLocation().getYaw(), 4) + 90);
        StorageMinecart minecart = targetBlock.getWorld().spawnStorageMinecart(spawnLocation);
        System.out.println("Cart yaw after spawn: " + minecart.getLocation().getYaw());
        minecart.getLocation().setYaw(LocationFunction.getRoundedYaw(event.getPlayer().getLocation().getYaw(), 4));
        minecart.teleport(spawnLocation);

        Automaton automaton = automata.addAutomaton(minecart);
        automaton.setLastTouchedPlayer(event.getPlayer());

        //If we still have items remaining, we can decrement the amount
        //Otherwise we have to hack it a bit and remove the player's item
        int resultingAmount = item.getAmount() - 1;
        if (resultingAmount > 0) {
             item.setAmount(resultingAmount);
        }
        else {
            event.getPlayer().setItemInHand(null);
        }
    }
}

