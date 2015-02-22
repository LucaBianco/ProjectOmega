package com.lubishiningstar.projectomega;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lubishiningstar.projectomega.game.Game;

public class LibGdxProjectOmegaMain extends ApplicationAdapter 
{
	SpriteBatch batch;
	Game game = new Game();
	
	@Override
	public void create ()
	{
		game.init();
		batch = new SpriteBatch();
	}

	@Override
	public void render ()
	{
		game.update(null, Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(0.7f, .7f, 0.7f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		game.render(batch, Gdx.graphics.getDeltaTime());
		batch.end();
	}
}
