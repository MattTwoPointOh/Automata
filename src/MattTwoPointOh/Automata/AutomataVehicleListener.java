package MattTwoPointOh.Automata;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.*;

/**
 * Created by IntelliJ IDEA.
 * User: Matt
 * Date: 4/7/11
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class AutomataVehicleListener extends VehicleListener {
    private Automata automata = new Automata();

    public AutomataVehicleListener(Automata automata) {
        this.automata = automata;
    }

    @Override
    public void onVehicleEntityCollision(VehicleEntityCollisionEvent event) {
        if (automata.isAutomaton(event.getVehicle()) || automata.isAutomaton(event.getEntity())) {
            //Not 100% sure what all of these do, but I saw Minecart Mania do them!
            event.setCancelled(true);
            event.setCollisionCancelled(true);
            event.setPickupCancelled(true);
        }
    }

    @Override
    public void onVehicleDamage(VehicleDamageEvent event) {
        if (event.getAttacker() instanceof Player) {
            Player player = (Player) event.getAttacker();
            if (player.getItemInHand() == null) return;
            if (player.getItemInHand().getType() != Material.REDSTONE_TORCH_ON) return;
            Automaton automaton = automata.getAutomaton(event.getVehicle());
            if (automaton == null) return;

            event.setCancelled(true);
            event.setDamage(0);

            automaton.setLastTouchedPlayer(player);
            automaton.toggleActive();
        }
    }

    @Override
    public void onVehicleMove(VehicleMoveEvent event) {
        Automaton automaton = automata.getAutomaton(event.getVehicle());
        if (automaton == null) return;
        automaton.onMoved(event);
    }

    @Override
    public void onVehicleDestroy(VehicleDestroyEvent event) {
        automata.removeAutomaton(event.getVehicle());
    }
}
