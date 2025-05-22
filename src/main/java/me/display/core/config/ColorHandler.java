package me.display.core.config;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class ColorHandler {

    private final TextColor primaryColor;
    private final TextColor secondaryColor;

    public ColorHandler(String primaryColorString, String secondaryColorString) {
        this.primaryColor = parseColor(primaryColorString);
        this.secondaryColor = parseColor(secondaryColorString);
    }

    private TextColor parseColor(String colorString) {
        if (colorString == null) return NamedTextColor.WHITE;

        // Try named colors first
        NamedTextColor named = NamedTextColor.NAMES.value(colorString.toLowerCase());
        if (named != null) return named;

        // Try parsing as hex
        if (colorString.startsWith("#")) {
            try {
                return TextColor.fromHexString(colorString);
            } catch (IllegalArgumentException e) {
                // fall through to default
            }
        }

        return NamedTextColor.WHITE;
    }

    public TextColor getPrimaryColor() {
        return primaryColor;
    }

    public TextColor getSecondaryColor() {
        return secondaryColor;
    }
}
