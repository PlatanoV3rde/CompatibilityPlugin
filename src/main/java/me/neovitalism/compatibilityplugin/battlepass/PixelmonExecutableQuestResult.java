package me.neovitalism.compatibilityplugin.battlepass;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import com.pixelmonmod.pixelmon.enums.BerryType;
import com.pixelmonmod.pixelmon.enums.items.ApricornType;
import me.neovitalism.compatibilityplugin.pixelmonutils.ApricornChecker;
import me.neovitalism.compatibilityplugin.pixelmonutils.BerryChecker;
import me.neovitalism.compatibilityplugin.pixelmonutils.SpecChecker;
import me.neovitalism.compatibilityplugin.pixelmonutils.TrainerChecker;
import net.advancedplugins.bp.impl.actions.objects.variable.ExecutableActionResult;
import net.advancedplugins.bp.impl.actions.objects.variable.Variable;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class PixelmonExecutableQuestResult extends ExecutableActionResult {
    private PixelmonEntity pixelmonEntity = null;
    private Pokemon pokemon = null;
    private Pokemon[] daycareBox = null;
    private ApricornType apricornType = null;
    private NPCTrainer trainer = null;
    private BerryType berryType = null;

    public PixelmonExecutableQuestResult root(PixelmonEntity pixelmonEntity) {
        this.pixelmonEntity = pixelmonEntity;
        return this;
    }

    public PixelmonExecutableQuestResult root(Pokemon pokemon) {
        this.pokemon = pokemon;
        return this;
    }

    public PixelmonExecutableQuestResult root(Pokemon[] daycareBox) {
        this.daycareBox = daycareBox;
        return this;
    }

    public PixelmonExecutableQuestResult root(ApricornType apricornType) {
        this.apricornType = apricornType;
        return this;
    }

    public PixelmonExecutableQuestResult root(NPCTrainer trainer) {
        this.trainer = trainer;
        return this;
    }

    public PixelmonExecutableQuestResult root(BerryType berryType) {
        this.berryType = berryType;
        return this;
    }

    @Override
    public boolean isEligible(Player var1, Variable var2) {
        for(String root : var2.getRoots()) {
            if(checkPixelmonRoots(var1, root)) return true;
        }
        return false;
    }

    private boolean isPixelmonRootValid(Player player, String string, PixelmonEntity pixelmonEntity) {
        SpecChecker specChecker = new SpecChecker(string);
        if(specChecker.checkPokemon(pixelmonEntity)) {
            if(specChecker.otCheckRequired()) {
                return specChecker.otChecker(pixelmonEntity.getPokemon().getOriginalTrainerUUID(), player.getUniqueId());
            } else return true;
        }
        return false;
    }

    private boolean isPixelmonRootValid(Player player, String string, Pokemon pokemon) {
        SpecChecker specChecker = new SpecChecker(string);
        if(specChecker.checkPokemon(pokemon)) {
            if(specChecker.otCheckRequired()) {
                return specChecker.otChecker(pokemon.getOriginalTrainerUUID(), player.getUniqueId());
            } else return true;
        }
        return false;
    }

    private boolean isPixelmonRootValid(Player player, String string, Pokemon[] daycareBox) {
        SpecChecker specChecker = new SpecChecker(string);
        return specChecker.checkBox(player.getUniqueId(), daycareBox);
    }

    private boolean isApricornRootValid(String root, ApricornType apricornType) {
        ApricornChecker apricornChecker = new ApricornChecker(root);
        return apricornChecker.checkApricorn(apricornType);
    }

    private boolean isTrainerRootValid(String root, NPCTrainer trainer) {
        if(root.equalsIgnoreCase("all")) {
            return true;
        } else if(root.equalsIgnoreCase("extras")) {
            return TrainerChecker.isExtrasTrainer(trainer);
        } else if(root.equalsIgnoreCase("not-extras")) {
            return !TrainerChecker.isExtrasTrainer(trainer);
        }
        return false;
    }

    private boolean isBerryRootValid(String root, BerryType berryType) {
        BerryChecker berryChecker = new BerryChecker(root);
        return berryChecker.checkBerry(berryType);
    }

    private boolean checkPixelmonRoots(Player player, String root) {
        if(pixelmonEntity != null) {
            return isPixelmonRootValid(player, root, pixelmonEntity);
        } else if(pokemon != null) {
            return isPixelmonRootValid(player, root, pokemon);
        } else if(daycareBox != null) {
            return isPixelmonRootValid(player, root, daycareBox);
        } else if(apricornType != null) {
            return isApricornRootValid(root, apricornType);
        } else if(trainer != null) {
            return isTrainerRootValid(root, trainer);
        } else if(berryType != null) {
            return isBerryRootValid(root, berryType);
        } else return true;
    }

    @Override
    public String toString() {
        String root = "none";
        if(pixelmonEntity != null) {
            root = pixelmonEntity.getPokemon().getTranslatedName().getString();
        } else if(pokemon != null) {
            root = pokemon.getTranslatedName().getString();
        } else if(daycareBox != null) {
            root = Arrays.toString(daycareBox);
        } else if(apricornType != null) {
            root = apricornType.toString();
        } else if(trainer != null) {
            root = trainer.getUUID().toString();
        } else if(berryType != null) {
            root = berryType.name();
        }
        return "QuestResult{root='" + root + "'" + "}";
    }
}
