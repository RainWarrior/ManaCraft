package sham1.manacraft.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import sham1.manacraft.common.CommonProxy;

public class ManaCraftPackets {
    public static SimpleNetworkWrapper packetNetwork;

    public static void registerNetwork() {
        packetNetwork = NetworkRegistry.INSTANCE.newSimpleChannel("manacraft");
    }

    public static void registerPackets(CommonProxy proxy) {
        proxy.registerServerSidePackets(packetNetwork);
        proxy.registerClientSidePackets(packetNetwork);
    }

    public static void sendPacketTo(IMessage packet, EntityPlayerMP player) {
        packetNetwork.sendTo(packet, player);
    }
}
