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
import java.util.Random;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import de.Lathanael.FC.FunCommands.FCConfigEnum;
import de.Lathanael.FC.FunCommands.FunCommands;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Manager.Exceptions.PlayerNotFound;
import be.Balor.Manager.Permissions.ActionNotPermitedException;
import be.Balor.Tools.Utils;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Slap extends CoreCommand {

	/**
	 *
	 */
	public Slap() {
		super("fc_slap", "fun.slap", "FunCommands");
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
		Random random = new Random();
		random.nextInt(10);
		Player target;

		target = Utils.getUser(sender, args, permNode, 0, true);
		if (target == null)
			return;

		float power = 0;
		float height = 0;
		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("target", Utils.getPlayerName(target));
		if (Utils.isPlayer(sender, false))
			replace.put("sender", Utils.getPlayerName((Player) sender));
		else
			replace.put("sender", "Server Admin");
		Vector direction = target.getLocation().getDirection();
		if (args.hasFlag('h')) {
			power = FCConfigEnum.SLAP_HP.getFloat();
			height = FCConfigEnum.SLAP_HH.getFloat();
			if (power > 10) {
				power = 10;
				FunCommands.getConfigEnum().set("Slap.hPower", 10.0F);
			}
			if (height > 10) {
				height = 10;
				FunCommands.getConfigEnum().set("Slap.hHeight", 10.0F);
			}
			direction = new Vector(direction.getX()*power, height, direction.getZ()*power);
			target.setVelocity(direction);
		}
		else if (args.hasFlag('v')) {
			power = FCConfigEnum.SLAP_VP.getFloat();
			height = FCConfigEnum.SLAP_VH.getFloat();
			if (power > 10) {
				power = 10;
				FunCommands.getConfigEnum().set("Slap.vPower", 10.0F);
			}
			if (height > 10) {
				height = 10;
				FunCommands.getConfigEnum().set("Slap.vHeight", 10.0F);
			}
			direction = new Vector(direction.getX()*power, height, direction.getZ()*power);
			target.setVelocity(direction);
		}
		else {
			power = FCConfigEnum.SLAP_NP.getFloat();
			height = FCConfigEnum.SLAP_NH.getFloat();
			if (power > 10) {
				power = 10;
				FunCommands.getConfigEnum().set("Slap.normalPower", 10.0F);
			}
			if (height > 10) {
				height = 10;
				FunCommands.getConfigEnum().set("Slap.normalHeight", 10.0F);
			}
			direction = new Vector(direction.getX()*power, height, direction.getZ()*power);
			target.setVelocity(direction);
		}

		if (!target.equals(sender)) {
			Utils.sI18n(target, "slapTarget", replace);
			Utils.sI18n(sender, "slapSender", replace);
		} else {
			Utils.sI18n(sender, "slapYourself");
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
