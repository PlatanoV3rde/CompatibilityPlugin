package me.neovitalism.compatibilityplugin.pixelmonutils;

import com.pixelmonmod.pixelmon.api.pokemon.Element;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokerusStrain;
import com.pixelmonmod.pixelmon.api.pokemon.species.gender.Gender;
import com.pixelmonmod.pixelmon.battles.status.StatusType;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpecChecker {
    private boolean abilityCheck = false;
    private boolean bossCheck = false;
    private boolean parent = false;
    private boolean formCheck = false;
    private boolean generationCheck = false;
    private boolean genderCheck = false;
    private boolean hiddenAbilityCheck = false;
    private boolean legendaryCheck = false;
    private boolean mythicalCheck = false;
    private boolean natureCheck = false;
    private boolean notOTCheck = false;
    private boolean otCheck = false;
    private boolean paletteCheck = false;
    private boolean pkrsCheck = false;
    private boolean pokemonCheck = false;
    private boolean shinyCheck = false;
    private boolean statusCheck = false;
    private boolean typeCheck = false;
    private boolean ultraBeastCheck = false;
    private boolean unbreedableCheck = false;
    private boolean uncatchableCheck = false;
    private boolean untradableCheck = false;

    private List<String> abilities;
    private List<String> bosses;
    private List<String> forms;
    private List<Integer> generations;
    private Gender gender;
    private List<String> natures;
    private List<String> palettes;
    private List<String> pokemonList;
    private List<StatusType> statuses;
    private List<Element> types;

    public SpecChecker(String in) {
        String[] instructions = in.split(",");
        for(String part : instructions) {
            String toParse = part.toUpperCase();
            if(toParse.contains("AB=")) {
                if(!abilityCheck) {
                    abilityCheck = true;
                    abilities = new ArrayList<>();
                }
                abilities.add(toParse.replace("AB=", ""));
            } else if(toParse.contains("BOSS=")) {
                if(!bossCheck) {
                    bossCheck = true;
                    bosses = new ArrayList<>();
                }
                bosses.add(toParse.replace("BOSS=", ""));
            } else if(toParse.contains("PARENT")) {
                parent = true;
            } else if(toParse.contains("FORM=")) {
                if(!formCheck) {
                    formCheck = true;
                    forms = new ArrayList<>();
                }
                forms.add(toParse.replace("FORM=", ""));
            } else if(toParse.contains("GEN=")) {
                if(!generationCheck) {
                    generationCheck = true;
                    generations = new ArrayList<>();
                }
                generations.add(Integer.parseInt(toParse.replace("GEN=", "")));
            } else if(toParse.contains("GENDER=")) {
                genderCheck = true;
                gender = SpecParser.genderParser(toParse.replace("GENDER=", ""));
            } else if(toParse.contains("HIDDENAB")) {
                hiddenAbilityCheck = true;
            } else if(toParse.contains("LEGENDARY")) {
                legendaryCheck = true;
                if(!toParse.contains("=ONLY")) {
                    mythicalCheck = true;
                }
            } else if(toParse.contains("MYTHICAL")) {
                mythicalCheck = true;
            } else if(toParse.contains("NATURE=")) {
                if(!natureCheck) {
                    natureCheck = true;
                    natures = new ArrayList<>();
                }
                natures.add(toParse.replace("NATURE=", ""));
            } else if(toParse.contains("OT=")) {
                if(toParse.contains("TRUE")) {
                    otCheck = true;
                } else if(toParse.contains("FALSE")) {
                    notOTCheck = false;
                }
            } else if(toParse.contains("PALETTE=")) {
                if(!paletteCheck) {
                    paletteCheck = true;
                    palettes = new ArrayList<>();
                }
                palettes.add(toParse.replace("PALETTE=", ""));
            } else if(toParse.contains("PKRS")) {
                pkrsCheck = true;
            } else if(toParse.contains("P=")) {
                if(!pokemonCheck) {
                    pokemonCheck = true;
                    pokemonList = new ArrayList<>();
                }
                pokemonList.add(toParse.replace("P=", ""));
            } else if(toParse.contains("SHINY")) {
                shinyCheck = true;
            } else if(toParse.contains("STATUS=")) {
                if (!statusCheck) {
                    statusCheck = true;
                    statuses = new ArrayList<>();
                }
                statuses.add(SpecParser.statusParser(toParse.replace("STATUS=", "")));
            } else if(toParse.contains("TYPE=")) {
                if(!typeCheck) {
                    typeCheck = true;
                    types = new ArrayList<>();
                }
                types.addAll(SpecParser.elementParser(toParse.replace("TYPE=", "")));
            } else if(toParse.contains("ULTRABEAST")) {
                ultraBeastCheck = true;
            } else if(toParse.contains("UNBREEDABLE")) {
                unbreedableCheck = true;
            } else if(toParse.contains("UNCATCHABLE")) {
                uncatchableCheck = true;
            } else if(toParse.contains("UNTRADEABLE")) {
                untradableCheck = true;
            }
        }
    }

    public boolean checkPokemon(PixelmonEntity pixelmon) {
        if(bossCheck) {
            if(!pixelmon.isBossPokemon()) return false;
            if(!bosses.contains("ANY")) {
                if(!bosses.contains(SpecParser.specFormatter(pixelmon.getBossTier().getName()))) return false;
            }
        }
        return checkPokemon(pixelmon.getPokemon());
    }

    public boolean checkPokemon(Pokemon pokemon) {
        if(pokemon == null) return false;
        if(abilityCheck && !abilities.contains(SpecParser.specFormatter(pokemon.getAbility().getName()))) return false;
        if(formCheck && !forms.contains(SpecParser.specFormatter(pokemon.getForm().getName()))) return false;
        if(generationCheck && !generations.contains(pokemon.getSpecies().getGeneration())) return false;
        if(genderCheck && !pokemon.getGender().equals(gender)) return false;
        if(hiddenAbilityCheck && !pokemon.hasHiddenAbility()) return false;
        if(legendaryCheck && !pokemon.getSpecies().isLegendary()) return false;
        if(mythicalCheck && !pokemon.getSpecies().isMythical()) return false;
        if(natureCheck && !natures.contains(SpecParser.specFormatter(pokemon.getNature().name()))) return false;
        if(paletteCheck && !palettes.contains(SpecParser.specFormatter(pokemon.getPalette().getName()))) return false;
        if(pkrsCheck && pokemon.getPokerus().type.equals(PokerusStrain.UNINFECTED)) return false;
        if(pokemonCheck && !pokemonList.contains(SpecParser.specFormatter(pokemon.getSpecies().getName()))) return false;
        if(shinyCheck && !pokemon.getPalette().getName().contains("shiny")) return false;
        if(statusCheck && !statuses.contains(pokemon.getStatus().type)) return false;
        if(typeCheck) {
            boolean hasType = false;
            if(pokemon.getForm() != null && pokemon.getForm().getAbilities() != null) {
                for (Element element : pokemon.getForm().getTypes()) {
                    if (types.contains(element)) {
                        hasType = true;
                        break;
                    }
                }
            }
            if(!hasType) return false;
        }
        if(ultraBeastCheck && !pokemon.isUltraBeast()) return false;
        if(unbreedableCheck && !pokemon.isUnbreedable()) return false;
        if(uncatchableCheck && !pokemon.isUncatchable()) return false;
        return !(untradableCheck && !pokemon.isUntradeable());
    }

    public boolean otCheckRequired() {
        return (otCheck || notOTCheck);
    }

    public boolean otChecker(UUID pokemonOT, UUID ot) {
        if(otCheck) return (pokemonOT.equals(ot));
        if(notOTCheck) return !(pokemonOT.equals(ot));
        return false;
    }

    public boolean checkBox(UUID playerUUID, Pokemon[] daycarePokemon) {
        if(parent) {
            if(checkPokemon(daycarePokemon[0])) {
                if(otCheckRequired()) {
                    return otChecker(daycarePokemon[0].getOriginalTrainerUUID(), playerUUID);
                } else return true;
            } else if(checkPokemon(daycarePokemon[1])) {
                if(otCheckRequired()) {
                    return otChecker(daycarePokemon[1].getOriginalTrainerUUID(), playerUUID);
                } else return true;
            } else return false;
        } else {
            if(checkPokemon(daycarePokemon[2])) {
                if(otCheckRequired()) {
                    return otChecker(daycarePokemon[2].getOriginalTrainerUUID(), playerUUID);
                } else return true;
            } else return false;
        }
    }
}
