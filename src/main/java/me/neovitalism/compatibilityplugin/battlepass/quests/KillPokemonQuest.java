package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class KillPokemonQuest extends PixelmonQuestContainer {
    public KillPokemonQuest(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onDefeat(PixelmonEvent pixelmonEvent) {
        if(pixelmonEvent.getPixelmonEvent() instanceof BeatWildPixelmonEvent) {
            BeatWildPixelmonEvent event = (BeatWildPixelmonEvent) pixelmonEvent.getPixelmonEvent();
            Player player = Bukkit.getPlayer(event.player.getUUID());
            if(player != null) {
                this.executionBuilder("kill-pokemon")
                        .player(player)
                        .root(((PixelmonEntity) event.wpp.getEntity()))
                        .progressSingle()
                        .buildAndExecute();
            }
        }
    }
}
