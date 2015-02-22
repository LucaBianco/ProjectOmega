package com.lubishiningstar.projectomega.fx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FadeFX extends TimedFX
{
	private Color _saved;
	
	
	public void fadeIn(SpriteBatch batch, float tmax)
	{	
		_saved = batch.getColor();
		
		if (_t <=  tmax)
		{
			Color col = new Color(_t/tmax, _t/tmax, _t/tmax, 1f);
			batch.setColor(col);
		}
	}
	
	public void fadeOut(SpriteBatch batch, float tmax)
	{		
		_saved = batch.getColor();
		
		if (_t <=  tmax)
		{
			Color col = new Color(1-(_t/tmax), 1-(_t/tmax), 1-(_t/tmax), 1f);
			batch.setColor(col);
		}
	}
	
	public void resetColor(SpriteBatch batch)
	{
		batch.setColor(_saved);
	}
}
