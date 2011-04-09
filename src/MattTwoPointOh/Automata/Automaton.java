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
    private final float speed = 0.1F;

    private BlockFace direction;
    private BlockFace directionToTheLeft;
    private byte torchToTheLeftData;
    private Vector vector;
    private int enclosedCount = 0;

    public Automaton(StorageMinecart minecart)
    {
        entity = minecart;
    }

    public void toggleActive() {
        isActive = !isActive;

        if (isActive)
            initRunningVariables();

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

        Block hallwayLeftBlock = fromBlock.getRelative(directionToTheLeft).getRelative(BlockFace.UP);
        if (hallwayLeftBlock.getType() == Material.AIR)
            enclosedCount = 0;
        else
            enclosedCount++;

        if (enclosedCount == 8) {
            enclosedCount = 0;

            Block torchBlock = fromBlock.getRelative(BlockFace.UP);
            torchBlock.setType(Material.TORCH);
            torchBlock.setData(torchToTheLeftData);
        }

        fromBlock.setType(Material.RAILS);

        Block aheadBlock = toBlock.getRelative(direction);
        makePassagewaySuitable(aheadBlock);
        makePassagewaySuitable(aheadBlock.getRelative(direction));
    }

    private void makePassagewaySuitable(Block block) {
        Block ceilingBlock = block.getRelative(BlockFace.UP).getRelative(BlockFace.UP);
        if (ceilingBlock.getType() == Material.SAND || ceilingBlock.getType() == Material.GRAVEL)
            ceilingBlock.setType(Material.DIRT);

        block.setType(Material.AIR);
        block.getRelative(BlockFace.UP).setType(Material.AIR);


        Block floorBlock = block.getRelative(BlockFace.DOWN);
        //if (floorBlock.getType() == Material.AIR)
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

    private void initRunningVariables() {
            //We get the face given the yaw, but the cart moves the opposite direction so we get the opposing face
            direction = LocationFunction.getBlockFace(entity.getLocation().getYaw()).getOppositeFace();
            float x = 0;
            float z = 0;

            if (direction == BlockFace.SOUTH)
                x = speed;
            else if (direction == BlockFace.NORTH)
                x = -speed;

            if (direction == BlockFace.WEST)
                z = speed;
            else if (direction == BlockFace.EAST)
                z = -speed;

            if (direction == BlockFace.NORTH) {
                directionToTheLeft = BlockFace.WEST;
                torchToTheLeftData = 0x4;
            }
            else if (direction == BlockFace.WEST) {
                directionToTheLeft = BlockFace.SOUTH;
                torchToTheLeftData = 0x2;
            }
            else if (direction == BlockFace.SOUTH) {
                directionToTheLeft = BlockFace.EAST;
                torchToTheLeftData = 0x3;
            }
            else if (direction == BlockFace.EAST) {
                directionToTheLeft = BlockFace.NORTH;
                torchToTheLeftData = 0x1;
            }

            vector = new Vector(x, 0, z);
    }

    private void resetRunningVariables() {
        direction = null;
        vector = null;
        enclosedCount = 0;
    }
}
