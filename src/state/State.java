package state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
	
	
	public State(){
		
	}
	
	public abstract void update();
	public abstract void render(SpriteBatch batch);
	public abstract void create();
	
}
