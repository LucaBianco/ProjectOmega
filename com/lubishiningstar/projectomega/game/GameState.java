package com.lubishiningstar.projectomega.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState implements GameLogic 
{
	private GameStates _state;	
	
	public GameState(GameStates state)
	{
		_state = state;
	}
	
	@Override
	public void init()
	{
		
	}

	@Override
	public void update(Game game, float dt)
	{
		
	}

	@Override
	public void render(SpriteBatch batch, float dt)
	{
		
	}
	
	protected void endState(Game game, GameStates newState, boolean forceAdd)
	{
		deInit();
		game.removeState(this);
		game.setState(newState, forceAdd);
	}
	
	public void setState(GameStates state) { _state = state; }
	public GameStates getState() { return _state; }
}
