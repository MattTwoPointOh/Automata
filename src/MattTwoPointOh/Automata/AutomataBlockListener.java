package MattTwoPointOh.Automata;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;

/**
 * Automata block listener
 * @author MattTwoPointOh
 */
public class AutomataBlockListener extends BlockListener {
    private final Automata plugin;

    public AutomataBlockListener(final Automata plugin) {
        this.plugin = plugin;
    }

    //put all Block related code here
}
