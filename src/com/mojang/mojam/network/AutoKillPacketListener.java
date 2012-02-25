package com.mojang.mojam.network;

import com.mojang.mojam.network.packet.PartPacket;


public class AutoKillPacketListener implements PacketListener {
	private NetworkPacketLink link;
	
	public AutoKillPacketListener(NetworkPacketLink link) {
		this.link = link;
	}
	
    public void handle(Packet packet) {
    	if (packet instanceof PartPacket) {
    		if (link != null)
    			link.disconnect();
    	}
    }

}
