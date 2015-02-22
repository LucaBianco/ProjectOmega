package com.lubishiningstar.projectomega.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.lubishiningstar.projectomega.fx.FadeFX;
import com.lubishiningstar.projectomega.fx.LerpFX;
import com.lubishiningstar.projectomega.game.Game;
import com.lubishiningstar.projectomega.game.GameState;
import com.lubishiningstar.projectomega.game.GameStates;
import com.lubishiningstar.projectomega.ui.Control;
import com.lubishiningstar.projectomega.ui.ControlClickCallBack;

public class MainMenuState extends GameState
{
	enum SubStates
	{
		FADING_IN,
		WAITING,
		FADING_OUT_OPTIONS,
		FADING_OUT_PLAY,
		FADING_OUT_EXIT,
		FADING_OUT_CREDITS
	};
	
	private Texture _titleScreen;
	private Music _backMusic;
	private SubStates _subState;
	
	private Control _exitButton, _creditsButton, _playButton, _optionsButton;
	private Vector2 _exitPos, _creditsPos, _playPos, _optionsPos, _exitSPos, _creditsSPos, _playSPos, _optionsSPos;
	
	private FadeFX _fade;
	private LerpFX _lerp;
	
	public MainMenuState() 
	{
		super(GameStates.MAIN_MENU);
	}

	void initUI()
	{
		ControlClickCallBack cccbExit = new ControlClickCallBack() {
			@Override
			public void onClick() {
				_fade.reset();
				_lerp.reset();
				_subState = SubStates.FADING_OUT_EXIT;
			}
		};
		
		ControlClickCallBack cccbCredits = new ControlClickCallBack() {
			@Override
			public void onClick() {
				_fade.reset();
				_lerp.reset();
				_subState = SubStates.FADING_OUT_CREDITS;
			}
		};
		
		ControlClickCallBack cccbPlay = new ControlClickCallBack() {
			@Override
			public void onClick() {
				_fade.reset();
				_lerp.reset();
				_subState = SubStates.FADING_OUT_PLAY;
			}
		};
		
		ControlClickCallBack cccbOptions = new ControlClickCallBack() {
			@Override
			public void onClick() {
				_fade.reset();
				_lerp.reset();
				_subState = SubStates.FADING_OUT_OPTIONS;
			}
		};
		
		_exitPos = new Vector2(0, 16);
		_creditsPos = new Vector2(0, 116);
		
		_exitButton = new Control(-256, 16, 256, 90, cccbExit, new Texture("images/MainMenu/ExitButton.png"));
		_creditsButton = new Control(-256, 116, 256, 90, cccbCredits, new Texture("images/MainMenu/CreditsButton.png"));
		
		int w = Gdx.graphics.getWidth();
		
		_playPos = new Vector2(w-256, 100);
		_optionsPos = new Vector2(w-256, 204);
		
		_playButton = new Control(w, 100, 256, 90, cccbPlay, new Texture("images/MainMenu/PlayButton.png"));
		_optionsButton = new Control(w, 204, 256, 90, cccbOptions, new Texture("images/MainMenu/OptionsButton.png"));
		
		_exitButton.enabled = true;
		_creditsButton.enabled = true;
		_playButton.enabled = true;
		_optionsButton.enabled = true;
		
		_exitSPos = _exitButton.getPosition();
		_creditsSPos = _creditsButton.getPosition();
		_playSPos = _playButton.getPosition();
		_optionsSPos = _optionsButton.getPosition();
	}
	
	@Override
	public void init() 
	{
		_titleScreen = new Texture("images/MainMenu/title_640_480.png");
		
		_backMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Heavy Brigade.mp3"));
		_backMusic.play();
		_backMusic.setLooping(true);
		
		initUI();
		
		_fade = new FadeFX();
		_lerp = new LerpFX();
		
		_subState = SubStates.FADING_IN;
	}

	@Override
	public void update(Game game, float dt)
	{		
		_fade.update(dt);
		_lerp.update(dt);
		
		switch(_subState)
		{
		case FADING_IN:
			if (_fade.getT() > 2 || Gdx.input.justTouched())
			{
				_fade.reset();
				
				_exitButton.setPosition(_exitPos);
				_creditsButton.setPosition(_creditsPos);
				_playButton.setPosition(_playPos);
				_optionsButton.setPosition(_optionsPos);
				
				_subState = SubStates.WAITING;
			}
		
		case WAITING:
			_exitButton.update();
			_creditsButton.update();
			_playButton.update();
			_optionsButton.update();
			
			break;
			
		case FADING_OUT_OPTIONS:
			if (_fade.getT() >= 1 || Gdx.input.justTouched())
			{
				endState(game, GameStates.OPTIONS, false);
			}
			break;
			
		case FADING_OUT_CREDITS:
			if (_fade.getT() >= 1 || Gdx.input.justTouched())
			{
				endState(game, GameStates.CREDITS, false);
			}
			break;
		
		case FADING_OUT_PLAY:
			if (_fade.getT() >= 1 || Gdx.input.justTouched())
			{
				endState(game, GameStates.PLAYING, false);
			}
			break;
			
		case FADING_OUT_EXIT:
			if (_fade.getT() >= 1 || Gdx.input.justTouched())
			{
				Gdx.app.exit();
			}
			break;
			
		default:
			Gdx.app.exit();
		}
	}

	@Override
	public void render(SpriteBatch batch, float dt)
	{
		switch (_subState)
		{
		case FADING_IN:
			_fade.fadeIn(batch, 2);
			_exitButton.setPosition(_lerp.getLerp(_exitSPos, _exitPos, 2));
			_creditsButton.setPosition(_lerp.getLerp(_creditsSPos, _creditsPos, 2));
			_playButton.setPosition(_lerp.getLerp(_playSPos, _playPos, 2));
			_optionsButton.setPosition(_lerp.getLerp(_optionsSPos, _optionsPos, 2));
			break;
		
		case FADING_OUT_PLAY:
		case FADING_OUT_OPTIONS:
		case FADING_OUT_CREDITS:
		case FADING_OUT_EXIT:
			_fade.fadeOut(batch, 1);
			_exitButton.setPosition(_lerp.getLerp(_exitPos, _exitSPos, 1));
			_creditsButton.setPosition(_lerp.getLerp(_creditsPos, _creditsSPos, 1));
			_playButton.setPosition(_lerp.getLerp(_playPos, _playSPos, 1));
			_optionsButton.setPosition(_lerp.getLerp(_optionsPos, _optionsSPos, 1));
			break;
			
		default:
			
		}
		
		batch.draw(_titleScreen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		_exitButton.render(batch);
		_creditsButton.render(batch);
		_playButton.render(batch);
		_optionsButton.render(batch);
		
		_fade.resetColor(batch);
	}

	@Override
	public void deInit() 
	{
		_backMusic.stop();
		_backMusic.dispose();
		_titleScreen.dispose();
	}
}
