package com.conaxgames.practice;

import com.conaxgames.CorePlugin;
import com.conaxgames.internal.com.mongodb.client.MongoDatabase;
import com.conaxgames.practice.kit.KitManager;
import com.conaxgames.practice.lobby.LobbyManager;
import com.conaxgames.practice.queue.QueueManager;
import com.conaxgames.practice.util.adapter.ItemStackTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Practice extends JavaPlugin {

    @Getter private static Practice instance;
    public static final Gson GSON = new GsonBuilder()
            .registerTypeHierarchyAdapter(ItemStack.class, new ItemStackTypeAdapter())
            .serializeNulls()
            .create();

    private MongoDatabase mongoDatabase;

    private LobbyManager lobbyManager;
    private KitManager kitManager;
    private QueueManager queueManager;

    public void onEnable() {
        instance = this;

        this.mongoDatabase = CorePlugin.getInstance().getCoreDatabase();

        this.lobbyManager = new LobbyManager();
        this.kitManager = new KitManager();
        this.queueManager = new QueueManager();
    }

    public void onDisable() {
        kitManager.saveAllKits();
    }
}
