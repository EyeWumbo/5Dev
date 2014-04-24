package app;


import state.*;
import state.game.GameContainerState;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Application implements ApplicationListener{

	public static int TRANSITION_SIGNAL;
	
	State[] statesTracked;
	//int currentState;
	State currentState;
	private int currentSignal;
	
	private final int LOGIN = 0;
	private final int ACCOUNT_CREATE = 1;
	private final int LANDING = 2;
	private final int MAIN_MENU = 3;
	private final int GAME_MENU = 4;
	private final int SCORE = 5;
	private final int PROGRESSION = 6;
	private final int GAME = 7;
	private final int END = 8;
	private final int ROUTINE = 9;
	
	
	public Application(){
		
		
		
		
		//currentState = LOGIN;
		
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		statesTracked = new State[10];
		statesTracked[LOGIN] = new LoginState();
		statesTracked[ACCOUNT_CREATE] = new AccountCreationState();
		statesTracked[LANDING] = new LandingState();
		statesTracked[MAIN_MENU] = new MainMenuState();
		statesTracked[GAME_MENU] = new GameMenuState();
		statesTracked[SCORE] = new ScoreState();
		statesTracked[PROGRESSION] = new ProgressionState();
		
		//statesTracked[END] = new EndState();
		statesTracked[ROUTINE] = new RoutineState();
		currentState = statesTracked[LOGIN];
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		currentState.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(currentState.doneFading()){
			if(TRANSITION_SIGNAL > 10 && TRANSITION_SIGNAL < 80){
				currentSignal = 7;	
				statesTracked[currentSignal] = new GameContainerState(TRANSITION_SIGNAL);
				TRANSITION_SIGNAL = 7;
			}
			else if(TRANSITION_SIGNAL > 80){
				System.out.println("bloop");
				currentSignal = 8;
				//statesTracked[currentSignal] = new EndState(TRANSITION_SIGNAL % 80);
				TRANSITION_SIGNAL = 8;
			}	
			else if(TRANSITION_SIGNAL < 10){
				currentSignal = TRANSITION_SIGNAL;
			}
			currentState = statesTracked[currentSignal];
		}
		
		SpriteBatch batch = new SpriteBatch();
		ShapeRenderer sRender = new ShapeRenderer();
		currentState.update();
		currentState.render(sRender);
		sRender.end();
		batch.begin();
		currentState.render(batch);
		batch.end();
		System.out.println(currentSignal + " " + TRANSITION_SIGNAL);
		if(currentSignal != TRANSITION_SIGNAL){
			System.out.println("what");
			currentState.setFade();
		}
		//statesTracked[currentState].render(sRender);

	}

	@Override
	public void resize(int x, int y) {
		// TODO Auto-generated method stub
		currentState.resize(x, y);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
