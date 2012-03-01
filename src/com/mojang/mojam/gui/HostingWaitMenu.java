package com.mojang.mojam.gui;

import java.awt.event.KeyEvent;
import java.net.*;

import com.mojang.mojam.screen.Screen;

public class HostingWaitMenu extends GuiMenu {
	public int numPlayers = 1;
	Button startButton;
	
    public HostingWaitMenu() {
        super();

        addButton(new Button(TitleMenu.CANCEL_JOIN_ID, "Cancel",  0, 250, 260));
        startButton = addButton(new Button(TitleMenu.START_HOST_ID, "Start", 0, 100, 260));
        startButton.visible = false;
    }

    @Override
    public void render(Screen screen) {
        screen.clear(0);
        
        if (numPlayers < 4)
        	Font.draw(screen, "Waiting for clients to join...", 100, 100);
        else
        	Font.draw(screen, "Ready to start", 100, 100);

        try {
            InetAddress thisIp = InetAddress.getLocalHost();
            Font.draw(screen, "Your IP:"+ thisIp.getHostAddress(), 100, 120);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (int i = 1; i <= numPlayers; i++)
        	Font.draw(screen, "           " + "Player " + i + " connected", 100, 120 + i*20);

        startButton.visible = (numPlayers >= 2);
        super.render(screen);
    }

    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	buttons.get(0).postClick();
        }
       
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }


}
