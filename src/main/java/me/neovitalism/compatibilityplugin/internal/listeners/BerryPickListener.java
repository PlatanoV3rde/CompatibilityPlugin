package me.neovitalism.compatibilityplugin.internal.listeners;

import com.pixelmonmod.pixelmon.enums.BerryType;
import me.neovitalism.compatibilityplugin.CompatibilityPlugin;
import me.neovitalism.compatibilityplugin.internal.events.BerryPickEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class BerryPickListener implements Listener {
    private static final List<String> berryLeaves = new ArrayList<>();


    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getHand() != EquipmentSlot.OFF_HAND) {
            Block block = event.getClickedBlock();
            testBlock(event.getPlayer(), block);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        testBlock(event.getPlayer(), event.getBlock());
    }

    private void testBlock(Player player, Block block) {
        String blockName;
        if(block != null && berryLeaves.contains(blockName = block.getType().name())) {
            String age = block.getBlockData().getAsString();
            String persistant = age.substring(age.indexOf("persistant=") + 11, age.indexOf("persistant=") + 15);
            age = age.substring(age.indexOf("age=") + 4, age.indexOf("age=") + 5);
            if(Integer.parseInt(age) == 2 && !persistant.equalsIgnoreCase("true")) {
                BerryType berryType = BerryType.valueOf(blockName.replace("PIXELMON_BERRY_LEAVES_", ""));
                CompatibilityPlugin.pm.callEvent(new BerryPickEvent(player, berryType));
            }
        }
    }

    static {
        for(BerryType berry : BerryType.values()) {
            berryLeaves.add("PIXELMON_BERRY_LEAVES_" + berry.toString());
        }
    }
}
