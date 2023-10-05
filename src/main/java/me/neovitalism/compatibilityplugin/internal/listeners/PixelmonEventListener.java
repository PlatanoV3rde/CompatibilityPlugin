package me.neovitalism.compatibilityplugin.internal.listeners;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.daycare.event.DayCareEvent;
import com.pixelmonmod.pixelmon.api.events.*;
import me.neovitalism.compatibilityplugin.CompatibilityPlugin;
import me.neovitalism.compatibilityplugin.internal.events.PixelmonEvent;
import me.neovitalism.compatibilityplugin.utils.HybridUtils;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PixelmonEventListener {
    public PixelmonEventListener() {
        HybridUtils.registerListener(Pixelmon.EVENT_BUS, this);
    }

    public void disable() {
        HybridUtils.unregisterListener(Pixelmon.EVENT_BUS, this);
    }

    @SubscribeEvent
    public void onBreed(DayCareEvent.PostCollect event) {
        CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event));
    }

    @SubscribeEvent
    public void onCatch(CaptureEvent.SuccessfulCapture event) {
        CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event));
    }

    @SubscribeEvent
    public void onEvolve(EvolveEvent event) {
        CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event));
    }

    @SubscribeEvent
    public void onHatch(EggHatchEvent.Post event) {
        CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event));
    }

    @SubscribeEvent
    public void onDefeat(BeatWildPixelmonEvent event) {
        CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event));
    }

    @SubscribeEvent
    public void onLevel(LevelUpEvent.Post event) {
        CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event));
    }

    @SubscribeEvent
    public void onApricornPick(ApricornEvent.Pick event) {
        CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event));
    }

    @SubscribeEvent
    public void onPictureTaken(CameraEvent.TakePhoto event) {
        CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event));
    }

    @SubscribeEvent
    public void onTrainerDefeat(BeatTrainerEvent event) {
        CompatibilityPlugin.pm.callEvent(new PixelmonEvent(event));
    }
}
