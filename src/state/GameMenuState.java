package state;

import handler.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameMenuState extends State{

	Button menuButton, goToGame;
	
	//private Button[] allBonusGames;
	
	public GameMenuState(){
		
		menuButton = new Button(3, "Main Menu", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
		goToGame = new Button(9, "Routine Game", Gdx.graphics.getWidth() / 2, 2 * Gdx.graphics.getHeight() / 3);

		this.backgroundColor = Color.WHITE;
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();

		if(Gdx.input.justTouched() && !fade){
			menuButton.update(Gdx.input.getX(), Gdx.input.getY());
			goToGame.update(Gdx.input.getX(), Gdx.input.getY());
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		super.render(batch);

		menuButton.render(batch, fadeMultiplier);
		goToGame.render(batch, fadeMultiplier);
	}

	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		super.render(sRender);

		menuButton.render(sRender, fadeMultiplier);
		goToGame.render(sRender, fadeMultiplier);
	}

}
