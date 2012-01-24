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

package de.Lathanael.FC.Tools;

import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet29DestroyEntity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import be.Balor.bukkit.AdminCmd.ACPluginManager;


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

	/**
	 * Creates a new Player and destroys the old one'S shell.
	 *
	 * @param target
	 * @param playerName
	 */
	public static void createNewPlayerShell(Player target, String playerName) {
		Packet29DestroyEntity destroy = new Packet29DestroyEntity(target.getEntityId());
		Packet20NamedEntitySpawn create = Utilities.createNewPlayerPacket(target, playerName);
		for (Player update : ACPluginManager.getServer().getOnlinePlayers()) {
			if(!update.getWorld().equals(target.getWorld())) {
				continue;
			}
			if (update.equals(target)) {
				continue;
			}
			((CraftPlayer) update).getHandle().netServerHandler.sendPacket(destroy);
			((CraftPlayer) update).getHandle().netServerHandler.sendPacket(create);
		}
	}

	/**
	 * Creates a new Player-shell with another name above the head at the location the old player was!
	 *
	 * @param player The Player to be "replaced"
	 * @param playerName The new name to be displayed
	 * @return
	 */
	private static Packet20NamedEntitySpawn createNewPlayerPacket(Player player, String playerName) {
		Packet20NamedEntitySpawn packet = new Packet20NamedEntitySpawn();
		Location loc = player.getLocation();
		packet.a = player.getEntityId();
		packet.b = playerName;
		packet.c = floor_double(loc.getX() *32D);
		packet.d = floor_double(loc.getY() *32D);
		packet.e = floor_double(loc.getZ() * 32D);
		packet.f = (byte)(int)((loc.getYaw() * 256F) / 360F);
		packet.g = (byte)(int)((loc.getPitch() * 256F) / 360F);
		packet.h = player.getItemInHand().getTypeId();
		return packet;
	}

	/**
	 * Helper function for createNewPlayer
	 *
	 * @param a
	 * @return
	 */
	private static int floor_double(double a) {
		int b = (int)a;
		return a >= (double)b ? b : b - 1;
	}
}
