package me.neovitalism.compatibilityplugin.internal.listeners;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.daycare.event.DayCareEvent;
import com.pixelmonmod.pixelmon.api.events.*;
import me.neovitalism.compatibilityplugin.CompatibilityPlugin;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import me.neovitalism.compatibilityplugin.utils.HybridUtils;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.Bukkit;

public class PixelmonEventListener {
    public PixelmonEventListener() {
        HybridUtils.registerListener(Pixelmon.EVENT_BUS, this);
    }

    public void disable() {
        HybridUtils.unregisterListener(Pixelmon.EVENT_BUS, this);
    }

    @SubscribeEvent
    public void onBreed(DayCareEvent.PostCollect event) {
        this.fireEvent(event);
    }

    @SubscribeEvent
    public void onCatch(CaptureEvent.SuccessfulCapture event) {
        this.fireEvent(event);
    }

    @SubscribeEvent
    public void onEvolve(EvolveEvent event) {
        this.fireEvent(event);
    }

    @SubscribeEvent
    public void onHatch(EggHatchEvent.Post event) {
        this.fireEvent(event);
    }

    @SubscribeEvent
    public void onDefeat(PixelmonKnockoutEvent event) {
        this.fireEvent(event);
    }

    @SubscribeEvent
    public void onLevel(LevelUpEvent.Post event) {
        this.fireEvent(event);
    }

    @SubscribeEvent
    public void onApricornPick(ApricornEvent.Pick event) {
        this.fireEvent(event);
    }

    @SubscribeEvent
    public void onPictureTaken(CameraEvent.TakePhoto event) {
        this.fireEvent(event);
    }

    @SubscribeEvent
    public void onTrainerDefeat(BeatTrainerEvent event) {
        this.fireEvent(event);
    }

    public void fireEvent(Event event) {
        Bukkit.getScheduler().runTask(CompatibilityPlugin.inst(), () -> CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event)));
    }
}
