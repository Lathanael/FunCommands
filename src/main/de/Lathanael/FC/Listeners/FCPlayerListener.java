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

import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.Lathanael.FC.FunCommands.FunCommands;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class FCPlayerListener extends PlayerListener {

	public FCPlayerListener() {

	}

	public void onPlayerKick(PlayerKickEvent event) {
		String displayName = event.getPlayer().getDisplayName();
		if (FunCommands.players.containsKey(displayName))
			FunCommands.players.remove(displayName);
	}

	public void onPlayerQuit(PlayerQuitEvent event) {
		String displayName = event.getPlayer().getDisplayName();
		if (FunCommands.players.containsKey(displayName))
			FunCommands.players.remove(displayName);
	}
}
