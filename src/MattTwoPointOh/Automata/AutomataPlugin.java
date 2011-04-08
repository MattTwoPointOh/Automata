package MattTwoPointOh.Automata;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftMinecart;
import org.bukkit.craftbukkit.entity.CraftStorageMinecart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.Server;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

/**
 * Automata for Bukkit
 *
 * @author MattTwoPointOh
 */
public class AutomataPlugin extends JavaPlugin {
    private final Automata automata = new Automata();
    private final AutomataPlayerListener playerListener = new AutomataPlayerListener(automata);
    private final AutomataBlockListener blockListener = new AutomataBlockListener(this);
    private final AutomataVehicleListener vehicleListener = new AutomataVehicleListener(automata);
    private final AutomataEntityListener entityListener = new AutomataEntityListener();

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
        //pm.registerEvent(Event.Type.VEHICLE_DESTROY, vehicleListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.VEHICLE_COLLISION_ENTITY, vehicleListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.VEHICLE_DAMAGE, vehicleListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.VEHICLE_MOVE, vehicleListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Priority.Normal, this);

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();

        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
        System.out.println("Suck my cock.");
    }

    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equals("clearcarts")) {
            if (!(sender instanceof Player)) return false;

            Player player = (Player) sender;

            for (Entity e: player.getWorld().getEntities()) {
                if (e instanceof CraftMinecart) {
                    automata.removeAutomaton(e);
                    e.remove();
                }
            }

            player.sendMessage(ChatColor.AQUA + "Removed all minecarts.");

            return true;
        }
        else if (label.equals("clearrails")) {
            if (!(sender instanceof Player)) return false;

            Player player = (Player) sender;

            System.out.println("Looking for block states.");
            Chunk playerChunk = player.getWorld().getChunkAt(player.getLocation());
            for (BlockState bs: playerChunk.getTileEntities()) {
                System.out.println("Block state: " + bs.getType());
            }
            //for (Chunk c: player.getWorld().getLoadedChunks()) {
//                c.
//            }
            return true;
        }

        return false;
    }
}

