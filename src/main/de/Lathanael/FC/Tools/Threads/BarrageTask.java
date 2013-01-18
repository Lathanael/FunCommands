/*************************************************************************
 * Copyright (C) 2013 Philippe Leipold
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

package de.Lathanael.FC.Tools.Threads;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class BarrageTask implements Runnable {

	private final Player target;
	
	public BarrageTask(final Player target) {
		this.target = target;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Location loc = target.getLocation();
		Location loc2 = new Location(target.getWorld(), loc.getX(), loc.getY()+4, loc.getZ());
		target.getWorld().spawnArrow(loc2, new Vector(0, -1, 0), 1F, 10F);
	}

}
