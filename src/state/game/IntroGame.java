package state.game;

import handler.TextField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class IntroGame extends Game{

	String name;
	int age;
	TextField field;
	
	public IntroGame(String name, int age){
		
		this.name = name;
		this.age = age;
		this.field = new TextField(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
		this.backgroundColor = Color.BLACK;
		
	}
	
	public void update(){
		super.update();
		if(Gdx.input.justTouched()){
			field.update(Gdx.input.getX(), Gdx.input.getY());
		}
		field.update();
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY)){
			//System.out.println("woo");
		}
	}
	
	public void render(ShapeRenderer sRender){
		super.render(sRender);
		
		field.render(sRender, fadeMultiplier);
		
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
		
		field.render(batch, fadeMultiplier);
		
	}
	
}
