package me.toples.playerinfogui.utilities;

import me.toples.playerinfogui.PlayerInfoGUI;

public class Description {
    public static String getVersion() {
        return PlayerInfoGUI.getInstance().getDescription().getVersion();
    }

    public static String getAuthor() {
        return PlayerInfoGUI.getInstance().getDescription().getAuthors().toString().replace("[", "").replace("]", "");
    }

    public static String getName() {
        return PlayerInfoGUI.getInstance().getDescription().getName();
    }
}
