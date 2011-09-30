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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class BlocksOld {

	private List<BlockState> blocks = new ArrayList<BlockState>();

	public BlocksOld () {

	}

	public void addBlock(Block block) {
		blocks.add(block.getState());
	}

	public List<BlockState> getStates() {
		return blocks;
	}
}
