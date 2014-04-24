package handler;

import app.Application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	BitmapFont font;
	String buttonText;
	Vector2 position, size;
	Color color;
	
	public Button(int state, String buttonText, int xPos, int yPos){
		this.stateToMove = state;
		this.buttonText = buttonText;
		
		font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
		color = font.getColor();
		
		this.size = new Vector2(font.getBounds(buttonText).width * 2, font.getBounds(buttonText).height * 2);
		this.position = new Vector2(xPos - size.x/2, yPos - size.y/2);
		
	}
	
	public void update(float x, float y){
		
		if(x > position.x && x < position.x + size.x &&
				Gdx.graphics.getHeight() - y > position.y &&
				Gdx.graphics.getHeight() - y < position.y + size.y)
		{
			Application.TRANSITION_SIGNAL = stateToMove;
		}
	
	}
	
	public void render(SpriteBatch batch, float fadeMultiplier){
		if(fadeMultiplier <= 0){
			return;
		}
		font.setColor(color.r * fadeMultiplier,
				color.g * fadeMultiplier,
				color.b * fadeMultiplier,
				1);
		font.draw(batch, buttonText, position.x + font.getBounds(buttonText).width / 2,
				position.y + size.y / 2 + font.getBounds(buttonText).height / 2);
		
	}
	
	public void render(ShapeRenderer sRender, float fadeMultiplier){
		//sRender.begin(ShapeType.Filled);
		sRender.setColor(1 * fadeMultiplier, 0, 0, 1);
		sRender.rect(position.x, position.y, size.x, size.y);
		//sRender.end();
	}
	
}
