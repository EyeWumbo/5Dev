package state;

import handler.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AccountCreationState extends State{
	
	Button testButton;
	
	public AccountCreationState(){
		this.testButton = new Button(2, "Landing", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		this.backgroundColor = Color.CYAN;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(Gdx.input.justTouched() && !fade){
			testButton.update(Gdx.input.getX(), Gdx.input.getY());
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		super.render(batch);
		testButton.render(batch, fadeMultiplier);
	}

	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		super.render(sRender);
		testButton.render(sRender, fadeMultiplier);
	}

}
