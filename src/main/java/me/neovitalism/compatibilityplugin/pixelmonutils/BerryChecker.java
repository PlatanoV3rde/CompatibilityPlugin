package me.neovitalism.compatibilityplugin.pixelmonutils;

import com.pixelmonmod.pixelmon.enums.BerryType;

import java.util.ArrayList;
import java.util.List;

public class BerryChecker {
    private final List<BerryType> berries = new ArrayList<>();

    public BerryChecker(String in) {
        String[] instructions = in.split(",");
        for(String part : instructions) {
            String toParse = part.toUpperCase();
            if(toParse.equals("ANY")) {
                berries.addAll(List.of(BerryType.values()));
            } else {
                try {
                    BerryType berry = BerryType.valueOf(toParse);
                    berries.add(berry);
                } catch(IllegalArgumentException ignored) {}
            }
        }
    }

    public boolean checkBerry(BerryType berry) {
        return berries.contains(berry);
    }
}
