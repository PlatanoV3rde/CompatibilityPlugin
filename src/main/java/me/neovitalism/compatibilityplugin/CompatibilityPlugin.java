package me.neovitalism.compatibilityplugin;

import me.neovitalism.compatibilityplugin.battlepass.BattlePassHook;
import me.neovitalism.compatibilityplugin.internal.listeners.BattlePassReloadListener;
import me.neovitalism.compatibilityplugin.internal.listeners.BerryPickListener;
import me.neovitalism.compatibilityplugin.internal.listeners.PixelmonEventListener;
import me.neovitalism.compatibilityplugin.pixelmonutils.TrainerChecker;
import me.neovitalism.compatibilityplugin.utils.ChatUtils;
import me.neovitalism.compatibilityplugin.utils.HybridUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CompatibilityPlugin extends JavaPlugin {
    private static CompatibilityPlugin instance;
    public static final PluginManager pm = Bukkit.getPluginManager();
    private PixelmonEventListener pixelmonEventListener;

    @Override
    public void onEnable() {
        instance = this;
        HybridUtils.checkPlatform(this);
        if(pm.getPlugin("OldSchoolBreeding") != null) {
            BattlePassHook.markHasOSB();
            ChatUtils.sendPrettyMessage(Bukkit.getConsoleSender(), "&aOldSchoolBreeding support enabled!");
        }
        if(pm.getPlugin("BattlePass") != null) {
            BattlePassHook.register();
            pm.registerEvents(new BattlePassReloadListener(), this);
            ChatUtils.sendPrettyMessage(Bukkit.getConsoleSender(), "&aBattlePass support enabled!");
        }
        TrainerChecker.checkExtras(pm.getPlugin("NeoExtras") != null);
        pixelmonEventListener = new PixelmonEventListener();
        pm.registerEvents(new BerryPickListener(), this);
    }


    @Override
    public void onDisable() {
        pixelmonEventListener.disable();
    }

    public static CompatibilityPlugin inst() {
        return instance;
    }
}
