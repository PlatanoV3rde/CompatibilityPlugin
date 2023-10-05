package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.events.ApricornEvent;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class PickApricornQuest extends PixelmonQuestContainer {
    public PickApricornQuest(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onApricornPick(PixelmonEvent pixelmonEvent) {
        if(pixelmonEvent.getPixelmonEvent() instanceof ApricornEvent.Pick) {
            ApricornEvent.Pick event = (ApricornEvent.Pick) pixelmonEvent.getPixelmonEvent();
            Player player = Bukkit.getPlayer(event.getPlayer().getUUID());
            if(player != null) {
                this.executionBuilder("pick-apricorn")
                        .player(player)
                        .root(event.getApricorn())
                        .progress(event.getPickedStack().getCount())
                        .buildAndExecute();
            }
        }
    }
}
