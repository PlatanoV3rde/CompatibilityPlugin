package me.neovitalism.compatibilityplugin.utils;

import io.izzel.arclight.api.Arclight;
import net.minecraftforge.eventbus.api.IEventBus;
import org.bukkit.plugin.java.JavaPlugin;

public class HybridUtils {
    private static JavaPlugin plugin;

    private static boolean arclight = false;
    private static boolean mohist = false;

    public static void checkPlatform(JavaPlugin plugin) {
        HybridUtils.plugin = plugin;
        try {
            Class.forName("io.izzel.arclight.api.Arclight");
            arclight = true;
//            return;
        } catch(ClassNotFoundException ignored) {}
//        try {
//            Class.forName("com.mohistmc.MohistMC");
//            mohist = true;
//        } catch(ClassNotFoundException ignored) {}
    }

    public static void registerListener(IEventBus eventBus, Object target) {
        if(arclight) {
            Arclight.registerForgeEvent(plugin, eventBus, target);
//        } else if(mohist) {
//            plugin.registerForgeEvent(eventBus, target);
        } else eventBus.register(target);
    }

    public static void unregisterListener(IEventBus eventBus, Object target) {
//        if(mohist) {
//            plugin.unregisterForgeEvents(eventBus, target);
//        } else
            eventBus.unregister(target);
    }
}
