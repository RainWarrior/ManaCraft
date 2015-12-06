package sham1.manacraft.manipulation.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ITickable;
import sham1.manacraft.reflect.ManaCraftReflection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManaNodeTileEntity extends TileEntity implements ITickable{

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

        if (!linkedNodes.isEmpty()) {
            NBTTagList nodeList = new NBTTagList();
            linkedNodes.forEach(pos1 -> {
                TileEntity temp = worldObj.getTileEntity(pos1);
                if (temp != null && temp instanceof ManaNodeTileEntity) {
                    NBTTagCompound storeTag = new NBTTagCompound();
                    storeTag.setInteger("x", pos1.getX());
                    storeTag.setInteger("y", pos1.getY());
                    storeTag.setInteger("z", pos1.getZ());

                    nodeList.appendTag(storeTag);
                }
            });

            tag.setTag("linkedNodes", nodeList);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if (tag.hasKey("linkedNodes")) {
            NBTTagList nodeList = (NBTTagList) tag.getTag("linkedNodes");

            List<NBTTagCompound> tagList = ManaCraftReflection.getTagList(nodeList).stream()
                    .filter(x -> x instanceof NBTTagCompound)
                    .map(x -> (NBTTagCompound) x)
                    .collect(Collectors.toList());

            tagList.forEach(posTag -> {
                BlockPos pos1 = new BlockPos(posTag.getInteger("x"), posTag.getInteger("y"), posTag.getInteger("z"));
                TileEntity temp = worldObj.getTileEntity(pos1);
                if (temp != null && temp instanceof ManaNodeTileEntity)
                    linkedNodes.add(pos1);
            });
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void update() {
        if (!worldObj.isRemote) {

        }
    }

    public void handShake(ManaNodeTileEntity other, EntityPlayer shaker) {
        BlockPos otherPos = other.getPos();

        if (getDistanceSq(otherPos.getX(), otherPos.getY(), otherPos.getZ()) > 64) {
            shaker.addChatComponentMessage(new ChatComponentTranslation("node.too.far.handshake"));
            return;
        }

        if (linkedNodes.contains(otherPos)) {
            shaker.addChatComponentMessage(new ChatComponentTranslation("node.already.linked"));
            return;
        }

        linkedNodes.add(otherPos);
        other.addMyPos(pos);

        shaker.addChatComponentMessage(new ChatComponentTranslation("node.link.success"));
    }

    private void addMyPos(BlockPos otherPos) {
        linkedNodes.add(otherPos);
    }
}
