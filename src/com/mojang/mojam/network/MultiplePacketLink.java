package com.mojang.mojam.network;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.mojang.mojam.network.packet.StartGamePacket;

public class MultiplePacketLink implements PacketLink {
	PacketLink[] packetLinks;
	
	public MultiplePacketLink(Socket[] socket) throws IOException {
		packetLinks = new NetworkPacketLink[socket.length];
		for (int i = 0; i < socket.length; i++) {
			packetLinks[i] = new NetworkPacketLink(socket[i]);
		}
	}
	
	public MultiplePacketLink(PacketLink[] packetLinks) throws IOException {
		this.packetLinks = new PacketLink[packetLinks.length];
		for (int i = 0; i < packetLinks.length; i++) {
			this.packetLinks[i] = packetLinks[i];
		}
	}

	public MultiplePacketLink(List<PacketLink> packetLinks) throws IOException {
		this.packetLinks = new PacketLink[packetLinks.size()];
		for (int i = 0; i < packetLinks.size(); i++) {
			this.packetLinks[i] = packetLinks.get(i);
		}
	}

	@Override
	public void sendPacket(Packet packet) {
		for (int i = 0; i < packetLinks.length; i++) {
			packetLinks[i].sendPacket(packet);
		}
	}

	public void sendPacketExcept(Packet packet, int playerId) {
		for (int i = 0; i < packetLinks.length; i++) {
			if (i != (playerId-1))
				packetLinks[i].sendPacket(packet);
		}
	}

	@Override
	public void sendStartPacket(StartGamePacket startGamePacket) {
		for (int i = 0; i < packetLinks.length; i++) {
			StartGamePacket newPacket =
					new StartGamePacket(startGamePacket.getGameSeed(), startGamePacket.getNumPlayers(), i+1);
			packetLinks[i].sendPacket(newPacket);
		}
	}
	
	@Override
	public void tick() {
		for (int i = 0; i < packetLinks.length; i++) {
			packetLinks[i].tick();
		}
	}

	@Override
	public void setPacketListener(PacketListener packetListener) {
		for (int i = 0; i < packetLinks.length; i++) {
			packetLinks[i].setPacketListener(packetListener);
		}
	}

	@Override
	public boolean connectionDropped() {
		for (int i = 0; i < packetLinks.length; i++) {
			if (packetLinks[i].connectionDropped()) return true;
		}
		return false;
	}

	@Override
	public void disconnect() {
		for (int i = 0; i < packetLinks.length; i++) {
			packetLinks[i].disconnect();
		}
	}

}
