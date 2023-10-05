package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class CatchPokemonQuest extends PixelmonQuestContainer {
    public CatchPokemonQuest(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onCatch(PixelmonEvent pixelmonEvent) {
        if(pixelmonEvent.getPixelmonEvent() instanceof CaptureEvent.SuccessfulCapture) {
            CaptureEvent.SuccessfulCapture event = (CaptureEvent.SuccessfulCapture) pixelmonEvent.getPixelmonEvent();
            Player player = Bukkit.getPlayer(event.getPlayer().getUUID());
            if(player != null) {
                this.executionBuilder("catch-pokemon")
                        .player(player)
                        .root(event.getPokemon())
                        .progressSingle()
                        .buildAndExecute();
            }
        }
    }
}
