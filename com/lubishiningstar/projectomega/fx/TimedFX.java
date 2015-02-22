package com.lubishiningstar.projectomega.fx;

public class TimedFX
{
	protected float _t;
	
	public TimedFX() 			{ _t=0; }
	
	public void reset()			{ _t = 0; }
	
	public void update(float dt){ _t += dt; }
	
	public float getT()			{ return _t; }
}
