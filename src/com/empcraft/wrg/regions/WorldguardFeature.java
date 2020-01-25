
package com.empcraft.wrg.regions;

import com.empcraft.wrg.WorldeditRegions;
import com.empcraft.wrg.object.AbstractRegion;
import com.empcraft.wrg.object.ChunkLoc;
import com.empcraft.wrg.object.CuboidRegionWrapper;
import com.empcraft.wrg.util.MainUtil;
import com.empcraft.wrg.util.VaultHandler;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.util.profile.cache.ProfileCache;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class WorldguardFeature extends AbstractRegion {
    public static WorldGuardPlugin worldguard = null;
    public static ProfileCache cache;
    WorldeditRegions               plugin;

    private WorldGuardPlugin getWorldGuard() {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if ((plugin == null) || !(plugin instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldGuardPlugin) plugin;
    }

    public WorldguardFeature(final Plugin p2, final WorldeditRegions p3) {
        worldguard = getWorldGuard();
        cache = worldguard.getProfileCache();
        this.plugin = p3;

    }
    
    public static void temporaryHighlight(final Vector2D bottom, final Vector2D top, final Player player, int id, long time) {
        highlightRegion(bottom, top, player, id);
        Bukkit.getScheduler().runTaskLater(WorldeditRegions.plugin, new Runnable() {
            @Override
            public void run() {
                highlightRegion(bottom, top, player, -1);
            }
        }, time);
    }
    
    public static void sendBlock(Player player, Location loc, int id) {
        if (id != -1) {
            player.sendBlockChange(loc, id, (byte) 0);
        }
        else {
            Block block = loc.getBlock();
            id = block.getTypeId();
            byte data = block.getData();
            player.sendBlockChange(loc, id, data);
        }
    }
    
    public static void highlightRegion(Vector2D bottom, Vector2D top, Player player, int id) {
        int view_distance = Bukkit.getViewDistance();
        Chunk playerChunk = player.getLocation().getChunk();
        int pcx = playerChunk.getX();
        int pcz = playerChunk.getZ();
        World world = player.getWorld();
        HashMap<ChunkLoc, Chunk> chunks = new HashMap<>();
        int x, z;
        z = bottom.getBlockZ();
        for (x = bottom.getBlockX(); x <= (top.getBlockX() - 1); x++) {
            ChunkLoc loc = new ChunkLoc(x >> 4, z >> 4);
            Chunk c;
            if (chunks.containsKey(loc)) {
                c = chunks.get(loc);
                if (c == null) {
                    continue;
                }
            }
            else {
                if (Math.abs(pcx - loc.x) > view_distance || Math.abs(pcz - loc.z) > view_distance) {
                    chunks.put(loc, null);
                    continue;
                }
                c = world.getChunkAt(loc.x, loc.z);
                if (!c.isLoaded()) {
                    chunks.put(loc, null);
                    continue;
                }
                else {
                    chunks.put(loc, null);
                }
            }
            Location l = new Location(world, x, world.getHighestBlockYAt(x, z), z);
            sendBlock(player, l, id);
        }
        x = top.getBlockX();
        for (z = bottom.getBlockZ(); z <= (top.getBlockZ() - 1); z++) {
            ChunkLoc loc = new ChunkLoc(x >> 4, z >> 4);
            Chunk c;
            if (chunks.containsKey(loc)) {
                c = chunks.get(loc);
                if (c == null) {
                    continue;
                }
            }
            else {
                if (Math.abs(pcx - loc.x) > view_distance || Math.abs(pcz - loc.z) > view_distance) {
                    chunks.put(loc, null);
                    continue;
                }
                c = world.getChunkAt(loc.x, loc.z);
                if (!c.isLoaded()) {
                    chunks.put(loc, null);
                    continue;
                }
                else {
                    chunks.put(loc, null);
                }
            }
            Location l = new Location(world, x, world.getHighestBlockYAt(x, z), z);
            sendBlock(player, l, id);
        }
        z = top.getBlockZ();
        for (x = top.getBlockX(); x >= (bottom.getBlockX() + 1); x--) {
            ChunkLoc loc = new ChunkLoc(x >> 4, z >> 4);
            Chunk c;
            if (chunks.containsKey(loc)) {
                c = chunks.get(loc);
                if (c == null) {
                    continue;
                }
            }
            else {
                if (Math.abs(pcx - loc.x) > view_distance || Math.abs(pcz - loc.z) > view_distance) {
                    chunks.put(loc, null);
                    continue;
                }
                c = world.getChunkAt(loc.x, loc.z);
                if (!c.isLoaded()) {
                    chunks.put(loc, null);
                    continue;
                }
                else {
                    chunks.put(loc, null);
                }
            }
            Location l = new Location(world, x, world.getHighestBlockYAt(x, z), z);
            sendBlock(player, l, id);
        }
        x = bottom.getBlockX();
        for (z = top.getBlockZ(); z >= (bottom.getBlockZ() + 1); z--) {
            ChunkLoc loc = new ChunkLoc(x >> 4, z >> 4);
            Chunk c;
            if (chunks.containsKey(loc)) {
                c = chunks.get(loc);
                if (c == null) {
                    continue;
                }
            }
            else {
                if (Math.abs(pcx - loc.x) > view_distance || Math.abs(pcz - loc.z) > view_distance) {
                    chunks.put(loc, null);
                    continue;
                }
                c = world.getChunkAt(loc.x, loc.z);
                if (!c.isLoaded()) {
                    chunks.put(loc, null);
                    continue;
                }
                else {
                    chunks.put(loc, null);
                }
            }
            Location l = new Location(world, x, world.getHighestBlockYAt(x, z), z);
            sendBlock(player, l, id);
        }
    }

    public ProtectedRegion isowner(final Player player) {
        final LocalPlayer localplayer = WorldguardFeature.worldguard.wrapPlayer(player);
        final RegionManager manager = WorldguardFeature.worldguard.getRegionManager(player.getWorld());
        final ProtectedRegion myregion = manager.getRegion("__global__");
        final ApplicableRegionSet regions = manager.getApplicableRegions(player.getLocation());
        if ((myregion != null) && (myregion.isOwner(localplayer) || (myregion.isMember(localplayer) && MainUtil.hasPermission(player, "wrg.WorldguardFeature.worldguard.member")))) {
            final BlockVector pt1 = new BlockVector(Integer.MIN_VALUE, 0, Integer.MIN_VALUE);
            final BlockVector pt2 = new BlockVector(Integer.MAX_VALUE, 256, Integer.MAX_VALUE);
            return new ProtectedCuboidRegion("__global__-" + player.getWorld().getName(), pt1, pt2);
        }
        for (final ProtectedRegion region : regions) {
            if (region.isOwner(localplayer)) {
                return region;
            }
            if (region.isMember(localplayer)) {
                if (MainUtil.hasPermission(player, "wrg.WorldguardFeature.worldguard.member")) {
                    return region;
                }
            }
            else if (region.getId().toLowerCase().equals(player.getName().toLowerCase())) {
                return region;
            }
            else if (region.getId().toLowerCase().contains(player.getName().toLowerCase() + "//")) {
                return region;
            }
            else if (region.isOwner("*")) {
                return region;
            }
            if (VaultHandler.enabled) {
                final String[] groups = VaultHandler.getGroup(player);
                boolean hasPerm = false;
                if (MainUtil.hasPermission(player, "wrg.WorldguardFeature.worldguard.member")) {
                    hasPerm = true;
                }
                for (final String group : groups) {
                    final String regionGroups = region.getOwners().toGroupsString();
                    if (regionGroups.contains("*" + group)) {
                        return region;
                    }
                    else if (hasPerm) {
                        final String regionGroupMembers = region.getMembers().toGroupsString();
                        if (regionGroupMembers.contains("*" + group)) {
                            return region;
                        }
                    }
                }
            }
        }
        return null;
    }

    public ProtectedRegion getregion(final Player player, final BlockVector location) {
        final com.sk89q.worldguard.LocalPlayer localplayer = WorldguardFeature.worldguard.wrapPlayer(player);
        final ApplicableRegionSet regions = WorldguardFeature.worldguard.getRegionManager(player.getWorld()).getApplicableRegions(location);
        for (final ProtectedRegion region : regions) {
            if (region.isOwner(localplayer)) {
                return region;
            }
            else if (region.getId().toLowerCase().equals(player.getName().toLowerCase())) {
                return region;
            }
            else if (region.getId().toLowerCase().contains(player.getName().toLowerCase() + "//")) {
                return region;
            }
            else if (region.isOwner("*")) {
                return region;
            }
        }
        return null;
    }

    @Override
    public CuboidRegionWrapper getcuboid(final Player player) {
        final ProtectedRegion myregion = isowner(player);
        if (myregion != null) {
            final CuboidRegion cuboid = new CuboidRegion(myregion.getMinimumPoint(), myregion.getMaximumPoint());
            return new CuboidRegionWrapper(cuboid, myregion.getId());
        }
        else {
            return null;
        }

    }

    @Override
    public boolean hasPermission(final Player player) {
        return MainUtil.hasPermission(player, "wrg.worldguard");
    }

}
