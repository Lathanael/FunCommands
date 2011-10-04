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
import org.bukkit.util.Vector;

import de.Lathanael.FunCommands.Configuration;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Tools.Utils;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Rocket extends CoreCommand {

	/**
	 *
	 */
	public Rocket() {
		super("ac_rocket", "admincmd.fun.rocket", "FunCommands");
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
	public void execute(CommandSender sender, CommandArgs args) {
		Player target;
		target = Utils.getUser(sender, args, permNode, 0, true);
		if (target == null)
			return;
		float power = 0;
		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("target", target.getName());
		if (Utils.isPlayer(sender, false))
			replace.put("sender", sender.getName());
		else
			replace.put("sender", "Server Admin");
		if (args.hasFlag('h')) {
			power = Configuration.getInstance().getConfFloat("Rocket.flagPower");
			if (power > 10) {
				power = 10;
				Configuration.getInstance().setConfProperty("Rocket.flagPower", 10);
			}
			target.setVelocity(new Vector(0, power, 0));
		}
		else {
			power = Configuration.getInstance().getConfFloat("Rocket.normalPower");
			if (power > 10) {
				power = 10;
				Configuration.getInstance().setConfProperty("Rocket.normalPower", 10);
			}
			target.setVelocity(new Vector(0, power, 0));
		}

		if (!target.equals(sender)) {
			Utils.sI18n(target, "rocketTarget", replace);
			Utils.sI18n(sender, "rocketSender", replace);
		} else {
			Utils.sI18n(sender, "rocketYourself");
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
