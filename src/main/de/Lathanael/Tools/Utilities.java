/************************************************************************
 * This file is part of FunCommands.
 *
 * FunCommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ExamplePlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FunCommands.  If not, see <http://www.gnu.org/licenses/>.
 ************************************************************************/

package de.Lathanael.Tools;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;


/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Utilities {

	/**
	 * Change the block at a location(+shift) into a given material
	 *
	 * @author Lathanael
	 * @param loc
	 *             The location of the block to be changed
	 * @param mat
	 *             The material the block should be changed to
	 * @param x
	 *             Shift in x direction to modify the location
	 * @param y
	 *             Shift in y direction to modify the location
	 * @param z
	 *             Shift in z direction to modify the location
	 * @param states
	 *             The states list where the old BlockState will be saved in.
	 */
	public static void changeBlock (CommandSender sender, Location loc, Material mat, BlocksOld states, int x, int y, int z) {
		Block block = null;
		block = loc.getBlock().getRelative(x, y, z);
		if (block == null) {
			sender.sendMessage("Invalid location!");
			return;
		}
		states.addBlock(block);
		block.setType(mat);
	}
}
