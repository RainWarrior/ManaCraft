package sham1.manacraft.manipulation.tileentity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.Constants;
import sham1.manacraft.reflect.ManaCraftReflection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManaNodeTileEntity extends TileEntity{

    public EnumFacing dir;
    public List<BlockPos> linkedNodes = new ArrayList<>();

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);

        return new S35PacketUpdateTileEntity(pos, 0, tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        if (dir != null) tag.setInteger("dir", dir.getIndex());

        if (!linkedNodes.isEmpty()) {
            NBTTagList nodeList = new NBTTagList();
            linkedNodes.forEach(pos -> {
                NBTTagCompound storeTag = new NBTTagCompound();
                storeTag.setInteger("x", pos.getX());
                storeTag.setInteger("y", pos.getY());
                storeTag.setInteger("z", pos.getZ());

                nodeList.appendTag(storeTag);
            });

            tag.setTag("linkedNodes", nodeList);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if (tag.hasKey("dir")) dir = EnumFacing.getFront(tag.getInteger("dir"));

        if (tag.hasKey("linkedNodes")) {
            NBTTagList nodeList = (NBTTagList) tag.getTag("linkedNodes");

            List<NBTTagCompound> tagList = ManaCraftReflection.getTagList(nodeList).stream()
                    .filter(x -> x instanceof NBTTagCompound)
                    .map(x -> (NBTTagCompound) x)
                    .collect(Collectors.toList());

            tagList.forEach(posTag -> {
                BlockPos pos1 = new BlockPos(posTag.getInteger("x"), posTag.getInteger("y"), posTag.getInteger("z"));
                linkedNodes.add(pos1);
            });
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }
}
