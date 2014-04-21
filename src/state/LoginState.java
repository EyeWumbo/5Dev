package state;

import handler.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LoginState extends State {

	Button testButton;
	
	public LoginState(){
		testButton = new Button(5, "what", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()/2);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()){
			if(testButton.update(Gdx.input.getX(), Gdx.input.getY())){
				System.out.println("feaw");
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		
		super.render(sRender);
		
		sRender.begin(ShapeType.Filled);
		sRender.setColor(0, 1 * fadeMultiplier, 0, 1);
		sRender.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		testButton.render(sRender, this.fadeMultiplier);		
		
	}

}
