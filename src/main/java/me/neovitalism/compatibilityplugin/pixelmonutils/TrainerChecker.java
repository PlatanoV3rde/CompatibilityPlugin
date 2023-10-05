package me.neovitalism.compatibilityplugin.pixelmonutils;

import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import me.neovitalism.compatibilityplugin.utils.ChatUtils;
import me.neovitalism.neoextras.api.battlesim.NeoExtrasNPCTrainer;
import org.bukkit.Bukkit;

public class TrainerChecker {
    private static boolean checkExtras;

    public static void checkExtras(boolean checkExtras) {
        TrainerChecker.checkExtras = checkExtras;
        if(checkExtras) {
            ChatUtils.sendPrettyMessage(Bukkit.getConsoleSender(), "&aNeoExtras support enabled!");
        }
    }

    public static boolean isExtrasTrainer(NPCTrainer trainer) {
        if(checkExtras) {
            return trainer instanceof NeoExtrasNPCTrainer;
        }
        return false;
    }
}
