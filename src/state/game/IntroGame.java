package state.game;

import handler.TextField;
import app.Application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class IntroGame extends Game{

	int age;
	TextField field, field2;
	
	public IntroGame(int age){
		
		this.stateName = "Introducing_Yourself";
		this.age = age;
		this.field = new TextField(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), true);
		this.field2 = new TextField(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3), false);
		this.backgroundColor = Color.BLACK;
		this.number = 1;
	}
	
	public void update(){
		super.update();
		if(Gdx.input.justTouched()){
			field.update(Gdx.input.getX(), Gdx.input.getY());
			field2.update(Gdx.input.getX(), Gdx.input.getY());
		}
		if(field.update()){
			field.animateTo(new Vector2(Gdx.graphics.getWidth() / 2, 2 * Gdx.graphics.getHeight() / 3));
			field2.setVisible(true);
		}
		if(field2.update()){
			System.out.println("okay");
			Application.TRANSITION_SIGNAL = 83;
		}
	}
	
	public void render(ShapeRenderer sRender){
		super.render(sRender);
		
		field.render(sRender, fadeMultiplier);
		field2.render(sRender, fadeMultiplier);
		
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
		
		field.render(batch, fadeMultiplier);
		field2.render(batch, fadeMultiplier);
		
	}
	
}
