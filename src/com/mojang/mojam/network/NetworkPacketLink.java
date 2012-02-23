package com.mojang.mojam.network;

import java.io.*;
import java.net.Socket;
import java.util.*;

import com.mojang.mojam.network.packet.PartPacket;
import com.mojang.mojam.network.packet.StartGamePacket;

public class NetworkPacketLink implements PacketLink {

    private static final int SEND_BUFFER_SIZE = 1024 * 5;

    private Socket socket;

    private Object writeLock = new Object();

    private List<Packet> incoming = Collections.synchronizedList(new ArrayList<Packet>());
    private List<Packet> outgoing = Collections.synchronizedList(new ArrayList<Packet>());

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private Thread writeThread;
    private Thread readThread;

    private boolean isRunning = true;
    private boolean isQuitting = false;
    private boolean isDisconnected = false;
    public boolean bConnectionDropped;

    private PacketListener packetListener;

    public NetworkPacketLink(Socket socket) throws IOException {
        this.socket = socket;

        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), SEND_BUFFER_SIZE));

        readThread = new Thread("Read thread") {
            public void run() {
                try {
                    while (isRunning && !isQuitting) {
                        while (readTick())
                            ;

                        try {
                            sleep(2L);
                        } catch (InterruptedException e) {
                        }
                    }
                } catch (Exception e) {
                }
            }
        };

        writeThread = new Thread("Write thread") {
            public void run() {
                try {
                    while (isRunning) {
                        while (writeTick())
                            ;

                        try {
                            if (outputStream != null) outputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }

                        try {
                            sleep(2L);
                        } catch (InterruptedException e) {
                        }
                    }
                } catch (Exception e) {
                }
                if (isQuitting) {
                	die();
                }
            }
        };

        readThread.start();
        writeThread.start();
    }

    public void tick() {
        int max = 1000;
        while (!incoming.isEmpty() && max-- >= 0) {
            Packet packet = incoming.remove(0);
            if (packet instanceof PartPacket) {
            	isQuitting = true;
            }
            if (packetListener != null) {
                packet.handle(packetListener);
            }
        }
    }

    public void sendPacket(Packet packet) {
        if (isQuitting) {
            return;
        }
        synchronized (writeLock) {
            outgoing.add(packet);
        }
    }
    
    public void sendPacketNow(Packet packet) {
    	if (isQuitting) {
    		return;
    	}
    	synchronized (writeLock) {
    		outgoing.add(packet);
    	}
    	while (outgoing.size() > 0 && isRunning && !isQuitting) ;
    }
    
    public boolean isDying() {
    	return isQuitting;
    }
    
    public boolean isDisconnected() {
    	return isDisconnected;
    }
    
    public void disconnect() {
    	isRunning = false;
    	while (readThread.isAlive()) ;
    	while (writeThread.isAlive()) ;
    }
    
    private boolean readTick() {
        boolean didSomething = false;
        try {
            Packet packet = Packet.readPacket(inputStream);

            if (packet != null) {
                if (!isQuitting) {
                    incoming.add(packet);
                }
                didSomething = true;
            }
            else
            {
            	if (!bConnectionDropped)
            	bConnectionDropped = true;
            }
        } catch (Exception e) {
            if (!isDisconnected) handleException(e);
            return false;
        }
        return didSomething;
    }

    private boolean writeTick() {
        boolean didSomething = false;
        try {
            if (!outgoing.isEmpty()) {
                Packet packet;
                synchronized (writeLock) {
                    packet = outgoing.remove(0);
                }
                Packet.writePacket(packet, outputStream);
                if (packet instanceof PartPacket) {
                	isQuitting = true; isRunning = false;
                }
                didSomething = true;
            }
        } catch (Exception e) {
            if (!isDisconnected) handleException(e);
            return false;
        }
        return didSomething;
    }

    private void die() {
    	isDisconnected = true;
    	try {
    		socket.close();
    	} catch (IOException e1) {
    	}
    }
    
    private void handleException(Exception e) {
        e.printStackTrace();
        die();
    }

    public void setPacketListener(PacketListener packetListener) {
        this.packetListener = packetListener;
    }

    @Override
    public void sendStartPacket(StartGamePacket startGamePacket) {
    	sendPacket(startGamePacket);
    }

}
