package state;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import handler.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ScoreState extends State{
	
	Texture oneStar, twoStar, threeStar;
	Texture g1, g2, g3, g4;
	Button testButton;
	int[] gameScores;
	BitmapFont g1Score;
	BitmapFont g2Score;
	BitmapFont g3Score;
	BitmapFont g4Score;
	public static final String filepath = "./data/SCORES.txt";
	String currentUser = "BRENT"; // REMOVE LATER
	
	public ScoreState(){
		
		testButton = new Button(3, "Main Menu", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		g1Score = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
		g2Score = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
		g3Score = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
		g4Score = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
		
		g1 = new Texture(Gdx.files.internal("data/gameLabels/Game1.png"));
		g2 = new Texture(Gdx.files.internal("data/gameLabels/Game2.png"));
		g3 = new Texture(Gdx.files.internal("data/gameLabels/Game3.png"));
		g4 = new Texture(Gdx.files.internal("data/gameLabels/Game4.png"));

		oneStar = new Texture(Gdx.files.internal("data/1star.png"));
		twoStar = new Texture(Gdx.files.internal("data/2star.png"));
		threeStar = new Texture(Gdx.files.internal("data/3star.png"));
		
		gameScores = new int[] {0,0,0,0}; // 4 games

		this.backgroundColor = Color.WHITE;
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
		
		batch.draw(g1, Gdx.graphics.getWidth()/3, 5*Gdx.graphics.getHeight()/6);
		batch.draw(g2, Gdx.graphics.getWidth()/3, 4*Gdx.graphics.getHeight()/6);
		batch.draw(g3, Gdx.graphics.getWidth()/3, 3*Gdx.graphics.getHeight()/6);
		batch.draw(g4, Gdx.graphics.getWidth()/3, 2*Gdx.graphics.getHeight()/6);
		
		for (int i = 0; i < 4; i++) {
			if (gameScores[i] == 1){
				batch.draw(oneStar, Gdx.graphics.getWidth()/2, (5-i)*Gdx.graphics.getHeight()/6);
			}
			else if (gameScores[i] == 2) {
				batch.draw(twoStar, Gdx.graphics.getWidth()/2, (5-i)*Gdx.graphics.getHeight()/6);
			}
			else if (gameScores[i] == 3) {
				batch.draw(threeStar, Gdx.graphics.getWidth()/2, (5-i)* Gdx.graphics.getHeight()/6);
			}
		}

	}

	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		super.render(sRender);
		try
		{
			getScores();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testButton.render(sRender, fadeMultiplier);
		
	}
	
	private void getScores() throws IOException {
		File file = new File(filepath);
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		String user = "";
		
		try
		{
			while(user != null)
			{
				user = in.readLine();
				if(user != null)
					{
						if (user.equals(currentUser)){
							break;
						}
					}
			}
			for (int i = 0; i < 4; i++){
				gameScores[i] = Integer.parseInt(in.readLine());
			}
		}
		catch(IOException e)
		{
		}
		finally
		{
			in.close();
		}
	}

}
