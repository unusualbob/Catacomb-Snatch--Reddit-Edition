package com.mojang.mojam.gui;

import java.awt.event.KeyEvent;

import com.mojang.mojam.screen.*;

public class PauseMenu extends GuiMenu {
    private int selectedItem = 0;
    private int gameWidth;

    public PauseMenu(int gameWidth, int gameHeight) {
        super();
        this.gameWidth = gameWidth;
        
        addButton(new Button(TitleMenu.RESUME_LEVEL_ID, "Resume", 0, (gameWidth - 128) / 2, 200));
        addButton(new Button(TitleMenu.HELP_ID, "Help", 0, (gameWidth - 128) / 2, 240));
        addButton(new Button(TitleMenu.EXIT_LEVEL_ID, "Exit to Main", 0, (gameWidth - 128) / 2, 280));
    }

    public void render(Screen screen) {
        screen.clear(0);
        screen.blit(Art.pauseScreen, 0, 0);

        //Do we want to write anything?
        //String msg = "";
        //Font.draw(screen, msg, 180, 160);

        super.render(screen);
        screen.blit(Art.lordLard[0][6], (gameWidth - 128) / 2 - 40, 200 + selectedItem * 40);
    }

    @Override
    public void buttonPressed(Button button) {
    }

    public void keyPressed(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            selectedItem--;
            if (selectedItem < 0) {
                selectedItem = 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            selectedItem++;
            if (selectedItem > 1) {
                selectedItem = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	buttons.get(0).postClick();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_E) {
            buttons.get(selectedItem).postClick();
        }
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

}
