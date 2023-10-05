package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.events.CameraEvent;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class TakePictureQuest extends PixelmonQuestContainer {
    public TakePictureQuest(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onCatch(PixelmonEvent pixelmonEvent) {
        if(pixelmonEvent.getPixelmonEvent() instanceof CameraEvent.TakePhoto) {
            CameraEvent.TakePhoto event = (CameraEvent.TakePhoto) pixelmonEvent.getPixelmonEvent();
            Player player = Bukkit.getPlayer(event.player.getUUID());
            if(player != null) {
                this.executionBuilder("take-picture")
                        .player(player)
                        .root(event.pixelmon)
                        .progressSingle()
                        .buildAndExecute();
            }
        }
    }
}
