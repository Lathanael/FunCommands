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
import java.util.Random;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Tools.Utils;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Slap extends CoreCommand {

	public Slap() {
		super("ac_slap", "admincmd.fun.slap", "FunCommands");
	}

	@Override
	public void execute(CommandSender sender, CommandArgs args) {
		Random random = new Random();
		random.nextInt(10);
		Player target;

		target = Utils.getUser(sender, args, permNode, 0, true);
		if (target == null)
			return;

		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("target", target.getName());
		if (Utils.isPlayer(sender, false))
			replace.put("sender", sender.getName());
		else
			replace.put("sender", "Server Admin");
		Vector direction = target.getLocation().getDirection();
		sender.sendMessage(direction.toString());
		if (args.hasFlag('h'))
			target.setVelocity(new Vector(0, 0, 0));
		else if (args.hasFlag('v'))
			target.setVelocity(new Vector(0, 5, 0));
		else
			target.setVelocity(target.getVelocity());

		if (!target.equals(sender)) {
			Utils.sI18n(target, "slapTarget", replace);
			Utils.sI18n(sender, "slapSender", replace);
		} else {
			Utils.sI18n(sender, "slapYourself");
		}
	}


	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 1;
	}
}
