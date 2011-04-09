package MattTwoPointOh.Automata;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

/**
 * Created by IntelliJ IDEA.
 * User: Matt
 * Date: 4/9/11
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class BlockFunction {
    public static void removeBlockAndDrop(Block block) {
        Material blockMaterial = block.getType();
        boolean shouldDrop = MaterialFunction.isDroppableMaterial(block.getType());

        block.setTypeIdAndData(Material.AIR.getId(), (byte)0, true);

        /* Dropping items in front of the cart confuses it
        if (shouldDrop) {
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(blockMaterial, 1));
        }
        */
    }
}
