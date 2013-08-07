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

import be.Balor.Manager.LocaleManager;
import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Manager.Exceptions.ActionNotPermitedException;
import be.Balor.Manager.Exceptions.PlayerNotFound;
import be.Balor.Tools.CommandUtils.Users;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Sacrifice extends CoreCommand {

	public Sacrifice() {
		super("fc_sacrifice", "fun.sacrifice", "FunCommands");
		other = true;
	}

	/* (non-Javadoc)
	 * @see be.Balor.Manager.Commands.CoreCommand#execute(org.bukkit.command.CommandSender, be.Balor.Manager.Commands.CommandArgs)
	 */
	@Override
	public void execute(CommandSender sender, CommandArgs args) throws PlayerNotFound, ActionNotPermitedException {
		Player target;
		target = Users.getUserParam(sender, args, permNode);
		if (target == null)
			return;
		target.setHealth(0);
		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("target", Users.getPlayerName(target));
		if (Users.isPlayer(sender, false))
			replace.put("sender", Users.getPlayerName((Player) sender));
		else
			replace.put("sender", "Server Admin");
		if (!target.equals(sender)) {
			LocaleManager.sI18n(target, "sacrificeTarget", replace);
			LocaleManager.sI18n(sender, "sacrificeSender", replace);
		} else {
			LocaleManager.sI18n(sender, "sacrificeYourself", replace);
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
