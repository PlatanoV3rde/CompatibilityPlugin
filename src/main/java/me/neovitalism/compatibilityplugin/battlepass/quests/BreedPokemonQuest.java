package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.daycare.event.DayCareEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class BreedPokemonQuest extends PixelmonQuestContainer {
    public BreedPokemonQuest(JavaPlugin battlePlugin) {
        super(battlePlugin);
    }

    @EventHandler
    public void onBreed(PixelmonEvent pixelmonEvent) {
        if(pixelmonEvent.getPixelmonEvent() instanceof DayCareEvent.PostCollect) {
            DayCareEvent.PostCollect event = (DayCareEvent.PostCollect) pixelmonEvent.getPixelmonEvent();
            Player player = Bukkit.getPlayer(event.getPlayer().getUUID());
            if(player != null) {
                this.executionBuilder("breed-pokemon")
                        .player(player)
                        .root(new Pokemon[]{event.getParentOne(), event.getParentTwo(), event.getChildGiven()})
                        .progressSingle()
                        .buildAndExecute();
            }
        }
    }
}
