package sham1.manacraft.client;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import sham1.manacraft.common.CommonProxy;
import sham1.manacraft.packets.ManaUpdatePacket;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer() {

    }

    @Override
    public void registerBlockItemRenderer() {

    }

    @Override
    public void registerClientSidePackets(SimpleNetworkWrapper packetNetwork) {
        packetNetwork.registerMessage(ManaUpdatePacket.Handler.class, ManaUpdatePacket.class, 0, Side.CLIENT);
    }

    @Override
    public void registerServerSidePackets(SimpleNetworkWrapper packetNetwork) {
        // NO OP
    }
}
