/*************************************************************************
 * Copyright (C) 2012 Philippe Leipold
 *
 * This file is part of FunCommands.
 *
 * FunCommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FunCommands is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FunCommands. If not, see <http://www.gnu.org/licenses/>.
 *
 **************************************************************************/

package de.Lathanael.FC.Commands;

import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Manager.Exceptions.PlayerNotFound;
import be.Balor.Manager.Permissions.ActionNotPermitedException;
import be.Balor.Tools.Utils;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Wound extends CoreCommand {

	public Wound() {
		super("fc_wound", "fun.wound", "FunCommands");
		other = true;
	}

	/* (non-Javadoc)
	 * @see be.Balor.Manager.Commands.CoreCommand#execute(org.bukkit.command.CommandSender, be.Balor.Manager.Commands.CommandArgs)
	 */
	@Override
	public void execute(CommandSender sender, CommandArgs args) throws PlayerNotFound, ActionNotPermitedException {
		Player target;
		target = Utils.getUserParam(sender, args, permNode);
		if (target == null)
			return;
		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("target", Utils.getPlayerName(target));
		target.setHealth(1);
		if (Utils.isPlayer(sender, false))
			replace.put("sender", Utils.getPlayerName((Player) sender));
		else
			replace.put("sender", "Server Admin");
		if (!target.equals(sender)) {
			Utils.sI18n(target, "woundTarget", replace);
			Utils.sI18n(sender, "woundSender", replace);
		} else {
			Utils.sI18n(sender, "woundYourself", replace);
		}
	}

	/* (non-Javadoc)
	 * @see be.Balor.Manager.Commands.CoreCommand#argsCheck(java.lang.String[])
	 */
	@Override
	public boolean argsCheck(String... args) {
		return args != null;
	}

}
