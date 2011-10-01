package de.Lathanael.Listeners;

import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import de.Lathanael.FunCommands.FunCommands;
import de.Lathanael.Tools.BlocksOld;

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
