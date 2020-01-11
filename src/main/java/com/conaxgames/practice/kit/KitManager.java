package com.conaxgames.practice.kit;

import com.conaxgames.internal.com.mongodb.client.MongoCollection;
import com.conaxgames.internal.com.mongodb.client.model.Filters;
import com.conaxgames.internal.com.mongodb.client.model.UpdateOptions;
import com.conaxgames.practice.Practice;
import org.bson.Document;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class KitManager {

    /**
     * Mongo collection that all server kits are saved in.
     */
    private final MongoCollection<Document> kitsCollection
            = Practice.getInstance().getMongoDatabase().getCollection("practice_kits");

    /**
     * All kits loaded on the server.
     */
    private final Map<String, Kit> idToKitMap = new HashMap<>();

    public KitManager() {
        // Load all kits from the database.
        kitsCollection.find().iterator().forEachRemaining(document -> {
            idToKitMap.put(document.getString("_id"), Practice.GSON.fromJson(document.toJson(), Kit.class));
        });
    }

    public Collection<Kit> getKits() {
        return idToKitMap.values();
    }

    /**
     * Attempts to get the kit with the specified {@code id}.
     *
     * @param id the kit ID to lookup
     * @return the kit if found, otherwise null
     */
    public Kit getKitFromId(String id) {
        return idToKitMap.get(id);
    }

    /**
     * Saves all currently loaded kits on the
     * server to the database.
     */
    public void saveAllKits() {
        idToKitMap.values().forEach(this::saveKit);
    }

    /**
     * Saves the {@code kit} to the database.
     *
     * @param kit the kit to save
     */
    public void saveKit(Kit kit) {
        Document document = Document.parse(Practice.GSON.toJson(this));

        kitsCollection.updateOne(Filters.eq("_id", kit.getId()),
                document,
                new UpdateOptions().upsert(true));
    }
}
