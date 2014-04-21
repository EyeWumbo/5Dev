package state;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class State{
	
	protected static float fadeMultiplier = 1;
	protected boolean fade = false;
	
	public State(){

	}
	
	public abstract void update();
	public abstract void render(SpriteBatch batch);
	public void render(ShapeRenderer sRender){
		decrementFade();
		incrementFade();
	}
	public void resize(int x, int y){
		
	}
	public void dispose(){
		
	}
	
	public void setFade(){
		this.fade = true;
	}
	
	protected void decrementFade(){
		if(fade && fadeMultiplier > 0){
			fadeMultiplier -= 0.01;
		}
	}
	
	protected void incrementFade(){
		if(!fade && fadeMultiplier < 1){
			fadeMultiplier += 0.01;
		}
	}
	
	public boolean doneFading(){
		return fadeMultiplier <= 0;
	}
}
