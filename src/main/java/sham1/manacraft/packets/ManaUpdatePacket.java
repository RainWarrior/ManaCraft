package sham1.manacraft.packets;

import com.google.common.base.Throwables;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import sham1.manacraft.api.GenericPair;
import sham1.manacraft.api.IManaStorage;
import sham1.manacraft.api.SerializableBlockPos;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * A packet that will be automatically sent to any clients that needs to receive an update in mana amount
 *
 * @author Sham1
 * @version 1.8.8-0.0.0.0
 */
public class ManaUpdatePacket implements IMessage{

    ManaUpdateJavabean bean;

    public ManaUpdatePacket(ManaUpdateJavabean bean) {
        this.bean = bean;
    }

    public ManaUpdatePacket() { }

    @Override
    public void fromBytes(ByteBuf buf) {
        int len = buf.readInt();
        byte[] compressedBytes = new byte[len];

        for (int i = 0; i < len; i++) compressedBytes[i] = buf.readByte();

        try {
            ObjectInputStream obj = new ObjectInputStream(new GZIPInputStream(new ByteArrayInputStream(compressedBytes)));
            bean = (ManaUpdateJavabean) obj.readObject();
            obj.close();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try {
            GZIPOutputStream compressor = new GZIPOutputStream(bytes);
            ObjectOutputStream objStream = new ObjectOutputStream(compressor);
            objStream.writeObject(bean);
            objStream.close();
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }

        buf.writeInt(bytes.size());
        buf.writeBytes(bytes.toByteArray());
    }

    public static class Handler implements IMessageHandler<ManaUpdatePacket, IMessage> {
        @Override
        public IMessage onMessage(ManaUpdatePacket message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                BlockPos pos = message.bean.pos.toBlockPos();
                TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(pos);

                if (te != null && te instanceof IManaStorage) {
                    IManaStorage manaStorage = (IManaStorage) te;

                    manaStorage.setManaStored(message.bean.facing, message.bean.amount);
                }
            });
            return null;
        }
    }

    public static class ManaUpdateJavabean implements Serializable {

        private SerializableBlockPos pos;
        private EnumFacing facing;
        private int amount;

        public ManaUpdateJavabean() {}

        public ManaUpdateJavabean(BlockPos pos, EnumFacing facing, int amount) {
            this.facing = facing;
            this.pos = SerializableBlockPos.fromBlockPos(pos);
            this.amount = amount;
        }

        public EnumFacing getFacing() {
            return facing;
        }

        public void setFacing(EnumFacing facing) {
            this.facing = facing;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public SerializableBlockPos getPos() {
            return pos;
        }

        public void setPos(SerializableBlockPos pos) {
            this.pos = pos;
        }
    }
}
