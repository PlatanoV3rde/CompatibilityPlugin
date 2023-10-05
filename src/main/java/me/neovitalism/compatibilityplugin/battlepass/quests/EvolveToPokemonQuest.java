package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.events.EvolveEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class EvolveToPokemonQuest extends PixelmonQuestContainer {
    public EvolveToPokemonQuest(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onEvolve(PixelmonEvent pixelmonEvent) {
        if(pixelmonEvent.getPixelmonEvent() instanceof EvolveEvent.Post) {
            EvolveEvent.Post event = (EvolveEvent.Post) pixelmonEvent.getPixelmonEvent();
            Player player = Bukkit.getPlayer(event.getPlayer().getUUID());
            if(player != null) {
                Pokemon pokemon = event.getPokemon();
                this.executionBuilder("evolve-to")
                        .player(player)
                        .root(pokemon)
                        .progressSingle()
                        .buildAndExecute();
            }
        }
    }
}
