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
import org.bukkit.block.BlockState;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.Balor.Manager.LocaleManager;
import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Manager.Exceptions.ActionNotPermitedException;
import be.Balor.Manager.Exceptions.PlayerNotFound;
import be.Balor.Tools.MaterialContainer;
import be.Balor.Tools.CommandUtils.Users;
import be.Balor.bukkit.AdminCmd.ACHelper;
import be.Balor.bukkit.AdminCmd.LocaleHelper;
import de.Lathanael.FC.FunCommands.FunCommands;
import de.Lathanael.FC.Tools.BlocksOld;
import de.Lathanael.FC.Tools.Utilities;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Entomb extends CoreCommand {

	/**
	 *
	 */
	public Entomb() {
		super("fc_entomb", "fun.entomb", "FunCommands");
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
		target = Users.getUser(sender, args, permNode, 0, true);
		if (target == null)
			return;

		BlocksOld states = new BlocksOld();
		MaterialContainer mat = null;
		mat = ACHelper.getInstance().checkMaterial(sender, "web");
		if (args.hasFlag('u')) {
			undoEntomb(FunCommands.blockStates.get(target));
			return;
		}
		if (args.length >= 2)
			mat = ACHelper.getInstance().checkMaterial(sender, args.getString(1));
		if (mat.isNull())
			return;
		if (!mat.getMaterial().isBlock()) {
			LocaleHelper.NOT_A_BLOCK.sendLocale(sender);
			return;
		}
		Location loc = target.getLocation();
		Utilities.entomb(sender, target, loc, mat.getMaterial(), states);
		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("target", Users.getPlayerName(target));
		if (Users.isPlayer(sender, false))
			replace.put("sender", Users.getPlayerName((Player) sender));
		else
			replace.put("sender", "Server Admin");
		if (!target.equals(sender)) {
			LocaleManager.sI18n(target, "entombTarget", replace);
			LocaleManager.sI18n(sender, "entombSender", replace);
		} else {
			LocaleManager.sI18n(sender, "entombYourself", replace);
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

	private void undoEntomb (BlocksOld states) {
		if (states == null)
			return;
		for (BlockState state : states.getStates())
			state.update(true);
	}
}
