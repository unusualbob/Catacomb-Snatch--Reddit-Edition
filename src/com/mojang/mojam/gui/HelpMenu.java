package com.mojang.mojam.gui;

import java.awt.event.KeyEvent;

import com.mojang.mojam.screen.Screen;

public class HelpMenu extends GuiMenu {

    public HelpMenu() {
        super();

        addButton(new Button(TitleMenu.CANCEL_JOIN_ID, "Back", 0, 10, 320));
    }

    @Override
    public void render(Screen screen) {

        screen.clear(0);
        Font.draw(screen, "CONTROLS", 10, 30);
        Font.draw(screen, "--------", 10, 40);
        Font.draw(screen, "WASD: Move.", 10, 50);
        Font.draw(screen, "Arrow keys: Shoot.", 10, 60);
        Font.draw(screen, "E/Z: Buy/Pick Up.", 10, 70);
        Font.draw(screen, "R/X: Lay/Remove Track.", 10, 80);
        Font.draw(screen, "R/X (while standing on the first rail in your base):", 10, 90);
        Font.draw(screen, "Creates a Rail-Droid.", 10, 100);
        
        Font.draw(screen, "ITEMS", 10, 120);
        Font.draw(screen, "-----", 10, 130);
        Font.draw(screen, "Turret (150 coins): Shoots baddies in general vicinity,", 10, 140);
        Font.draw(screen, "even while holding it.", 10, 150);
        Font.draw(screen, "Collector  (300 coins): Sucks up coins within a large radius.", 10, 170);
        Font.draw(screen, "Pick up the  collector to receive the coins.", 10, 180);
        Font.draw(screen, "They can also be destroyed by enemies, so protect them!", 10, 190);
        Font.draw(screen, "Bomb (500 coins): Used to destroy purple gemmed walls.", 10, 210);
        Font.draw(screen, "Shoot the bomb or have turrets shoot it to detonate it.", 10, 220);
        Font.draw(screen, "Track (10 coins to add, 15 coins to remove):", 10, 240);
        Font.draw(screen, "Rail track for gathering batches Treasure.", 10, 250);
        Font.draw(screen, "Rail-Droid  (50 coins): Carts can be bought by standing on", 10, 270);
        Font.draw(screen, "the first section of track at your Base Camp. These will ", 10, 280);
        Font.draw(screen, "travel along rails that you lay down and will collect", 10, 290);
        Font.draw(screen, "treasure from the Treasure Trove at the center of the map.", 10, 300);
        
        
        super.render(screen);
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_F1) {
        	buttons.get(0).postClick();
        }
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }
	
}
