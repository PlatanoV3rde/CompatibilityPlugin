package me.neovitalism.compatibilityplugin.battlepass.quests;

import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.BerryPickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class PickBerryQuest extends PixelmonQuestContainer {
    public PickBerryQuest(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onBerryPick(BerryPickEvent event) {
        Player player = event.getPlayer();
        if(player != null) {
            this.executionBuilder("pick-berry")
                    .player(player)
                    .root(event.getBerry())
                    .progressSingle()
                    .buildAndExecute();
        }
    }
}
