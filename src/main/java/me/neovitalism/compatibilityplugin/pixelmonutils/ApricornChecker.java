package me.neovitalism.compatibilityplugin.pixelmonutils;

import com.pixelmonmod.pixelmon.enums.items.ApricornType;

import java.util.Locale;

public class ApricornChecker {
    private boolean red;
    private boolean yellow;
    private boolean blue;
    private boolean green;
    private boolean pink;
    private boolean white;
    private boolean black;

    public ApricornChecker(String in) {
        String[] instructions = in.split(",");
        for(String part : instructions) {
            String toParse = part.toUpperCase(Locale.ENGLISH);
            switch (toParse) {
                case "ANY":
                    red = true;
                    yellow = true;
                    blue = true;
                    green = true;
                    pink = true;
                    white = true;
                    black = true;
                    return;
                case "RED":
                    red = true;
                    return;
                case "YELLOW":
                    yellow = true;
                    return;
                case "BLUE":
                    blue = true;
                    return;
                case "GREEN":
                    green = true;
                    return;
                case "PINK":
                    pink = true;
                    return;
                case "WHITE":
                    white = true;
                    return;
                case "BLACK":
                    black = true;
                    return;
            }
        }
    }

    public boolean checkApricorn(ApricornType apricorn) {
        switch (apricorn.name()) {
            case "RED":
                return red;
            case "YELLOW":
                return yellow;
            case "BLUE":
                return blue;
            case "GREEN":
                return green;
            case "PINK":
                return pink;
            case "WHITE":
                return white;
            case "BLACK":
                return black;
            default:
                return false;
        }
    }
}
