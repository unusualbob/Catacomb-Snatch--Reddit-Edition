package com.mojang.mojam.gui;

import java.awt.event.KeyEvent;

import com.mojang.mojam.screen.*;


public class DisconnectMenu extends GuiMenu {
    private int selectedItem = 0;
    private final int gameWidth;

    public DisconnectMenu(int gameWidth, int gameHeight) {
        super();
        this.gameWidth = gameWidth;

        addButton(new Button(TitleMenu.RESTART_GAME_ID, "Restart", 0, (gameWidth - 128) / 2, 200));
    }

    public void render(Screen screen) {
        screen.clear(0);
        screen.blit(Art.gameOverScreen, 0, 0);

        String msg1 = "The other player ragequit!";
        String msg2 = "...or the connection died";
        Font.draw(screen, msg1, ((gameWidth) / 2) - (Font.getStringWidth(msg1) / 2), 160);
        Font.draw(screen, msg2, ((gameWidth) / 2) - (Font.getStringWidth(msg1) / 2), 180);

        super.render(screen);
    }

    @Override
    public void buttonPressed(Button button) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            buttons.get(selectedItem).postClick();
        }
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

}
