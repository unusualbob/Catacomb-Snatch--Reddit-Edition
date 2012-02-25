package com.mojang.mojam.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.mojang.mojam.network.Packet;

public class JoinPacket extends Packet {
	private int numPlayers = 0;
	
	public JoinPacket() {
	}
	
	public JoinPacket(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	@Override
	public void read(DataInputStream dis) throws IOException {
		numPlayers = dis.readInt();
	}

	@Override
	public void write(DataOutputStream dos) throws IOException {
		dos.writeInt(numPlayers);
	}

	public int getNumPlayers() {
		return numPlayers;
	}

}
