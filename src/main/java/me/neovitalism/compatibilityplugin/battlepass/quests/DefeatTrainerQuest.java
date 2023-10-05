package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.events.BeatTrainerEvent;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class DefeatTrainerQuest extends PixelmonQuestContainer {
    public DefeatTrainerQuest(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onTrainerDefeat(PixelmonEvent pixelmonEvent) {
        if(pixelmonEvent.getPixelmonEvent() instanceof BeatTrainerEvent) {
            BeatTrainerEvent event = (BeatTrainerEvent) pixelmonEvent.getPixelmonEvent();
            Player player = Bukkit.getPlayer(event.player.getUUID());
            if(player != null) {
                this.executionBuilder("defeat-trainers")
                        .player(player)
                        .root(event.trainer)
                        .progressSingle()
                        .buildAndExecute();
            }
        }
    }
}
