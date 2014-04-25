package state.game;

import handler.DialogBox;
import app.Application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class ShirtGame extends Game{
	Texture[] texs;
	boolean isDone;
	boolean[] greyed = {false, false, false, false};
	DialogBox probState, solState;
	float[] greyTimer = {1,1,1,1};
	Color batchColor;
	int randomBit;
	
	public ShirtGame(){
		texs = new Texture[4];
		texs[0] = new Texture(new FileHandle("data/shirt_green.jpg"));
		texs[1] = new Texture(new FileHandle("data/shirt_blue.jpg"));
		texs[2] = new Texture(new FileHandle("data/shirt_red.jpg"));
		texs[3] = new Texture(new FileHandle("data/shirt_white.jpg"));
		
		this.number = 3;
		
		this.backgroundColor = Color.GREEN;
		
		randomBit = (int)Math.random() * 4;
		String[] possibles = {"green", "blue", "red", "white"};
		
		probState = new DialogBox("Select the " + possibles[randomBit] + " shirt!",
				Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		solState = new DialogBox("Good Job!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		isDone = false;
	}
	
	
	public void render(SpriteBatch batch){
		batchColor = batch.getColor();
		for(int i = 0; i < 2; i ++){
			for(int j = 0; j < 2; j ++){
				if(greyed[i + 2*j]){
					batch.setColor(batchColor.r * greyTimer[i+2*j],
							batchColor.g * greyTimer[i+2*j],
							batchColor.b * greyTimer[i+2*j],
							batchColor.a * greyTimer[i+2*j]);
					greyTimer[i+2*j] -= Gdx.graphics.getDeltaTime();
					if(greyTimer[i+2*j] <= 0.3f){
						greyTimer[i+2*j] = 0.3f;
					}
				}
				else{
					batch.setColor(batchColor);
				}
				batch.draw(texs[i+2*j], Gdx.graphics.getWidth() * .5f * i + texs[i+2*j].getWidth()/8, 
						Gdx.graphics.getHeight() * .5f * j + texs[2*i+j].getHeight()/8,
						Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4
				);
			}
		}
		
		probState.render(batch, fadeMultiplier);
		solState.render(batch, fadeMultiplier);
		
	}
	
	public void render(ShapeRenderer sRender){
		super.render(sRender);
	}
	
	public void update(){
		
		probState.update(true);
		solState.update(isDone);
		if(isDone){
			if(solState.getCompletionStatus()){
				int score = 3;
				for(int i = 0; i < 4; i ++){
					if(greyed[i] == true){
						score -= 1;
					}
				}
				if(score < 1){
					score = 1;
				}
				Application.TRANSITION_SIGNAL = 80 + score;
			}
			return;
		}
		
		if(Gdx.input.justTouched()){
			for(int i = 0; i < 2; i ++){
				for(int j = 0; j < 2; j ++){
					int k = Math.abs(j - 1);
					Rectangle r = new Rectangle(Gdx.graphics.getWidth() * .5f * i + texs[2*i+j].getWidth()/8, 
							(Gdx.graphics.getHeight() * .5f * k + texs[2*i+j].getHeight()/8),
							Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
					if(r.contains(Gdx.input.getX(), Gdx.input.getY())){
						if(i + 2*j == randomBit){
							isDone = true;
						}
						else{
							greyed[i + 2*j] = true;
						}
						System.out.println(i + 2*j);
					}
				}
			}
		}
		
	}
	
}
