package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.oldschoolbreeding.api.events.bukkit.EggCreateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class OSBBreedPokemonQuest extends PixelmonQuestContainer {
    public OSBBreedPokemonQuest(JavaPlugin battlePlugin) {
        super(battlePlugin);
    }

    @EventHandler
    public void onEggCreation(EggCreateEvent event) {
        Player player = Bukkit.getPlayer(event.getPlayerUUID());
        if(player != null) {
            this.executionBuilder("osbreed-pokemon")
                    .player(player)
                    .root(new Pokemon[]{event.getParent1(), event.getParent2(), event.getEgg()})
                    .progressSingle()
                    .buildAndExecute();
        }
    }
}
