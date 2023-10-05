package me.neovitalism.compatibilityplugin.internal.events;

import com.pixelmonmod.pixelmon.enums.BerryType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BerryPickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final BerryType berry;


    public BerryPickEvent(Player player, BerryType berry) {
        this.player = player;
        this.berry = berry;
    }

    public Player getPlayer() {
        return player;
    }

    public BerryType getBerry() {
        return berry;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
