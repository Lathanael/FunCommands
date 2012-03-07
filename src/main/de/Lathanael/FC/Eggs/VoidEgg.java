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

package de.Lathanael.FC.Eggs;

import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEggThrowEvent;

import de.Lathanael.FC.Tools.BlocksOld;
import de.Lathanael.FC.Tools.Utilities;
import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Tools.Egg.EggPermission;
import be.Balor.Tools.Egg.EggType;
import be.Balor.Tools.Egg.Exceptions.ProcessingArgsException;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
@EggPermission(permission = "fun.voidegg", permissionParent = "fun.*")
public class VoidEgg extends EggType<BlocksOld> {

	/**
	 *
	 */
	private static final long serialVersionUID = 5272463480388189298L;

	@Override
	public void onEvent(PlayerEggThrowEvent event) {
		Egg egg = event.getEgg();
		egg.remove();
		event.setHatching(false);
		Utilities.createVoid(event.getPlayer(), egg.getLocation(), Material.AIR, value, -150);
		Utilities.undoVoid(egg.getLocation());
	}

	@Override
	protected void processArguments(Player sender, CommandArgs args) throws ProcessingArgsException {
		value = new BlocksOld();
	}

}
