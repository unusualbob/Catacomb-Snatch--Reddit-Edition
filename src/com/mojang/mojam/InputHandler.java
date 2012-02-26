package com.mojang.mojam;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import com.mojang.mojam.Keys.Key;

public class InputHandler implements KeyListener {
	private Map<Integer, Key> mappings = new HashMap<Integer, Key>();

	public InputHandler(Keys keys) {
		// Movement

		mappings.put(KeyEvent.VK_W, keys.up);
		mappings.put(KeyEvent.VK_S, keys.down);
		mappings.put(KeyEvent.VK_A, keys.left);
		mappings.put(KeyEvent.VK_D, keys.right);

		// Laser beemz
		mappings.put(KeyEvent.VK_UP, keys.fire_up);
		mappings.put(KeyEvent.VK_DOWN, keys.fire_down);
		mappings.put(KeyEvent.VK_LEFT, keys.fire_left);
		mappings.put(KeyEvent.VK_RIGHT, keys.fire_right);

        //Build rails.
		mappings.put(KeyEvent.VK_R, keys.build);
        mappings.put(KeyEvent.VK_X, keys.build);
        
        //Use
        mappings.put(KeyEvent.VK_Z, keys.use);
        mappings.put(KeyEvent.VK_E, keys.use);
		
		// Hax0r ch33tz
		mappings.put(KeyEvent.VK_1, keys.give_money);
		mappings.put(KeyEvent.VK_2, keys.give_turret);
		mappings.put(KeyEvent.VK_3, keys.give_harvester);
		mappings.put(KeyEvent.VK_4, keys.give_bomb);
		mappings.put(KeyEvent.VK_5, keys.add_slave);
		mappings.put(KeyEvent.VK_6, keys.team1_score);
		mappings.put(KeyEvent.VK_7, keys.team2_score);
		

		// misc
		mappings.put(KeyEvent.VK_ESCAPE, keys.pause);
		mappings.put(KeyEvent.VK_F1, keys.help);
		mappings.put(KeyEvent.VK_M, keys.mute);
	}

	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	public void keyTyped(KeyEvent ke) {
	}

	private void toggle(KeyEvent ke, boolean state) {
		Key key = mappings.get(ke.getKeyCode());
		if (key != null) {
			key.nextState = state;
		}
	}
}
