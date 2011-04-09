package MattTwoPointOh.Automata;

import org.bukkit.block.BlockFace;

/**
 * Created by IntelliJ IDEA.
 * User: Matt
 * Date: 4/8/11
 * Time: 7:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocationFunction {

    public static float getCorrectedYaw(float yaw) {
		float angle = (yaw - 90) % 360;
		if (angle < 0) angle += 360.0F;
		return angle;
    }

    public static BlockFace getBlockFace(float yaw) {
        int correctedYaw = (int) getCorrectedYaw(yaw);
        //I'm not very confident about adding 90 degrees here... revisit this.
        correctedYaw += 90;

        if (correctedYaw < 23) return BlockFace.NORTH;
        if (correctedYaw < 68) return BlockFace.NORTH_EAST;
		if (correctedYaw < 113) return BlockFace.EAST;
		if (correctedYaw < 158) return BlockFace.SOUTH_EAST;
		if (correctedYaw < 203) return BlockFace.SOUTH;
		if (correctedYaw < 248) return BlockFace.SOUTH_WEST;
		if (correctedYaw < 293) return BlockFace.WEST;
		if (correctedYaw < 338) return BlockFace.NORTH_WEST;
		return BlockFace.NORTH;
    }

    public static float getRoundedYaw(float yaw, float directions) {
        float correctedYaw = getCorrectedYaw(yaw);
        float slices = 360.0F / directions;

        correctedYaw /= slices;
        correctedYaw = Math.round(correctedYaw);
        correctedYaw *= slices;

        return correctedYaw + 90.0F;
    }

}
