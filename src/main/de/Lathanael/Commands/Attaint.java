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

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Tools.Utils;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Attaint extends CoreCommand {

	public Attaint() {
		super("ac_attaint", "admincmd.fun.attaint", "FunCommands");
	}

	@Override
	public void execute(CommandSender sender, CommandArgs args) {
		Player target;

		target = Utils.getUser(sender, args, permNode, 1, true);
		if (target == null)
			return;
		if (!Utils.checkImmunity(sender, args, 0))
			return;
		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("target", target.getName());
		replace.put("name", args.getString(2));
		if (Utils.isPlayer(sender, false))
			replace.put("sender", sender.getName());
		else
			replace.put("sender", "Server Admin");

		target.setDisplayName(args.getString(2));
		if (!target.equals(sender)) {
			Utils.sI18n(target, "attaintTarget", replace);
			Utils.sI18n(sender, "attaintSender", replace);
		} else {
			Utils.sI18n(sender, "attaintYourself", replace);
		}
	}


	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 2;
	}

}
