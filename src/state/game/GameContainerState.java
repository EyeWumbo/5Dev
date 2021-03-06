package state.game;

import state.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameContainerState extends State {

	Game game;
	
	public GameContainerState(int gameNumber){
		
		switch(gameNumber){
		
		case 11:
			game = new IntroGame(12);
			break;
		case 12:
			game = new ShirtGame();
			break;
		case 13:
			game = new GetInCarGame();
			break;
		default:
			System.out.println(gameNumber);
			break;
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
	
	@Override
	public void setFade(){
		game.setFade();
	}
	
	public int getNumber(){
		return game.getNumber();
	}
	
}
