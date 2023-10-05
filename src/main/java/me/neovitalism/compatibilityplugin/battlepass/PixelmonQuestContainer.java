package me.neovitalism.compatibilityplugin.battlepass;

import me.hyfe.simplespigot.annotations.NotNull;
import net.advancedplugins.bp.impl.actions.containers.ActionContainer;
import org.bukkit.plugin.java.JavaPlugin;

public class PixelmonQuestContainer extends ActionContainer {
    public PixelmonQuestContainer(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @NotNull
    @Override
    public PixelmonExecutionBuilder executionBuilder(@NotNull String var1) {
        return new PixelmonExecutionBuilder(this, var1);
    }
}
