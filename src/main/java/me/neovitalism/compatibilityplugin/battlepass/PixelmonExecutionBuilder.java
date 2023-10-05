package me.neovitalism.compatibilityplugin.battlepass;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import com.pixelmonmod.pixelmon.enums.BerryType;
import com.pixelmonmod.pixelmon.enums.items.ApricornType;
import io.github.battlepass.service.CheckHelper;
import me.hyfe.simplespigot.annotations.NotNull;
import me.hyfe.simplespigot.annotations.Nullable;
import net.advancedplugins.bp.impl.actions.ActionExecution;
import net.advancedplugins.bp.impl.actions.ActionExecutionBuilder;
import net.advancedplugins.bp.impl.actions.ActionRegistry;
import net.advancedplugins.bp.impl.actions.containers.ActionContainer;
import org.bukkit.entity.Player;

import java.math.BigInteger;

public class PixelmonExecutionBuilder extends ActionExecutionBuilder {
    private final String questType;
    private Player player;
    private BigInteger progress;
    private boolean overrideUpdate;
    private final ActionContainer container;
    private PixelmonExecutableQuestResult questResult;

    public PixelmonExecutionBuilder(ActionContainer var1, String var2) {
        super(var1, var2);
        this.container = var1;
        this.questType = var2;
    }

    public PixelmonExecutionBuilder root(PixelmonEntity var1) {
        if (this.questResult == null) {
            this.questResult = new PixelmonExecutableQuestResult();
        }
        this.questResult.root(var1);
        return this;
    }

    public PixelmonExecutionBuilder root(Pokemon var1) {
        if (this.questResult == null) {
            this.questResult = new PixelmonExecutableQuestResult();
        }
        this.questResult.root(var1);
        return this;
    }

    public PixelmonExecutionBuilder root(Pokemon[] var1) {
        if (this.questResult == null) {
            this.questResult = new PixelmonExecutableQuestResult();
        }
        this.questResult.root(var1);
        return this;
    }

    public PixelmonExecutionBuilder root(NPCTrainer var1) {
        if (this.questResult == null) {
            this.questResult = new PixelmonExecutableQuestResult();
        }
        this.questResult.root(var1);
        return this;
    }

    public PixelmonExecutionBuilder root(ApricornType var1) {
        if (this.questResult == null) {
            this.questResult = new PixelmonExecutableQuestResult();
        }
        this.questResult.root(var1);
        return this;
    }

    public PixelmonExecutionBuilder root(BerryType var1) {
        if (this.questResult == null) {
            this.questResult = new PixelmonExecutableQuestResult();
        }
        this.questResult.root(var1);
        return this;
    }


    @Override
    public PixelmonExecutionBuilder player(@Nullable Player var1) {
        this.player = var1;
        return this;
    }

    @Override
    public void buildAndExecute() {
        if (this.player != null) {
            ActionExecution var1 = this.build();
            ActionRegistry.getRegistry().getReader().onAction(var1);
        }
    }

    @Override
    public PixelmonExecutionBuilder progress(@NotNull int progress) {
        CheckHelper.notNull(progress, "Quest execution progress");
        return this.progress(BigInteger.valueOf(progress));
    }

    @Override
    public ActionExecution build() {
        String var1 = "QuestExecution Build -> %s must be set.";
        if (this.player == null) {
            throw new IllegalStateException(String.format(var1, "Player"));
        } else if (this.questType != null && !this.questType.isEmpty()) {
            if (this.progress == null) {
                throw new IllegalStateException(String.format(var1, "Progress"));
            } else {
                if (this.questResult == null) {
                    this.questResult = (PixelmonExecutableQuestResult) (new PixelmonExecutableQuestResult()).root("none");
                }
                return new ActionExecution(this.player, this.questType, this.progress, this.overrideUpdate, this.questResult);
            }
        } else {
            throw new IllegalStateException(String.format(var1, "Quest type"));
        }
    }

    @Override
    public PixelmonExecutionBuilder progressSingle() {
        return this.progress(BigInteger.ONE);
    }

    @Override
    public PixelmonExecutionBuilder progress(BigInteger var1) {
        CheckHelper.notNull(var1, "Quest execution progress");
        this.progress = var1;
        return this;
    }

    @Override
    public PixelmonExecutionBuilder overrideUpdate() {
        this.overrideUpdate = true;
        return this;
    }
}
