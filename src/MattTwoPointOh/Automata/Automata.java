package MattTwoPointOh.Automata;

import org.bukkit.entity.Entity;
import org.bukkit.entity.StorageMinecart;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Matt
 * Date: 4/7/11
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Automata {
    private HashMap<Integer, Automaton> automatonCollection = new HashMap<Integer, Automaton>();

    public Automaton addAutomaton(StorageMinecart entity) {
        System.out.println("Added automaton #" + entity.getEntityId());
        Automaton automaton = new Automaton(entity);
        automatonCollection.put(entity.getEntityId(), automaton);
        return automaton;
    }

    public void removeAutomaton(Entity entity) {
        if (automatonCollection.remove(entity.getEntityId()) != null) {
            System.out.println("Removed automaton #" + entity.getEntityId());
        }
    }

    public boolean isAutomaton(Entity entity) {
        return automatonCollection.containsKey(entity.getEntityId());
    }

    public Automaton getAutomaton(Entity entity) {
        return automatonCollection.get(entity.getEntityId());
    }
}
