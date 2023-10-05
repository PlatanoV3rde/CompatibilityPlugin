package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.events.LevelUpEvent;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class LevelPokemonQuest extends PixelmonQuestContainer {
    public LevelPokemonQuest(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onLevel(PixelmonEvent pixelmonEvent) {
        if(pixelmonEvent.getPixelmonEvent() instanceof LevelUpEvent.Post) {
            LevelUpEvent.Post event = (LevelUpEvent.Post) pixelmonEvent.getPixelmonEvent();
            if(event.getPlayer() != null) {
                Player player = Bukkit.getPlayer(event.getPlayer().getUUID());
                if (player != null) {
                    this.executionBuilder("level-pokemon")
                            .player(player)
                            .root(event.getPokemon())
                            .progressSingle()
                            .buildAndExecute();
                }
            }
        }
    }
}
