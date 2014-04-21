package handler;

import app.Application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import state.State;

public class Button{
	
	private int stateToMove;
	String buttonText;
	Vector2 position, size;
	
	public Button(int state, String buttonText, int xPos, int yPos){
		System.out.println("fdao");
		this.stateToMove = state;
		this.buttonText = buttonText;
		
		this.size = new Vector2(50,50);
		this.position = new Vector2(xPos - size.x/2, yPos - size.y/2);
		
		
	}
	
	public boolean update(float x, float y){
		Application.TRANSITION_SIGNAL = 5;
		return (x > position.x && x < position.x + size.x &&
				Gdx.graphics.getHeight() - y > position.y &&
				Gdx.graphics.getHeight() - y < position.y + size.y);
	
	}
	
	public void render(SpriteBatch batch){
		
	}
	
	public void render(ShapeRenderer sRender, float fadeMultiplier){
		//sRender.begin(ShapeType.Filled);
		sRender.setColor(1 * fadeMultiplier, 0, 0, 1);
		sRender.rect(position.x, position.y, size.x, size.y);
		//sRender.end();
	}
	
}
