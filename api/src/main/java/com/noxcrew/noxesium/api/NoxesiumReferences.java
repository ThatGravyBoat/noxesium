package com.noxcrew.noxesium.api;

/**
 * Stores various constant values for Noxesium.
 */
public class NoxesiumReferences {

    /**
     * The current protocol version of the mod. Servers can use this version to determine which functionality
     * of Noxesium is available on the client. The protocol version will increment every full release, as such
     * ít is recommended to work with >= comparisons.
     */
    public static final int VERSION = 13;

    /**
     * The name space to use for Noxesium.
     */
    public static final String NAMESPACE = "noxesium";

    /**
     * The name of the custom NBT component used by Bukkit for custom values.
     */
    public static final String BUKKIT_COMPOUND_ID = "PublicBukkitValues";

    /**
     * The NBT tag used for an immovable item.
     */
    public static final String IMMOVABLE_TAG = NoxesiumReferences.NAMESPACE + ":immovable";
}
