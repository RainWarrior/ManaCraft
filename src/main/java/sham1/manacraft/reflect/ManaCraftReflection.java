package sham1.manacraft.reflect;

import com.google.common.base.Throwables;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.management.PlayerManager;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public final class ManaCraftReflection {

    private final static MethodHandle getPlayerInstanceHandle;
    private final static MethodHandle playersWatchingChunkFieldHandle;

    private final static MethodHandle tagListFieldHandle;

    static {
        boolean isDev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        try {
            Class playerInstanceClass = Class.forName("net.minecraft.server.management.PlayerManager$PlayerInstance");
            Method getPlayerInstanceMethod = PlayerManager.class.getDeclaredMethod(isDev ? "getPlayerInstance" : "func_72690_a", playerInstanceClass, int.class, int.class, boolean.class);
            getPlayerInstanceMethod.setAccessible(true);

            getPlayerInstanceHandle = MethodHandles.publicLookup().unreflect(getPlayerInstanceMethod);

            Field playersWatchingChunkField = playerInstanceClass.getDeclaredField(isDev ? "playersWatchingChunk" : "field_73263_b");
            playersWatchingChunkField.setAccessible(true);

            playersWatchingChunkFieldHandle = MethodHandles.publicLookup().unreflectGetter(playersWatchingChunkField);

            Field tagListField = NBTTagList.class.getDeclaredField(isDev ? "tagList" : "field_74747_a");
            tagListField.setAccessible(true);

            tagListFieldHandle = MethodHandles.publicLookup().unreflectGetter(tagListField);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public static Object getPlayerInstanceFromChunk(PlayerManager manager, int chunkX, int chunkY) {
        try {
            return getPlayerInstanceHandle.invokeExact(manager, chunkX, chunkY, false);
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<EntityPlayerMP> getPlayersWatchingChunk(Object playerInstance) {
        try {
            return (List<EntityPlayerMP>) playersWatchingChunkFieldHandle.invokeExact(playerInstance);
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<NBTBase> getTagList (NBTTagList tagList) {
        try {
            return (List<NBTBase>) tagListFieldHandle.invokeExact(tagList);
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }
}
