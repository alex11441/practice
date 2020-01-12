package com.conaxgames.practice.arena;

import com.conaxgames.internal.com.mongodb.client.MongoCollection;
import com.conaxgames.internal.com.mongodb.client.model.Filters;
import com.conaxgames.internal.com.mongodb.client.model.ReplaceOptions;
import com.conaxgames.practice.Practice;
import com.conaxgames.practice.arena.schematic.Schematic;
import com.conaxgames.practice.arena.task.ArenaScanTask;
import com.conaxgames.practice.kit.Kit;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    /**
     * Mongo collection that all server kits are saved in.
     */
    private final MongoCollection<Document> arenasCollection
            = Practice.getInstance().getMongoDatabase().getCollection("practice_arenas");

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
        // Ensure the schematics folder exists.
        if (!schematicsFolder.exists()) {
            schematicsFolder.mkdir();
        }

        // Load all arenas from the database.
        arenasCollection.find().iterator().forEachRemaining(document -> {
            arenas.add(Practice.GSON.fromJson(document.toJson(), Arena.class));
        });
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
     * @param increment value to increment the {@code startingX} and {@code startingZ} by after every paste
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

        World world = Bukkit.getWorlds().get(0);

        Schematic schematic = new Schematic(file);
        schematic.pasteSchematic(world, x, z);

        new ArenaScanTask(this, arenaName, world, x, z, schematic)
                .runTaskLaterAsynchronously(Practice.getInstance(), 20L);
    }

    /**
     * Adds an arena to the {@link #arenas} list.
     *
     * @param arena arena to add
     */
    public void addArena(Arena arena) {
        arenas.add(arena);
    }

    /**
     * Saves the {@code arena} to the database.
     *
     * @param arena the arena to save
     */
    public void saveArena(Arena arena) {
        Document document = Document.parse(Practice.GSON.toJson(arena));

        arenasCollection.replaceOne(Filters.eq("_id", arena.getName()),
                document,
                new ReplaceOptions().upsert(true));
    }
}
