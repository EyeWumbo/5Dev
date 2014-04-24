package handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;

public class TextField {
	
	String currentText;
	BitmapFont font;
	boolean isFocus;
	Color color;
	Vector2 position;
	Vector2 size;
	float cursorFader;
	float count;
	int currentCharacter, lastCharacter;
	
	public TextField(Vector2 position){
		font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
		font.setColor(Color.BLACK);
		color = font.getColor();
		this.currentText = "";
		this.size = new Vector2(font.getBounds("****************").width * 2,
				font.getBounds("****************").height * 2);
		this.position = new Vector2(position.x - size.x/2, position.y - size.y/2);
		this.isFocus = false;
		this.cursorFader = 0;
		this.count = 0;
		
	}
	
	public void update(float x, float y){
		if(x > position.x && x < position.x + size.x &&
				Gdx.graphics.getHeight() - y > position.y &&
				Gdx.graphics.getHeight() - y < position.y + size.y){
			this.isFocus = true;
		}
	}
	
	public void update(){
		if(isFocus){
			Gdx.input.setOnscreenKeyboardVisible(true);
			cursorFader = Math.abs((float)(Math.sin(count)));
			count += Gdx.graphics.getDeltaTime() * 2;
			if(count > 360){
				count = 0;
			}
			
			if(Gdx.input.isKeyPressed(Keys.BACKSPACE)){
				if(currentText.length() == 0){
					return;
				}
				currentText = currentText.substring(0, currentText.length() - 1);
			}
			
			if(currentText.length() > 12){
				return;
			}
			
			for(int i = Keys.A; i <= Keys.Z; i ++){
				if(Gdx.input.isKeyPressed(i) &&
						(lastCharacter != i) && 
						(currentCharacter == 0 || i != currentCharacter))
				{
					String s = Character.toString((char)(i + 36));
					currentText += s;
					lastCharacter = currentCharacter;
					currentCharacter = i;
				}
			}
			if(!Gdx.input.isKeyPressed(Keys.ANY_KEY)){
				lastCharacter = currentCharacter;
				currentCharacter = 0;
			}
		}
		
	}
	
	public void render(SpriteBatch batch, float fadeMultiplier){
		if(fadeMultiplier <= 0){
			return;
		}
		batch.setColor(Color.BLACK);
		font.setColor(color.r * fadeMultiplier,
				color.g * fadeMultiplier,
				color.b * fadeMultiplier,
				1);
		font.draw(batch, currentText, position.x + font.getBounds("*").width / 2,
				position.y + size.y / 2 + font.getBounds("*").height / 2);
		
	}
	
	public void render(ShapeRenderer sRender, float fadeMultiplier){
		
		sRender.setColor(1 * fadeMultiplier, 1 * fadeMultiplier, 1 * fadeMultiplier, 1);
		sRender.rect(position.x, position.y, size.x, size.y);
		
		if(isFocus){
			sRender.setColor(1 * cursorFader, 1 * cursorFader, 1 * cursorFader, 1);
			sRender.rect(position.x + font.getBounds(currentText).width + font.getBounds("*").width
					, position.y + (size.y * .15f)/2,
					font.getBounds("*").width/2, size.y * .85f);
		}
		
	}
	
}
