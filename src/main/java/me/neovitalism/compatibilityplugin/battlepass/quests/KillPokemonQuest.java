package me.neovitalism.compatibilityplugin.battlepass.quests;

import com.pixelmonmod.pixelmon.api.events.PixelmonKnockoutEvent;
import me.neovitalism.compatibilityplugin.battlepass.PixelmonQuestContainer;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import net.minecraft.entity.player.ServerPlayerEntity;
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
        if(pixelmonEvent.getPixelmonEvent() instanceof PixelmonKnockoutEvent) {
            PixelmonKnockoutEvent event = (PixelmonKnockoutEvent) pixelmonEvent.getPixelmonEvent();
            ServerPlayerEntity serverPlayerEntity = event.source.getPlayerOwner();
            if(serverPlayerEntity != null) {
                Player player = Bukkit.getPlayer(serverPlayerEntity.getUUID());
                if(player != null) {
                    this.executionBuilder("kill-pokemon")
                            .player(player)
                            .root(event.pokemon.entity)
                            .progressSingle()
                            .buildAndExecute();
                }
            }
        }
    }
}
