package com.lubishiningstar.projectomega.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lubishiningstar.projectomega.fx.FadeFX;
import com.lubishiningstar.projectomega.game.Game;
import com.lubishiningstar.projectomega.game.GameLogic;
import com.lubishiningstar.projectomega.game.GameState;
import com.lubishiningstar.projectomega.game.GameStates;

public class LogoState extends GameState implements GameLogic
{
	enum SubStates
	{
		FADING_IN,
		WAITING,
		FADING_OUT
	};
	
	private Texture _logo;
	private FadeFX _fade;
	private SubStates _subState;
	private Sound _logoSound;
	
	public LogoState() 
	{
		super(GameStates.LOGO);
	}

	@Override
	public void init()
	{
		_logo = new Texture("images/logo/logo_640_480.png");
		_fade = new FadeFX();
		_logoSound = Gdx.audio.newSound(Gdx.files.internal("sounds/logo/logo1.wav"));
		_logoSound.play();
		
		_subState = SubStates.FADING_IN;
	}

	@Override
	public void update(Game game, float dt)
	{
		if (Gdx.input.justTouched())
			endState(game, GameStates.MAIN_MENU, false);
		
		_fade.update(dt);
		
		switch(_subState)
		{
			case FADING_IN:
				if (_fade.getT() > 2)
				{
					_fade.reset();
					_subState = SubStates.WAITING;
				}
				break;
			case WAITING:
				if (_fade.getT() > 1)
				{
					_fade.reset();
					_subState = SubStates.FADING_OUT;
				}
				break;
				
			case FADING_OUT:
				if (_fade.getT() > 2)
				{
					endState(game, GameStates.MAIN_MENU, false);
				}
				break;
		}
	}
	
	
	@Override
	public void render(SpriteBatch batch, float dt)
	{		
		switch(_subState)
		{
			case FADING_IN:
				_fade.fadeIn(batch, 2);
				break;
				
			case WAITING:
				break;
				
			case FADING_OUT:
				_fade.fadeOut(batch, 2);
				break;
		}
		
		batch.draw(_logo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		_fade.resetColor(batch);
	}


	@Override
	public void deInit()
	{
		_logoSound.stop();
		_logo.dispose();
		_logoSound.dispose();
	}
}
