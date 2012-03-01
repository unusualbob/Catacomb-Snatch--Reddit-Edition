package com.mojang.mojam.gui;

import java.awt.event.KeyEvent;

import com.mojang.mojam.screen.Screen;

public class JoinWaitMenu extends GuiMenu {
	public int numPlayers = 1;
	public String message = "";
	
    public JoinWaitMenu() {
        super();

        addButton(new Button(TitleMenu.CANCEL_JOIN_ID, "Cancel",  0, 250, 240));
    }

    @Override
    public void render(Screen screen) {
        screen.clear(0);
        
        String message = "Waiting for host to start...";
        if (numPlayers <= 0) {
        	if (this.message == "")
        		message = "Server dropped out, connection lost.";
        	else
        		message = this.message;
        } else if (numPlayers < 4)
        	message = "Waiting for clients to join...";
    	Font.draw(screen, message, 100, 100);
        
        for (int i = 1; i <= numPlayers; i++)
        	Font.draw(screen, "           " + "Player " + i + " connected", 100, 100 + i*20);

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
