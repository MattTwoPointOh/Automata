package MattTwoPointOh.Automata;

import org.bukkit.Material;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Matt
 * Date: 4/9/11
 * Time: 8:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class MaterialFunction {
    private static HashSet<Material> solidMaterialSet = new HashSet<Material>();
    private static Material[] solidMaterials = new Material[] {
            Material.STONE,
            Material.GRASS,
            Material.DIRT,
            Material.COBBLESTONE,
            Material.WOOD,
            Material.BEDROCK,
            Material.SAND,
            Material.GRAVEL,
            Material.GOLD_ORE,
            Material.IRON_ORE,
            Material.COAL_ORE,
            Material.LOG,
            Material.SPONGE,
            Material.GLASS,
            Material.LAPIS_ORE,
            Material.LAPIS_BLOCK,
            Material.SANDSTONE,
            Material.WOOL,
            Material.GOLD_BLOCK,
            Material.IRON_BLOCK,
            Material.BRICK,
            Material.TNT,
            Material.MOSSY_COBBLESTONE,
            Material.OBSIDIAN,
            Material.DIAMOND_ORE,
            Material.DIAMOND_BLOCK,
            Material.WORKBENCH,
            Material.SOIL,
            Material.FURNACE,
            Material.BURNING_FURNACE,
            Material.REDSTONE_ORE,
            Material.GLOWING_REDSTONE_ORE,
            Material.SNOW,
            Material.ICE,
            Material.SNOW_BLOCK,
            Material.CACTUS,
            Material.CLAY,
            Material.PUMPKIN,
            Material.NETHERRACK,
            Material.SOUL_SAND,
            Material.GLOWSTONE,
            Material.JACK_O_LANTERN
    };

    private static HashSet<Material> nonDroppableMaterialSet = new HashSet<Material>();
    private static Material[] nonDroppableMaterials = new Material[] {
            Material.AIR,
            Material.BEDROCK,
            Material.WATER,
            Material.STATIONARY_WATER,
            Material.LAVA,
            Material.STATIONARY_LAVA,
            Material.LEAVES,
            Material.FIRE,
            Material.PORTAL
    };

    static {
        for (Material m: solidMaterials)
            solidMaterialSet.add(m);

        for (Material m: nonDroppableMaterials)
            nonDroppableMaterialSet.add(m);
    }

    public static boolean isSolidMaterial(Material material) {
        return solidMaterialSet.contains(material);
    }

    public static boolean isDroppableMaterial(Material material) {
        return !nonDroppableMaterialSet.contains(material);
    }
}
