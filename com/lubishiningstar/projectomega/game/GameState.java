package com.lubishiningstar.projectomega.game;


public abstract class GameState implements GameLogic 
{
	private GameStates _state;	
	
	public GameState(GameStates state)
	{
		_state = state;
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
