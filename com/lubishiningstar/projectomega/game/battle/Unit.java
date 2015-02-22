package com.lubishiningstar.projectomega.game.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lubishiningstar.projectomega.game.GameLogic;

public abstract class Unit extends Sprite implements GameLogic
{
	private int _maxHP, _HP;
	public boolean dead;
	
	public Unit(Texture texture, int hp) 
	{
		super(texture);
		_HP = _maxHP = hp;
		dead = false;
	}
	
	@Override
	public void render(SpriteBatch batch, float dt)
	{
		super.draw(batch);
	}
	
	public boolean addHealth(int hp)
	{
		_HP -= hp;
		
		if (_HP > _maxHP)
			_HP = _maxHP;
		
		else if (_HP <= 0)
			dead = true;
		
		return dead;
	}
}
