package com.lubishiningstar.projectomega.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Control extends Rectangle {	
	private static final long serialVersionUID = -3972976456589231171L;
	
	private ControlClickCallBack _cbClick;
	protected Texture _backTex;
	
	public boolean enabled;
	
	public Control(float x, float y, float width, float height, ControlClickCallBack cbClick, Texture tex)
	{
		super(x, y, width, height);
		_cbClick = cbClick;
		_backTex = tex;
	}

	public void render(SpriteBatch batch)
	{
		if (_backTex != null && enabled)
			batch.draw(_backTex, this.x, this.y , this.width, this.height,
					0, 0, _backTex.getWidth(), _backTex.getHeight(), false, false);
    }
	
	public void update()
	{
		if (Gdx.input.justTouched() && Gdx.input.isTouched(0) && enabled)
			click(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
	}
	
	public void click(int x, int y)
	{
		if (this.contains(x, y) && _cbClick  != null)
		{
			_cbClick.onClick();
		}
	}
	
	public void deInit()
	{
		_backTex.dispose();
	}
	
	public void setClickCB(ControlClickCallBack cbClick)
	{
		_cbClick = cbClick;
	}

	public Vector2 getPosition() 
	{
		return new Vector2(x, y);
	}
}
