package sham1.manacraft.api;

import net.minecraft.util.BlockPos;

import java.io.Serializable;

public class SerializableBlockPos implements Serializable{

    private static final long serialVersionUID = -7955296919319062483L;

    private  int x, y, z;

    public SerializableBlockPos() {}

    public SerializableBlockPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockPos toBlockPos() {
        return new BlockPos(x, y, z);
    }

    public static SerializableBlockPos fromBlockPos(BlockPos pos) {
        return new SerializableBlockPos(pos.getX(), pos.getY(), pos.getZ());
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SerializableBlockPos that = (SerializableBlockPos) o;

        return x == that.x && y == that.y && z == that.z;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    @Override
    public String toString() {
        return "SerializableBlockPos{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
