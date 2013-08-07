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

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import de.Lathanael.FC.FunCommands.FunCommands;
import de.Lathanael.FC.Tools.Utilities;

import be.Balor.Manager.LocaleManager;
import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Manager.Exceptions.PlayerNotFound;
import be.Balor.Manager.Exceptions.ActionNotPermitedException;
import be.Balor.Manager.Permissions.PermissionManager;
import be.Balor.Player.ACPlayer;
import be.Balor.Tools.Type;
import be.Balor.Tools.CommandUtils.Users;
import be.Balor.bukkit.AdminCmd.ACPluginManager;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class Attaint extends CoreCommand {

	/**
	 *
	 */
	public Attaint() {
		super("fc_attaint", "fun.attaint", "FunCommands");
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
		String name = "";
		CommandArgs newArgs;
		HashMap<String, String> replace = new HashMap<String, String>();
		if (FunCommands.players.containsKey(args.getString(0)))
			name = FunCommands.players.get(args.getString(0)).getName();
		else
			name = args.getString(0);
		for (Player p : ACPluginManager.getServer().getOnlinePlayers()) {
			if (p.getName().equals(name)) {
				replace.put("name", name);
				LocaleManager.sI18n(sender, "attaintErrorDuplicateName", replace);
				return;
			}
		}
		newArgs = new CommandArgs(name);
		target = Users.getUser(sender, newArgs, permNode, 0, true);
		final ACPlayer acTarget = ACPlayer.getPlayer(target);
		if (target == null)
			return;
		
		if (args.hasFlag('c')) {
			if (!(PermissionManager.hasPerm(sender, "fun.attaint.check")))
				return;
			replace.put("dname", target.getDisplayName());
			replace.put("name", target.getName());
			LocaleManager.sI18n(sender, "attaintShowName", replace);
			return;
		}
		if (FunCommands.players.containsKey(args.getString(0)))
			FunCommands.players.remove(args.getString(0));
		if (args.length < 2) {
			target.setDisplayName(target.getName());
			acTarget.setInformation("displayName", target.getName());
			return;
		}
		FunCommands.players.put(args.getString(1), target);
		replace.put("target", target.getName());
		replace.put("name", args.getString(1));
		if (Users.isPlayer(sender, false))
			replace.put("sender", Users.getPlayerName((Player) sender));
		else
			replace.put("sender", "Server Admin");

		target.setDisplayName(args.getString(1));
		acTarget.setInformation("displayName", args.getString(1));
		if (!ACPlayer.getPlayer(target).hasPower(Type.INVISIBLE) || !ACPlayer.getPlayer(target).hasPower(Type.FAKEQUIT)) {
			target.setPlayerListName(args.getString(1));
			Utilities.createNewPlayerShell(target, args.getString(1));
		}

		if (!target.equals(sender)) {
			LocaleManager.sI18n(target, "attaintTarget", replace);
			LocaleManager.sI18n(sender, "attaintSender", replace);
		} else {
			LocaleManager.sI18n(sender, "attaintYourself", replace);
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

	/* (non-Javadoc)
	 * @see be.Balor.Manager.Commands.CoreCommand#registerBukkitPerm()
	 */
	@Override
	public void registerBukkitPerm() {
		plugin.getPermissionLinker().addPermChild("fun.attaint.check", PermissionDefault.OP);
		super.registerBukkitPerm();
	}
}
