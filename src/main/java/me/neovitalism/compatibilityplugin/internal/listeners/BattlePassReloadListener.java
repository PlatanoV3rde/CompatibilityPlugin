package me.neovitalism.compatibilityplugin.internal.listeners;

import io.github.battlepass.api.events.server.PluginReloadEvent;
import me.neovitalism.compatibilityplugin.battlepass.BattlePassHook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BattlePassReloadListener implements Listener {
    @EventHandler
    public void onBPReload(PluginReloadEvent event) {
        BattlePassHook.register();
    }
}
