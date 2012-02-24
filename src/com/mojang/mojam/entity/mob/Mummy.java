package com.mojang.mojam.entity.mob;

import com.mojang.mojam.entity.Entity;
import com.mojang.mojam.network.TurnSynchronizer;
import com.mojang.mojam.screen.*;

public class Mummy extends Mob {
    public int facing;
    public int walkTime;
    public int stepTime;

    public Mummy(double x, double y) {
        super(x, y, Team.Neutral);
        setPos(x, y);
        setStartHealth(7);
        dir = TurnSynchronizer.synchedRandom.nextDouble() * Math.PI * 2;
        minimapColor = 0xffff0000;
        yOffs = 10;
        facing = TurnSynchronizer.synchedRandom.nextInt(4);

        deathPoints = 4;
    }

    public void tick() {
        super.tick();
        if (freezeTime > 0) return;

        double speed = 0.5;
        if(player != null && player.pos.dist(pos) < radius){
            //Doubles the mummies speed. Could be set to lower as currently it makes the game much more difficult.
        	speed = 1;
        	//Cardinals
        	if (pos.y < player.pos.y && Math.abs(pos.x - player.pos.x) < Tile.WIDTH * 0.5) facing = 0;
        	else if (pos.x > player.pos.x && Math.abs(pos.y - player.pos.y) < Tile.WIDTH * 0.5) facing = 1;
        	else if (pos.y > player.pos.y && Math.abs(pos.y - player.pos.y) < Tile.WIDTH * 0.5) facing = 2;
        	else if (pos.x < player.pos.x && Math.abs(pos.y - player.pos.y) < Tile.WIDTH * 0.5) facing = 3;
        	//Diagonals
        	else if(pos.x > player.pos.x && pos.y > player.pos.y) facing = 4;
        	else if(pos.x > player.pos.x && pos.y < player.pos.y) facing = 5;
        	else if(pos.x < player.pos.x && pos.y < player.pos.y) facing = 6;
        	else if(pos.x < player.pos.x && pos.y > player.pos.y) facing = 7;
        	
        	
        	
        	
        }
        //Cardinals
        if (facing == 0) yd += speed;
        if (facing == 1) xd -= speed;
        if (facing == 2) yd -= speed;
        if (facing == 3) xd += speed;
        //Diagonals
        if (facing == 4){
        	xd -= speed;
        	yd -= speed;
        }
        if (facing == 5){
        	xd -= speed;
        	yd += speed;
        }
        if (facing == 6){
        	xd += speed;
        	yd += speed;
        }
        if (facing == 7){
        	xd += speed;
        	yd -= speed;
        }
        
        //Can be removed when sprite sheet is updated with diagonals. Will have to change the way it picks the sprite too;
        if(facing == 4 || facing == 5){
        	if(player != null && Math.abs(pos.y - player.pos.y) > Math.abs(pos.x - player.pos.x)){
        		if(pos.y < player.pos .y) facing = 0;
        		if(pos.y > player.pos.y) facing = 2; 
        	}
        	else{
        		facing = 1;
        	}
        }
        else if(facing == 6 || facing == 7) {
        	if(player != null && Math.abs(pos.y - player.pos.y) > Math.abs(pos.x - player.pos.x)){
        		if(pos.y < player.pos .y) facing = 0;
        		if(pos.y > player.pos.y) facing = 2; 
        	}
        	else{
        		facing = 3;
        	}
        }
        //End of sprite sheet work around
        
        walkTime++;

        if (walkTime / 12 % 3 != 0) {
            stepTime++;
            if (!move(xd, yd) || (walkTime > 10 && TurnSynchronizer.synchedRandom.nextInt(200) == 0)) {
                facing = TurnSynchronizer.synchedRandom.nextInt(4);
                walkTime = 0;
            }
        }
        xd *= 0.2;
        yd *= 0.2;
    }

    public void die() {
        super.die();
    }

    public Bitmap getSprite() {
        return Art.mummy[((stepTime / 6) & 3)][(facing + 3) & 3];
    }

    @Override
    public void collide(Entity entity, double xa, double ya) {
        super.collide(entity, xa, ya);

        if (entity instanceof Mob) {
            Mob mob = (Mob) entity;
            if (isNotFriendOf(mob)) {
                mob.hurt(this, 2);
            }
        }
    }

    @Override
    public String getDeatchSound() {
        return "/sound/Enemy Death 2.wav";
    }
}
