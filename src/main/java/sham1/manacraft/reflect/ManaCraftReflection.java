package sham1.manacraft.reflect;

import com.google.common.base.Throwables;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.management.PlayerManager;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;

public final class ManaCraftReflection {
    public static final MethodHandles.Lookup lookup = MethodHandles.lookup();

    private final static MethodHandle getPlayerInstanceHandle;
    private final static MethodHandle playersWatchingChunkFieldHandle;

    static {
        boolean isDev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        try {
            Class playerInstanceClass = Class.forName("net.minecraft.server.management.PlayerManager$PlayerInstance");
            getPlayerInstanceHandle = lookup.findVirtual(PlayerManager.class, isDev ? "getPlayerInstance" : "func_72690_a", MethodType.methodType(playerInstanceClass, int.class, int.class, boolean.class));
            playersWatchingChunkFieldHandle = lookup.findGetter(playerInstanceClass, isDev ? "playersWatchingChunk" : "field_73263_b", List.class);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public static Object getPlayerInstanceFromChunk(PlayerManager manager, int chunkX, int chunkY) throws Throwable{
        return getPlayerInstanceHandle.invokeExact(manager, chunkX, chunkY, false);
    }

    @SuppressWarnings("unchecked")
    public static List<EntityPlayerMP> getPlayersWatchingChunk(Object playerInstance) throws Throwable {
        return (List<EntityPlayerMP>) playersWatchingChunkFieldHandle.invokeExact(playerInstance);
    }
}
