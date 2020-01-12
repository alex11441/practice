package com.conaxgames.practice.arena;

import com.conaxgames.practice.Practice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    /**
     * All of the loaded {@link Arena}s.
     */
    private final List<Arena> arenas = new ArrayList<>();

    /**
     * Folder with every schematic used for generating arenas.
     *
     * @see #createArena(String, String, int, int)
     */
    private final File schematicsFolder = new File(Practice.getInstance().getDataFolder(), "schematics");

    public ArenaManager() {
        if (!schematicsFolder.exists()) {
            schematicsFolder.mkdir();
        }
    }

    /**
     * Creates an arena with the given {@code schematicName} a
     * specified number of times.
     *
     * @param schematicName the name of the schematic file to paste
     * @param times # of times to paste the schematic
     * @param startingX X coordinate to start pasting at
     * @param startingZ Z coordinate to start pasting at
     * @param incrementX whether or not to increment the X coordinate
     * @param incrementZ whether or not to increment the Z coordinate
     * @param increment value to increment the {@code startingX} and {@code startingZ} after every paste
     */
    public void createArenas(String schematicName, int times,
                             int startingX, int startingZ,
                             boolean incrementX, boolean incrementZ, int increment) {
        int currentX = startingX;
        int currentZ = startingZ;

        for (int i = 0; i < times; i++) {
            createArena(schematicName, schematicName + i, currentX, currentZ);

            if (incrementX) {
                currentX += increment;
            }

            if (incrementZ) {
                currentZ += increment;
            }
        }
    }

    /**
     * Loads a schematic and pastes it at the specified
     * {@code x} and {@code z}. After pasted, an ArenaScanTask
     * is created to search for the A and B locations, then
     * saves the arena.
     *
     * @param schematicName the name of the schematic file to paste
     * @param arenaName specified arena name or null
     */
    public void createArena(String schematicName, String arenaName, int x, int z) {
        File file = new File(this.schematicsFolder, schematicName);
        if (!file.exists()) {
            return;
        }


    }

}
