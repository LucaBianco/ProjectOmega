package com.lubishiningstar.projectomega.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.lubishiningstar.projectomega.game.Game;
import com.lubishiningstar.projectomega.game.GameState;
import com.lubishiningstar.projectomega.game.GameStates;

public class PlayingState extends GameState
{
	private TiledMap _map;
	private OrthogonalTiledMapRenderer _renderer;
	private OrthographicCamera _camera;
	
	public PlayingState() 
	{
		super(GameStates.PLAYING);
	}


	@Override
	public void init() 
	{
		TmxMapLoader loader = new TmxMapLoader();
		_map = loader.load("maps/test.tmx");
		
		_renderer = new OrthogonalTiledMapRenderer(_map);
		_camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void update(Game game, float dt) 
	{
		
	}

	@Override
	public void render(SpriteBatch batch, float dt) 
	{
		_renderer.setView(_camera);
		_renderer.render();
	}
	
	@Override
	public void deInit()
	{
		_map.dispose();
		_renderer.dispose();
	}
}
