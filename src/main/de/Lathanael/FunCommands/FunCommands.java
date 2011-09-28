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

package de.Lathanael.FunCommands;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;

import be.Balor.Manager.LocaleManager;
import be.Balor.Manager.Permissions.PermParent;
import be.Balor.Tools.Utils;
import be.Balor.bukkit.AdminCmd.ACPluginManager;
import be.Balor.bukkit.AdminCmd.AbstractAdminCmdPlugin;


/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class FunCommands extends AbstractAdminCmdPlugin {

	/**
	 * @param name
	 */
	public FunCommands() {
		super("FunCommands");
	}

	@Override
	public void registerCmds() {
		ACPluginManager.registerCommand(Slap.class);
		ACPluginManager.registerCommand(Rocket.class);
		ACPluginManager.registerCommand(Entomb.class);
		ACPluginManager.registerCommand(ACVoid.class);
		ACPluginManager.registerCommand(Attaint.class);
	}

	@Override
	protected void registerPermParents() {
		permissionLinker.addPermParent(new PermParent("admincmd.fun.*"));
		permissionLinker.setMajorPerm(new PermParent("admincmd.*"));
	}

	@Override
	protected void setDefaultLocale() {
		Utils.addLocale("slap", ChatColor.DARK_AQUA + "You have slapped " + ChatColor.GOLD
				+ "%target" + ChatColor.DARK_AQUA + "!");
		Utils.addLocale("slapped", ChatColor.DARK_AQUA + "You have been slapped by " + ChatColor.GOLD
				+ "%sender" + ChatColor.DARK_AQUA + "!");
		Utils.addLocale("attaintYourself", ChatColor.DARK_AQUA + "You changed your Displayname to:" + ChatColor.DARK_RED
				+ " %name");
		Utils.addLocale("attaintTarget", ChatColor.DARK_AQUA+  "Your Displayname has been changed by" + ChatColor.GOLD
				+ " %sender" + ChatColor.DARK_AQUA + " to:" + ChatColor.DARK_RED + "%name");
		Utils.addLocale("attaintSender", ChatColor.DARK_AQUA + "You have changed the Displayname of" + ChatColor.GOLD
				+ " %target" + ChatColor.DARK_AQUA + " to:" + ChatColor.DARK_RED +" %name");
		LocaleManager.getInstance().save();
	}

	@Override
	public void onEnable() {
		super.onEnable();
		PluginDescriptionFile pdfFile = this.getDescription();
		permissionLinker.registerAllPermParent();
		System.out.print("[" + pdfFile.getName() +"] Enabled. (Version " + pdfFile.getVersion() + ")");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.bukkit.plugin.Plugin#onDisable()
	 */
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.print("[" + pdfFile.getName() +"] Disabled. (Version " + pdfFile.getVersion() + ")");
	}
}
