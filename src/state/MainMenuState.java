package state;

import handler.Button;
import app.Application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainMenuState extends State{

	Button testButton1, testButton2, testButton3;
	
	public MainMenuState(){
		
		testButton1 = new Button(6, "Progression", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 4);
		testButton2 = new Button(5, "Scores", Gdx.graphics.getWidth() / 2, 2 * Gdx.graphics.getHeight() / 4);
		testButton3 = new Button(4, "Games", Gdx.graphics.getWidth() / 2, 3 * Gdx.graphics.getHeight() / 4);
		
		this.backgroundColor = Color.WHITE;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(Gdx.input.justTouched() && !fade){
			testButton1.update(Gdx.input.getX(), Gdx.input.getY());
			testButton2.update(Gdx.input.getX(), Gdx.input.getY());
			testButton3.update(Gdx.input.getX(), Gdx.input.getY());
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		super.render(batch);
		testButton1.render(batch, fadeMultiplier);
		testButton2.render(batch, fadeMultiplier);
		testButton3.render(batch, fadeMultiplier);
	}

	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		super.render(sRender);
		testButton1.render(sRender, fadeMultiplier);
		testButton2.render(sRender, fadeMultiplier);
		testButton3.render(sRender, fadeMultiplier);
	}

}
