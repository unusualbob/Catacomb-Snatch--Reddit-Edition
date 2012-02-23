package com.mojang.mojam.network.packet;

import java.io.*;

import com.mojang.mojam.network.Packet;

public class StartGamePacket extends Packet {
    private long gameSeed;
    private int numPlayers;
    private int localId;

    public StartGamePacket() {
    }

    public StartGamePacket(long gameSeed, int numPlayers, int localId) {
        this.gameSeed = gameSeed;
        this.numPlayers = numPlayers;
        this.localId = localId;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        gameSeed = dis.readLong();
        numPlayers = dis.readInt();
        localId = dis.readInt();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeLong(gameSeed);
        dos.writeInt(numPlayers);
        dos.writeInt(localId);
    }

    public long getGameSeed() {
        return gameSeed;
    }
    
    public int getNumPlayers() {
    	return numPlayers;
    }

    public int getLocalId() {
    	return localId;
    }

    public void setLocalId(int localId) {
    	this.localId = localId;
    }
}
