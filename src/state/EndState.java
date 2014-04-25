package state;

import handler.Button;
import handler.DialogBox;
import app.Application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class EndState extends State{

	int score, gameName;
	float currentWidth, buttonFader;
	Texture starCounter;
	DialogBox box;
	Button toNext, toMenu;
	
	public EndState(int score, int gameName){
		
		this.score = score;
		starCounter = (new Texture(new FileHandle("data/stars.png")));		
		this.backgroundColor = Color.LIGHT_GRAY;
		switch(score){
		case 1:
			this.box = new DialogBox("Okay!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		case 2:
			this.box = new DialogBox("Good!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		case 3:
			this.box = new DialogBox("Great!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		}
		toNext = new Button(12, "Next", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 15 / 100);
		toMenu = new Button(4, "Menu", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 10 / 100);
		this.gameName = gameName;
		writeScores();
		RoutineState.gameCompleted(gameName);
	}
	
	private void writeScores(){
		FileHandle f = new FileHandle("data/SCORES.TXT");
		String[] strs = f.readString().split("\n");
		for(int i = 0; i < strs.length; i ++){
			String s = strs[i].trim();
			if(s.equals(Application.USER)){
				if(Integer.parseInt(strs[i+gameName].trim()) < score){
					strs[i+gameName] = Integer.toString(score);
				}
			}
		}
		f.writeString("", false);
		for(int i = 0; i < strs.length - 1; i ++){
			f.writeString(strs[i] + "\n", true);
		}
		f.writeString(strs[strs.length - 1], true);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(this.isFullColor()){
			if(currentWidth < starCounter.getWidth() * score / 6 - 5){
				currentWidth += 64f * Gdx.graphics.getDeltaTime();
			}
			box.update(currentWidth >= starCounter.getWidth() * score / 6 - 5);
		}
		if(box.getCompletionStatus()){
			if(Gdx.input.justTouched()){
				toNext.update(Gdx.input.getX(), Gdx.input.getY());
				toMenu.update(Gdx.input.getX(), Gdx.input.getY());
			}
			if(buttonFader < 1){
				buttonFader += Gdx.graphics.getDeltaTime();
			}
			else{
				buttonFader = 1;
			}
			
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		super.render(batch);
		batch.draw(starCounter, (Gdx.graphics.getWidth() - starCounter.getWidth() / 2) / 2,
				(Gdx.graphics.getHeight() - starCounter.getHeight() / 2) / 2,
				starCounter.getWidth() / 2, starCounter.getHeight() / 2);
		box.render(batch, fadeMultiplier);
		
		if(box.getCompletionStatus()){
			batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b,
					batch.getColor().a * buttonFader);
			toNext.render(batch, fadeMultiplier);
			toMenu.render(batch, fadeMultiplier);
		}
		
	}

	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		super.render(sRender);
		
		sRender.setColor(Color.YELLOW.r * fadeMultiplier,
				Color.YELLOW.g * fadeMultiplier,
				Color.YELLOW.b * fadeMultiplier,
				Color.YELLOW.a);
		sRender.rect((Gdx.graphics.getWidth() - starCounter.getWidth() / 2) / 2,
				(Gdx.graphics.getHeight() - starCounter.getHeight() / 2) / 2,
				currentWidth, starCounter.getHeight() / 2);
		
		box.render(sRender, fadeMultiplier);
		
		if(box.getCompletionStatus()){
			sRender.setColor(sRender.getColor().r, sRender.getColor().g, 
					sRender.getColor().b, sRender.getColor().a * buttonFader);
			toNext.render(sRender, fadeMultiplier * buttonFader);
			toMenu.render(sRender, fadeMultiplier * buttonFader);
		}
		
	}

}
