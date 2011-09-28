package de.Lathanael.FunCommands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Tools.Utils;

public class ACVoid extends CoreCommand {

	public ACVoid() {
		super("ac_void", "admincmd.fun.void", "FunCommands");
	}

	@Override
	public void execute(CommandSender sender, CommandArgs args) {
		Player target;

		target = Utils.getUser(sender, args, permNode, 1, true);
		if (target == null)
			return;
		Location loc = target.getLocation();
	}


	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 1;
	}
}
