package me.neovitalism.compatibilityplugin.battlepass;

import io.github.battlepass.BattlePlugin;
import me.neovitalism.compatibilityplugin.battlepass.quests.*;
import net.advancedplugins.bp.impl.actions.ActionRegistry;

public class BattlePassHook {
    public static void register() {
        ActionRegistry actionRegistry = BattlePlugin.getApi().getActionRegistry();
        actionRegistry.quest(BreedPokemonQuest::new);
        actionRegistry.quest(CatchPokemonQuest::new);
        actionRegistry.quest(EvolveFromPokemonQuest::new);
        actionRegistry.quest(EvolveToPokemonQuest::new);
        actionRegistry.quest(HatchPokemonQuest::new);
        actionRegistry.quest(KillPokemonQuest::new);
        actionRegistry.quest(LevelPokemonQuest::new);
        actionRegistry.quest(PickApricornQuest::new);
        actionRegistry.quest(PickBerryQuest::new);
        actionRegistry.quest(TakePictureQuest::new);
        actionRegistry.quest(DefeatTrainerQuest::new);
    }
}
