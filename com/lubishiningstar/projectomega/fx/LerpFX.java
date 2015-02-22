package com.lubishiningstar.projectomega.fx;

import com.badlogic.gdx.math.Vector2;

public class LerpFX extends TimedFX 
{
	public LerpFX() 
	{
		super();
	}
	
	public float getLerp(float v0, float v1, float tmax)
	{
		if (_t <= tmax)
		{			
			float t = _t/tmax;
			return (1-t)*v0 + t*v1;
		}
		else
		{
			return v1;
		}
	}
	
	public Vector2 getLerp(Vector2 v0, Vector2 v1, float tmax)
	{
		return new Vector2(getLerp(v0.x, v1.x, tmax), getLerp(v0.y, v1.y, tmax));
	}
}
