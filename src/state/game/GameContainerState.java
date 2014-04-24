package state.game;

import state.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameContainerState extends State {

	Game game;
	
	public GameContainerState(int gameNumber){
		
		switch(gameNumber){
		
		case 11:
			game = new IntroGame("Bob", 12);
		default:
			System.out.println(gameNumber);
		}
	}
	
	public void render(SpriteBatch batch){
		game.render(batch);
	}
	
	public void render(ShapeRenderer sRender){
		game.render(sRender);
	}
	
	public void update(){
		game.update();
	}
	
}
