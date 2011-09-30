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

package de.Lathanael.FunCommands;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Tools.MaterialContainer;
import be.Balor.Tools.Utils;
import be.Balor.bukkit.AdminCmd.ACHelper;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class ACVoid extends CoreCommand {

	public ACVoid() {
		super("ac_void", "admincmd.fun.void", "FunCommands");
	}

	@Override
	public void execute(CommandSender sender, CommandArgs args) {
		Player target;
		MaterialContainer mat = null;
		BlocksOld states = new BlocksOld();

		mat = ACHelper.getInstance().checkMaterial(sender, "air");
		target = Utils.getUser(sender, args, permNode, 1, true);
		if (target == null)
			return;
		FunCommands.players.add(target);
		Location loc = target.getLocation();
		int y = loc.getBlock().getY();
		for (; y >= 0; y--) {
			Utilities.changeBlock(sender, loc, mat.getMaterial(), states, 0, y, 0);
			Utilities.changeBlock(sender, loc, mat.getMaterial(), states, 1, y, 0);
			Utilities.changeBlock(sender, loc, mat.getMaterial(), states, 0, y, 1);
			Utilities.changeBlock(sender, loc, mat.getMaterial(), states, 1, y, 1);
			Utilities.changeBlock(sender, loc, mat.getMaterial(), states, -1, y, 0);
			Utilities.changeBlock(sender, loc, mat.getMaterial(), states, 0, y, -1);
			Utilities.changeBlock(sender, loc, mat.getMaterial(), states, -1, y, 1);
			Utilities.changeBlock(sender, loc, mat.getMaterial(), states, 1, y, -1);
			Utilities.changeBlock(sender, loc, mat.getMaterial(), states, -1, y, -1);
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


	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 1;
	}
}
