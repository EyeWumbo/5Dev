package state;

import handler.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameMenuState extends State{

	Button testButton1, testButton2;
	
	public GameMenuState(){
		
		testButton1 = new Button(9, "Routine Game", Gdx.graphics.getWidth() / 2, 2* Gdx.graphics.getHeight() / 3);
		testButton2 = new Button(3, "Main Menu", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
		
		this.backgroundColor = Color.WHITE;
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(Gdx.input.justTouched()){
			testButton1.update(Gdx.input.getX(), Gdx.input.getY());
			testButton2.update(Gdx.input.getX(), Gdx.input.getY());
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		super.render(batch);
		testButton1.render(batch, fadeMultiplier);
		testButton2.render(batch, fadeMultiplier);
	}

	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		super.render(sRender);
		testButton1.render(sRender, fadeMultiplier);
		testButton2.render(sRender, fadeMultiplier);
	}

}
