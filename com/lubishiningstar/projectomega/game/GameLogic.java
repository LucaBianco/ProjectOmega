package com.lubishiningstar.projectomega.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameLogic
{
	void init();
	
	void update(Game game, float dt);
	
	void render(SpriteBatch batch, float dt);
	
	void deInit();
}
