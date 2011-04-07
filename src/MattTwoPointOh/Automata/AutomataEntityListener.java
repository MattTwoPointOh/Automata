package MattTwoPointOh.Automata;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

/**
 * Created by IntelliJ IDEA.
 * User: Matt
 * Date: 4/7/11
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class AutomataEntityListener extends EntityListener {
    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        System.out.println("Entity death!");
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        System.out.println("Entity damage.");
    }
}
