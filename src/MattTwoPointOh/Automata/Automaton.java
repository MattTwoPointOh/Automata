package MattTwoPointOh.Automata;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Matt
 * Date: 4/7/11
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Automaton {
    private StorageMinecart entity;
    private boolean isActive = false;
    private BlockFace direction;
    private Vector vector;
    private final float speed = 0.1F;

    public Automaton(StorageMinecart minecart)
    {
        entity = minecart;
    }

    public void toggleActive() {
        isActive = !isActive;

        if (isActive) {
            direction = LocationFunction.getBlockFace(entity.getLocation().getYaw());
            float x = 0;
            float z = 0;

            if (direction == BlockFace.NORTH)
                x = -speed;
            else if (direction == BlockFace.SOUTH)
                x = speed;

            if (direction == BlockFace.EAST)
                z = speed;
            else if (direction == BlockFace.WEST)
                z = -speed;

            vector = new Vector(x, 0, z);
        }

        applyVelocity();
        //float yaw = entity.getLocation().getYaw();
        //if (yaw >= 0 && yaw < )

        //entity.getLocation().getBlock().getRelative()
    }

    public void onMoved(VehicleMoveEvent event) {
        applyVelocity();

        //System.out.println("Vehicle velocity: " + event.getVehicle().getVelocity());
        Block fromBlock = event.getFrom().getBlock();
        Block toBlock = event.getTo().getBlock();
        if (fromBlock == toBlock) return;
        fromBlock.setType(Material.RAILS);

        Block aheadBlock = toBlock.getRelative(direction);
        makePassagewaySuitable(aheadBlock);
        makePassagewaySuitable(aheadBlock.getRelative(direction));
    }

    private void makePassagewaySuitable(Block block) {
        block.setType(Material.AIR);
        block.getRelative(BlockFace.UP).setType(Material.AIR);

        Block floorBlock = block.getRelative(BlockFace.DOWN);
        if (floorBlock.getType() == Material.AIR)
            floorBlock.setType(Material.DIRT);
    }

    private void applyVelocity() {
        if (isActive) {
            entity.setVelocity(vector);
        }
        else {
            entity.setVelocity(new Vector());
        }
    }

}
