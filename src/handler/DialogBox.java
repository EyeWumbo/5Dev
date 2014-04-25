package handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class DialogBox {
	
	BitmapFont font;
	String displayText;
	Vector2 position, size;
	boolean display, touched, done;
	float fader = 0;
	Texture bgTex;
	
	public DialogBox(String text, float x, float y){
		this.displayText = text;
		font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
		this.size = new Vector2(Gdx.graphics.getWidth() * .85f,
				Gdx.graphics.getHeight() * .45f);
		Vector2 temp = new Vector2(font.getWrappedBounds(displayText, 36).width,
				font.getWrappedBounds(displayText, 36).height);
		position = new Vector2(x - temp.x / 2, y - temp.y / 2);
		bgTex = new Texture(new FileHandle("data/UIBull.png"));
		touched = false;
	}
	
	public void update(boolean display){
		this.display = display;
		if(display && fader < 1 && !touched){
			fader += Gdx.graphics.getDeltaTime();
			if(fader > 1){
				fader = 1;
			}
		}
		if(touched){
			fader -= Gdx.graphics.getDeltaTime();
			if(fader < 0){
				fader = 0;
				done = true;
			}
		}
		if(Gdx.input.justTouched() && this.display){
			touched = true;
		}
	}
	
	public boolean getCompletionStatus(){
		return done;
	}
	
	public boolean getDisplayable(){
		return display;
	}
	
	public void render(SpriteBatch batch, float fadeMultiplier){
		if(display){
			batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, fader);
			batch.draw(bgTex, Gdx.graphics.getWidth() * .075f,
					Gdx.graphics.getHeight() * .25f,
					Gdx.graphics.getWidth() * .85f,
					Gdx.graphics.getHeight() * .45f);
			font.setColor(font.getColor().r, font.getColor().g, font.getColor().b, fader);
			font.draw(batch, displayText, position.x, position.y);
		}
	}
	
	public float getFader(){
		return fader;
	}
	
	public void render(ShapeRenderer sRender, float fadeMultiplier){
		
	}
	
}
