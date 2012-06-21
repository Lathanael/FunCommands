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

package de.Lathanael.FC.Commands;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Lathanael.FC.Tools.BlocksOld;
import de.Lathanael.FC.Tools.Utilities;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Manager.Exceptions.PlayerNotFound;
import be.Balor.Manager.Permissions.ActionNotPermitedException;
import be.Balor.Tools.Utils;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class FCVoid extends CoreCommand {

	/**
	 *
	 */
	public FCVoid() {
		super("fc_void", "fun.void", "FunCommands");
		other = true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * be.Balor.Manager.ACCommands#execute(org.bukkit.command.CommandSender,
	 * java.lang.String[])
	 */
	@Override
	public void execute(CommandSender sender, CommandArgs args) throws PlayerNotFound, ActionNotPermitedException {
		Player target;
		target = Utils.getUser(sender, args, permNode, 0, true);
		if (target == null)
			return;

		Material mat = null;
		BlocksOld states = new BlocksOld();
		mat = Material.AIR;
		Location loc = target.getLocation();
		Utilities.createVoid(sender, target, loc, mat, states, -150);
		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("target", Utils.getPlayerName(target));
		if (Utils.isPlayer(sender, false))
			replace.put("sender", Utils.getPlayerName((Player) sender));
		else
			replace.put("sender", "Server Admin");
		if (!target.equals(sender)) {
			Utils.sI18n(target, "voidTarget", replace);
			Utils.sI18n(sender, "voidSender", replace);
		} else {
			Utils.sI18n(sender, "voidYourself", replace);
		}
		Utilities.undoVoid(target);
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