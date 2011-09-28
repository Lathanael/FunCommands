package de.Lathanael.FunCommands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Tools.MaterialContainer;
import be.Balor.Tools.Utils;
import be.Balor.bukkit.AdminCmd.ACHelper;

public class Entomb extends CoreCommand {

	public Entomb() {
		super("ac_entomb", "admincmd.fun.entomb", "FunCommands");
	}

	@Override
	public void execute(CommandSender sender, CommandArgs args) {
		Player target;
		MaterialContainer mat = null;
		mat = ACHelper.getInstance().checkMaterial(sender, "web");

		target = Utils.getUser(sender, args, permNode, 1, true);
		if (target == null)
			return;
		if (args.length >= 2)
			mat = ACHelper.getInstance().checkMaterial(sender, args.getString(1));
		Location loc = target.getLocation();

	}


	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 1;
	}
}
