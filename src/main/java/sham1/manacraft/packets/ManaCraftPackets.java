package sham1.manacraft.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import sham1.manacraft.common.CommonProxy;

public class ManaCraftPackets {
    public static SimpleNetworkWrapper packetNetwork;

    public static void registerNetwork() {
        packetNetwork = NetworkRegistry.INSTANCE.newSimpleChannel("manacraft");
    }

    public static void registerPackets() {
        packetNetwork.registerMessage(ManaUpdatePacket.Handler.class, ManaUpdatePacket.class, 0, Side.CLIENT);
    }

    public static void sendPacketTo(IMessage packet, EntityPlayerMP player) {
        packetNetwork.sendTo(packet, player);
    }
}
