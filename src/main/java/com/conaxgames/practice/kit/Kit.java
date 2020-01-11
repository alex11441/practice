package com.conaxgames.practice.kit;

import com.google.common.base.Preconditions;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter @Setter
public class Kit {

    /**
     * The internal ID of this kit. This field is mainly
     * used to save and load from the database and distinguish
     * this kit from others.
     */
    @SerializedName("_id") private String id;

    /**
     * The name displayed to players whenever used.
     */
    private String displayName;

    /**
     * The kit's default items that are given to players.
     */
    private KitItems defaultKitItems;

    /**
     * Determines if this kit is playable, editable, etc.
     */
    private boolean enabled = true;

    /**
     * Determines if players are allowed to queue for this kit
     * in ranked.
     */
    private boolean ranked = true;

    /**
     * Determines if players are allowed to build in arenas
     * in this kit.
     */
    private boolean allowBuilding = false;

    /**
     * Applies the kit's default armor and inventory
     * contents to the specified {@code player}.
     *
     * If the kit does not have a {@link #defaultKitItems},
     * this method will throw a {@link NullPointerException}.
     *
     * @param player the player to give the kit to
     */
    public void apply(Player player) {
        Preconditions.checkNotNull(defaultKitItems, "defaultKitItems");
        defaultKitItems.apply(player);
    }
}
