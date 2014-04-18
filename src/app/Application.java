package app;

import java.util.ArrayList;

import state.State;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Application implements ApplicationListener{

	ArrayList<State> statesTracked;
	
	private enum stateAccessor{
		LOGIN(0),
		ACCOUNT_CREATE(1),
		LANDING(2),
		MAIN_MENU(3),
		GAME_MENU(4),
		SCORE(5),
		PROGRESSION(6),
		GAME(7),
		END(8),
		ROUTINE(9);
		
		private final int index;
		
		stateAccessor(int index){
			this.index = index;
		}
		
	}
	
	public Application(){
		statesTracked = new ArrayList<State>(10);
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		SpriteBatch batch = new SpriteBatch();
		ShapeRenderer sRender = new ShapeRenderer();
		
		sRender.begin(ShapeType.Filled);
		sRender.setColor(0, 1, 0, 1);
		sRender.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sRender.end();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
