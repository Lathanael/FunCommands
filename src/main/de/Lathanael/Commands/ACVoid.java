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

package de.Lathanael.Commands;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Lathanael.FunCommands.FunCommands;
import de.Lathanael.Tools.BlocksOld;
import de.Lathanael.Tools.Utilities;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Tools.Utils;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class ACVoid extends CoreCommand {

	/**
	 *
	 */
	public ACVoid() {
		super("ac_void", "admincmd.fun.void", "FunCommands");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * be.Balor.Manager.ACCommands#execute(org.bukkit.command.CommandSender,
	 * java.lang.String[])
	 */
	@Override
	public void execute(CommandSender sender, CommandArgs args) {
		Player target;
		target = Utils.getUser(sender, args, permNode, 0, true);
		if (target == null)
			return;

		Material mat = null;
		BlocksOld states = new BlocksOld();
		mat = Material.AIR;

		FunCommands.players.add(target);
		Location loc = target.getLocation();
		for (int i = loc.getBlock().getY(); i >= -128; --i) {
			Utilities.changeBlock(sender, loc, mat, states, 0, i, 0);
			Utilities.changeBlock(sender, loc, mat, states, 1, i, 0);
			Utilities.changeBlock(sender, loc, mat, states, 0, i, 1);
			Utilities.changeBlock(sender, loc, mat, states, 1, i, 1);
			Utilities.changeBlock(sender, loc, mat, states, -1, i, 0);
			Utilities.changeBlock(sender, loc, mat, states, 0, i, -1);
			Utilities.changeBlock(sender, loc, mat, states, -1, i, 1);
			Utilities.changeBlock(sender, loc, mat, states, 1, i, -1);
			Utilities.changeBlock(sender, loc, mat, states, -1, i, -1);
		}
		FunCommands.blockStates.put(target, states);
		FunCommands.players.add(target);
		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("target", target.getName());
		if (Utils.isPlayer(sender, false))
			replace.put("sender", sender.getName());
		else
			replace.put("sender", "Server Admin");
		if (!target.equals(sender)) {
			Utils.sI18n(target, "voidTarget", replace);
			Utils.sI18n(sender, "voidSender", replace);
		} else {
			Utils.sI18n(sender, "voidYourself", replace);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see be.Balor.Manager.ACCommands#argsCheck(java.lang.String[])
	 */
	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 1;
	}
}
