package me.neovitalism.compatibilityplugin.pixelmonutils;

import com.pixelmonmod.pixelmon.api.pokemon.Element;
import com.pixelmonmod.pixelmon.api.pokemon.species.gender.Gender;
import com.pixelmonmod.pixelmon.battles.status.StatusType;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SpecParser {
    private static final Map<String, Element> elements = new HashMap<>();
    private static final Map<String, Gender> genders = new HashMap<>();
    private static final Map<String, StatusType> statuses = new HashMap<>();

    public static String specFormatter(String spec) {
        return spec.replaceAll(" ", "").toUpperCase();
    }

    public static Collection<Element> elementParser(String element) {
        if(element.equals("ANY")) return elements.values();
        return Collections.singletonList(elements.getOrDefault(element.toUpperCase(), Element.MYSTERY));
    }

    public static Gender genderParser(String gender) {
        return genders.getOrDefault(gender.toUpperCase(), Gender.NONE);
    }

    public static StatusType statusParser(String status) {
        return statuses.getOrDefault(status.toUpperCase(), StatusType.None);
    }

    static {
        elements.put("NORMAL", Element.NORMAL);
        elements.put("WATER", Element.WATER);
        elements.put("FIRE", Element.FIRE);
        elements.put("GRASS", Element.GRASS);
        elements.put("ELECTRIC", Element.ELECTRIC);
        elements.put("ICE", Element.ICE);
        elements.put("FIGHTING", Element.FIGHTING);
        elements.put("POISON", Element.POISON);
        elements.put("GROUND", Element.GROUND);
        elements.put("FLYING", Element.FLYING);
        elements.put("PSYCHIC", Element.PSYCHIC);
        elements.put("BUG", Element.BUG);
        elements.put("ROCK", Element.ROCK);
        elements.put("GHOST", Element.GHOST);
        elements.put("DARK", Element.DARK);
        elements.put("DRAGON", Element.DRAGON);
        elements.put("STEEL", Element.STEEL);
        elements.put("FAIRY", Element.FAIRY);
        genders.put("MALE", Gender.MALE);
        genders.put("FEMALE", Gender.FEMALE);
        statuses.put("BURNED", StatusType.Burn);
        statuses.put("FROZEN", StatusType.Freeze);
        statuses.put("ASLEEP", StatusType.Sleep);
        statuses.put("POISONED", StatusType.Poison);
        statuses.put("PARALYZED", StatusType.Paralysis);
    }
}
