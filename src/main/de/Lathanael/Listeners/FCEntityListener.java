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

package de.Lathanael.Listeners;

import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import de.Lathanael.FunCommands.FunCommands;
import de.Lathanael.Tools.BlocksOld;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class FCEntityListener extends EntityListener {

	FunCommands main;

	public FCEntityListener(FunCommands instance) {
		main = instance;
	}

	public void onEntityDeath(EntityDeathEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		Player player = (Player) event.getEntity();
		if (!FunCommands.players.contains(player))
			return;
		FunCommands.players.remove(player);
		BlocksOld states = FunCommands.blockStates.get(player);
		for (BlockState state : states.getStates())
			state.update(true);
	}
}
