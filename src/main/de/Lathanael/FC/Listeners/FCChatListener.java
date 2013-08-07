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

package de.Lathanael.FC.Listeners;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import be.Balor.Manager.LocaleManager;
import be.Balor.Tools.CommandUtils.Users;

import de.Lathanael.FC.FunCommands.ChatCensorStrings;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class FCChatListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(final AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		HashMap<String, String> replace = new HashMap<String, String>();
		replace.put("player", Users.getPlayerName(event.getPlayer()));
		if (ChatCensorStrings.containsString(message)) {
			event.getPlayer().sendMessage(LocaleManager.I18n("censorTarget"));
			event.getPlayer().setHealth(0);
			Users.broadcastMessage(LocaleManager.I18n("censorBroadcast", replace));
		} else {
			String[] split = message.split(" ");
			if (split == null || split.length == 0)
				return;
			for (String s : split) {
				if (ChatCensorStrings.containsString(s)) {
					event.getPlayer().sendMessage(LocaleManager.I18n("censorTarget"));
					event.getPlayer().setHealth(0);
					Users.broadcastMessage(LocaleManager.I18n("censorBroadcast", replace));
				}
			}
		}
	}
}
