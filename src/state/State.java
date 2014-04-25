package state;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class State{
	
	protected Color backgroundColor;
	protected static float fadeMultiplier = 1;
	protected boolean fade = false;
	
	public State(){
		backgroundColor = Color.ORANGE;
	}
	
	public void update(){
		incrementFade();
		decrementFade();
	}
	public void render(SpriteBatch batch){
		batch.setColor(batch.getColor().r * fadeMultiplier,
				batch.getColor().g * fadeMultiplier,
				batch.getColor().b * fadeMultiplier,
				batch.getColor().a * fadeMultiplier);
	}
	public void render(ShapeRenderer sRender){
		sRender.begin(ShapeType.Filled);
		sRender.setColor(backgroundColor.r * fadeMultiplier,
				backgroundColor.g * fadeMultiplier, 
				backgroundColor.b * fadeMultiplier,1);
		sRender.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
			fadeMultiplier -= 0.11;
		}
	}
	
	protected void incrementFade(){
		if(!fade && fadeMultiplier < 1){
			fadeMultiplier += 0.11;
		}
	}
	
	public boolean doneFading(){
		if(fadeMultiplier <= 0){
			fade = false;
		}
		return fadeMultiplier <= 0;
	}
	
	public Boolean doesFileExist(String filepath)
	{
		File f = new File(filepath);
		if(f.exists() == true)
		{
			return true;
		}
		return false; 
	}
}
