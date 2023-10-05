package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.events.EggHatchEvent;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class HatchPokemonQuest extends PixelmonQuestContainer {
    public HatchPokemonQuest(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onHatch(PixelmonEvent pixelmonEvent) {
        if(pixelmonEvent.getPixelmonEvent() instanceof EggHatchEvent.Post) {
            EggHatchEvent.Post event = (EggHatchEvent.Post) pixelmonEvent.getPixelmonEvent();
            Player player = Bukkit.getPlayer(event.getPlayer().getUUID());
            if(player != null) {
                this.executionBuilder("hatch-pokemon")
                        .player(player)
                        .root(event.getPokemon())
                        .progressSingle()
                        .buildAndExecute();
            }
        }
    }
}
