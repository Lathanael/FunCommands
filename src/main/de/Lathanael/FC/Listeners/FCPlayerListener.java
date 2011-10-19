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

package de.Lathanael.FC.Listeners;

import java.util.HashMap;

import net.minecraft.server.Packet201PlayerInfo;
import net.minecraft.server.Packet29DestroyEntity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

import be.Balor.Manager.Permissions.Plugins.SuperPermissions;
import be.Balor.Player.ACPlayer;
import be.Balor.Tools.Type;
import be.Balor.Tools.Utils;
import be.Balor.Tools.Files.ObjectContainer;

import de.Lathanael.FC.FunCommands.FunCommands;
import de.Lathanael.FC.FunCommands.Configuration;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class FCPlayerListener extends PlayerListener {

	public FCPlayerListener() {

	}

	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String displayName = null;
		ObjectContainer o = ACPlayer.getPlayer(player).getInformation("displayName");
		if (o != null) {
			displayName = o.getString();
			player.setDisplayName(displayName);
			if (!ACPlayer.getPlayer(player).hasPower(Type.INVISIBLE) || !ACPlayer.getPlayer(player).hasPower(Type.FAKEQUIT)) {
				((CraftServer) player.getServer()).getHandle().sendAll(
						new Packet201PlayerInfo(player.getName(), false, 100));
				((CraftServer) player.getServer()).getHandle().sendAll(
						new Packet201PlayerInfo(displayName, true, 100));
			}
			FunCommands.players.put(displayName, player);
			if (!SuperPermissions.isApiSet()) {
				HashMap<String, String> replace = new HashMap<String, String>();
				replace.put("name", Utils.getPlayerName(player));
				event.setJoinMessage(Utils.I18n("joinMessage", replace));
			}
		}
	}

	public void onPlayerKick(PlayerKickEvent event) {
		String displayName = event.getPlayer().getDisplayName();
		if (FunCommands.players.containsKey(displayName)) {
			if (Configuration.getInstance().getConfBoolean("PersistentNames")) {
				ACPlayer player = ACPlayer.getPlayer(event.getPlayer());
				player.setInformation("displayName", displayName);
			}
			FunCommands.players.remove(displayName);
		}
	}

	public void onPlayerQuit(PlayerQuitEvent event) {
		String displayName = event.getPlayer().getDisplayName();
		if (FunCommands.players.containsKey(displayName)) {
			if (Configuration.getInstance().getConfBoolean("PersistentNames")) {
				ACPlayer player = ACPlayer.getPlayer(event.getPlayer());
				player.setInformation("displayName", displayName);
			}
			FunCommands.players.remove(displayName);
			if (!SuperPermissions.isApiSet()) {
				HashMap<String, String> replace = new HashMap<String, String>();
				replace.put("name", Utils.getPlayerName(event.getPlayer()));
				event.setQuitMessage(Utils.I18n("quitMessage", replace));
			}
		}
	}
}
