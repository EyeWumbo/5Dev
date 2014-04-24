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
	boolean isFocus, selectable, visible, isFading;
	Color color;
	Vector2 position, size, animationDestination;
	float cursorFader, containerFader;
	float count;
	int currentCharacter, lastCharacter;
	
	public TextField(Vector2 position, boolean visible){
		font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
		font.setColor(Color.BLACK);
		color = font.getColor();
		this.currentText = "";
		this.size = new Vector2(font.getBounds("****************").width * 2,
				font.getBounds("****************").height * 2);
		this.position = new Vector2(position.x - size.x/2, position.y - size.y/2);
		this.isFocus = false;
		this.selectable = true;
		this.cursorFader = 0;
		if(visible){containerFader=1;}
		this.count = 0;
		this.visible = visible;
		
	}
	
	public void update(float x, float y){
		if(x > position.x && x < position.x + size.x &&
				Gdx.graphics.getHeight() - y > position.y &&
				Gdx.graphics.getHeight() - y < position.y + size.y && selectable){
			this.isFocus = true;
		}
	}
	
	public boolean update(){
		
		if(!visible){
			if(containerFader > 0){
				containerFader -= 0.02f;
			}
			return false;
		}
		
		if(isFading){
			if(containerFader < 1){
				containerFader += 0.02f;
			}
			if(containerFader <= 0){
				isFading = false;
			}
		}
		
		if(!selectable && (this.animationDestination != null && this.position != this.animationDestination)){
			Vector2 temp = this.animationDestination.cpy().sub(this.position);
			this.position = this.position.add(temp.nor());
		}
		
		if(isFocus){
			Gdx.input.setOnscreenKeyboardVisible(true);
			cursorFader = Math.abs((float)(Math.sin(count)));
			count += Gdx.graphics.getDeltaTime() * 2;
			if(count > 360){
				count = 0;
			}
			
			if(Gdx.input.isKeyPressed(Keys.BACKSPACE)){
				if(currentText.length() == 0){
					return false;
				}
				currentText = currentText.substring(0, currentText.length() - 1);
				try {
					Thread.sleep(75);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(currentText.length() > 12){
				return false;
			}
			
			if(Gdx.input.isKeyPressed(Keys.ENTER)){
				this.selectable = false;
				this.isFocus = false;
				System.out.println("hai");
				return true;
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
		return false;
		
	}
	
	public void animateTo(Vector2 heading){
		
		this.animationDestination = new Vector2(heading.x - size.x / 2,
				heading.y - size.y / 2);
		System.out.println(this.position);
	}
	
	public void setVisible(boolean bool){
		visible = bool;
		isFading = true;
	}
	
	public void render(SpriteBatch batch, float fadeMultiplier){
		if(fadeMultiplier <= 0 || !visible){
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
		
		if(!visible){
			return;
		}
		
		sRender.setColor(1 * fadeMultiplier * containerFader,
				1 * fadeMultiplier * containerFader,
				1 * fadeMultiplier * containerFader, 1);
		sRender.rect(position.x, position.y, size.x, size.y);
		
		if(isFocus){
			sRender.setColor(1 * cursorFader, 1 * cursorFader, 1 * cursorFader, 1);
			sRender.rect(position.x + font.getBounds(currentText).width + font.getBounds("*").width,
					position.y + (size.y * .15f)/2, font.getBounds("*").width/2, size.y * .85f);
		}
		
	}
	
	public String getCurrentText()
	{
		return this.currentText; 
	}
	
}
