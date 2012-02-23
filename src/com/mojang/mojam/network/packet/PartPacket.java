package com.mojang.mojam.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.mojang.mojam.network.Packet;

public class PartPacket extends Packet {
	private String message;
	
	public PartPacket() {
		this("");
	}
	
	public PartPacket(String message) {
		this.message = "";
	}

	@Override
	public void read(DataInputStream dis) throws IOException {
		message = "";
		int len = dis.readInt();
		for (int i = 0; i < len; i++)
			message += dis.readChar();
	}

	@Override
	public void write(DataOutputStream dos) throws IOException {
		int len = message.length();
		dos.writeInt(len);
		for (int i = 0; i < len; i++)
			dos.writeChars(message);
	}
	
	public String getMessage() {
		return message;
	}

}
