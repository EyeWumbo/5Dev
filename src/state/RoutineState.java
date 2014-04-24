package state;

import handler.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RoutineState extends State{
	
	private int gameNum;
	private int avaliableGames;
	Button g0, g1, g2, g3, g4, g5, g6, g7, g8, g9;
	private Button[] allGames;
	private Boolean[] gamePlayable;
	private int firstGame; // first game on menu screen
	private int yChange;
	
	public RoutineState(){
		
		firstGame = 0;
		gameNum = 10;
		avaliableGames = 2;
		gamePlayable = new Boolean[gameNum];
		
		g0 = new Button(11, "Routine Game 0", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		g1 = new Button(9 , "Routine Game 1", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		g2 = new Button(9 , "Routine Game 2", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		g3 = new Button(9 , "Routine Game 3", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		g4 = new Button(9 , "Routine Game 4", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		g5 = new Button(3 , "Routine Game 5", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		g6 = new Button(9 , "Routine Game 6", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		g7 = new Button(9 , "Routine Game 7", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		g8 = new Button(9 , "Routine Game 8", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		g9 = new Button(9 , "Routine Game 9", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);

		allGames = new Button[] {g0, g1, g2, g3, g4, g5, g6, g7, g8, g9};
		gamePlayable[0] = true;
		
		for (int i = 1; i < gameNum; i++){
			gamePlayable[i] = false;
		}
		
		this.backgroundColor = Color.CYAN;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		
		if(Gdx.input.justTouched()){
			if (avaliableGames < 5){
				for (int i = 0; i < avaliableGames; i++){
					if (gamePlayable[i] == true) {
						allGames[i].update(Gdx.input.getX(), Gdx.input.getY());
					}
				}
			}
			else {
				for(int i = firstGame; i < firstGame+5; i++){
					if (gamePlayable[i] == true) {
						allGames[i].update(Gdx.input.getX(), Gdx.input.getY());
					}
				}
			}
		}
		if (avaliableGames > 5) {
			if(Gdx.input.isTouched())
			{
				if (Gdx.input.getDeltaY() > 7 && firstGame > 0){
					// down swipe
					firstGame--;
					yChange = 5;
					for (int i = firstGame; i < firstGame+5; i++) {
						allGames[i].setNewPosition(Gdx.graphics.getWidth() / 2, yChange*  Gdx.graphics.getHeight() / 6);
						yChange--;
					}
				}
				if (Gdx.input.getDeltaY() < -7 && firstGame < avaliableGames-5) {
					// up swipe
					firstGame++;
					yChange = 5;
					for (int i = firstGame; i < firstGame+5; i++) {
						allGames[i].setNewPosition(Gdx.graphics.getWidth() / 2, yChange*  Gdx.graphics.getHeight() / 6);
						yChange--;
					}
				}
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
		super.render(batch);
		if (avaliableGames < 5){
			for (int i = 0; i < avaliableGames; i++){
				if (gamePlayable[i] == true) {
					allGames[i].render(batch, fadeMultiplier);
				}
			}
		}
		else {
			for(int i = firstGame; i < firstGame+5; i++){
				if (gamePlayable[i] == true) {
					allGames[i].render(batch, fadeMultiplier);
				}
			}		
		}
	}

	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		super.render(sRender);
		if (avaliableGames < 5){
			for (int i = 0; i < avaliableGames; i++){
				if (gamePlayable[i] == true) {
					allGames[i].render(sRender, fadeMultiplier);
				}
			}
		}
		else {
			for(int i = firstGame; i < firstGame+5; i++){
				if (gamePlayable[i] == true) {
					allGames[i].render(sRender, fadeMultiplier);
				}
			}		
		}
	}
	
	public void gameCompleted(int gameNum){
		gamePlayable[gameNum] = true;
	}

}
