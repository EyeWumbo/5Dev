package state.game;

import handler.DialogBox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GetInCarGame extends Game{

	Texture taxi, policeCar, schoolBus, fireTruck;
	Texture[] texs;
	DialogBox probState;
	
	public GetInCarGame(){
		texs = new Texture[4];
		texs[0] = new Texture(new FileHandle("data/taxi.jpg"));
		texs[1] = new Texture(new FileHandle("data/policecar.jpg"));
		texs[2] = new Texture(new FileHandle("data/schoolbus.jpg"));
		texs[3] = new Texture(new FileHandle("assets/bus.jpg"));
		
		this.number = 2;
		
		this.backgroundColor = Color.GREEN;
		
		probState = new DialogBox("Select the schoolbus.",Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		
	}
	
	
	public void render(SpriteBatch batch){
		for(int i = 0; i < 2; i ++){
			for(int j = 0; j < 2; j ++){
				batch.draw(texs[2*i+j], Gdx.graphics.getWidth() * .5f * i + texs[i+j].getWidth()/8, 
						Gdx.graphics.getHeight() * .5f * j + texs[i+j].getHeight()/8,
						Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4
				);
			}
		}
	}
	
	public void render(ShapeRenderer sRender){
		
	}
	
	public void update(){
		
		probState.update(true);
		
		if(Gdx.input.justTouched()){
			for(int i = 0; i < 2; i ++){
				for(int j = 0; j < 2; j ++){
					Rectangle r = new Rectangle(Gdx.graphics.getWidth() * .5f * i + texs[i+j].getWidth()/8, 
							Gdx.graphics.getHeight() * .5f * j + texs[i+j].getHeight()/8,
							Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
					if(r.contains(Gdx.input.getX(), Gdx.input.getY())){
						System.out.println(2*i + j);
					}
				}
			}
		}
		
	}
	
}
