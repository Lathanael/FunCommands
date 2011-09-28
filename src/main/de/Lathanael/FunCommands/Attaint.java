package de.Lathanael.FunCommands;

import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Tools.Utils;

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
			Utils.sI18n(target, "attainTarget", replace);
			Utils.sI18n(sender, "attaintSender", replace);
		} else {
			Utils.sI18n(sender, "attainYourself", replace);
		}
	}


	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 2;
	}

}
