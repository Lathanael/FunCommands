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

import java.util.Random;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

		target = Utils.getUser(sender, args, permNode, 1, true);
		if (target == null)
			return;
	}


	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 1;
	}
}
