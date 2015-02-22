package com.lubishiningstar.projectomega.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lubishiningstar.projectomega.game.states.*;

public class Game implements GameLogic 
{
	private int	_currentState;
	private ArrayList<GameState> _states;
	
	@Override
	public void init() 
	{
		_states = new ArrayList<GameState>();
		setState(GameStates.LOGO, true);
	}

	@Override
	public void update(Game game, float dt)
	{
		_states.get(_currentState).update(this, dt);
	}

	@Override
	public void render(SpriteBatch batch, float dt)
	{
		_states.get(_currentState).render(batch, dt);
	}
	
	
	//STATEs STUFF
	private int searchOrAddState(GameStates stateType, boolean forceAdd)
	{
		int index = -1;
		
		for (int i=0; i<_states.size(); i++)
			if (_states.get(i).getState() == stateType)
			{
				index = i;
				break;
			}
		
		if (index == -1 || forceAdd)
		{
			switch(stateType)
			{
			case NONE:
				break;
			
			case LOGO:
				_states.add(new LogoState());
				break;
				
			case MAIN_MENU:
				_states.add(new MainMenuState());
				break;
				
			case CREDITS:
				_states.add(new CreditsState());
				break;
				
			case OPTIONS:
				_states.add(new OptionsState());
				break;
				
			case PLAYING:
				_states.add(new PlayingState());
				break;
			default:
				break;
			}
			_states.get(_states.size()-1).init();
			return _states.size()-1;
		}
		else
		{
			return index;
		}
	}
	
	public void removeStatesOfType(GameStates stateType)
	{
		for (int i=0; i<_states.size(); i++)
			if (_states.get(i).getState() == stateType)
				_states.remove(i);
	}
	
	public void removeState(GameState state)
	{
		for (int i=0; i<_states.size(); i++)
			if (_states.get(i).equals(state))
				_states.remove(i);
	}
	
	public void setState(GameStates state, boolean forceAdd)
	{
		_currentState = searchOrAddState(state, forceAdd);
	}
	
	public GameStates getState() { return _states.get(_currentState).getState(); }

	@Override
	public void deInit() {
		// TODO Auto-generated method stub
		
	}
}
