package com.mojang.mojam.network;

import com.mojang.mojam.network.packet.StartGamePacket;


public interface PacketLink {

    public void sendPacket(Packet packet);
    
    public void sendStartPacket(StartGamePacket startGamePacket);

    public void tick();

    public void setPacketListener(PacketListener packetListener);


}
