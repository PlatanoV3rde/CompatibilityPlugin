package me.neovitalism.compatibilityplugin.internal.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PixelmonEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final net.minecraftforge.eventbus.api.Event pixelmonEvent;

    public PixelmonEvent(net.minecraftforge.eventbus.api.Event pixelmonEvent) {
        this.pixelmonEvent = pixelmonEvent;
    }

    public net.minecraftforge.eventbus.api.Event getPixelmonEvent() {
        return pixelmonEvent;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
