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

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import de.Lathanael.FC.FunCommands.FunCommands;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Manager.Exceptions.PlayerNotFound;
import be.Balor.Manager.Permissions.ActionNotPermitedException;
import be.Balor.Manager.Permissions.PermChild;
import be.Balor.Manager.Permissions.PermParent;
import be.Balor.Manager.Permissions.PermissionManager;
import be.Balor.Tools.Utils;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Barrage extends CoreCommand {

	private PermChild fire, arrow;

	public Barrage() {
		super("fc_barrage", "fun.barrage", "FunCommands");
		other = true;
		arrow = new PermChild(permNode + ".arrow");
		fire = new PermChild(permNode + ".fire");
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
		if (args.getString(0).equalsIgnoreCase("arrow") && PermissionManager.hasPerm(sender, "fun.barrage.arrow")) {
			Location loc = target.getLocation();
			Location loc2 = new Location(target.getWorld(), loc.getX(), loc.getY()+4, loc.getZ());
			for (int i = 0; i < 20; i++)
				target.getWorld().spawnArrow(loc2, new Vector(0, -1, 0), 1F, 10F);

			if (Utils.isPlayer(sender, false))
				replace.put("sender", Utils.getPlayerName((Player) sender));
			else
				replace.put("sender", "Server Admin");
			if (!target.equals(sender)) {
				Utils.sI18n(target, "arrowTarget", replace);
				Utils.sI18n(sender, "arrowSender", replace);
			} else {
				Utils.sI18n(sender, "arrowYourself", replace);
			}
		} else if (args.getString(0).equalsIgnoreCase("fire") && PermissionManager.hasPerm(sender, "fun.barrage.fire")) {
			target.setFireTicks(100);
			FunCommands.onFire.add(target);

			if (Utils.isPlayer(sender, false))
				replace.put("sender", Utils.getPlayerName((Player) sender));
			else
				replace.put("sender", "Server Admin");
			if (!target.equals(sender)) {
				Utils.sI18n(target, "fireTarget", replace);
				Utils.sI18n(sender, "fireSender", replace);
			} else {
				Utils.sI18n(sender, "fireYourself", replace);
			}
		}
	}

	/* (non-Javadoc)
	 * @see be.Balor.Manager.Commands.CoreCommand#argsCheck(java.lang.String[])
	 */
	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see be.Balor.Manager.Commands.CoreCommand#registerBukkitPerm()
	 */
	@Override
	public void registerBukkitPerm() {
		PermParent parent = new PermParent(permNode + ".*");
		plugin.getPermissionLinker().addChildPermParent(parent, new PermParent("fun.*"));
		PermChild child = new PermChild(permNode, bukkitDefault);
		parent.addChild(child).addChild(fire).addChild(arrow);
		bukkitPerm = child.getBukkitPerm();
	}
}