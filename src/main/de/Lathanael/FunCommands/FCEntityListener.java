package de.Lathanael.FunCommands;

import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerListener;

public class FCEntityListener extends PlayerListener {

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
