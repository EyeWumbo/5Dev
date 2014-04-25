package state;
import java.io.*;
import java.util.Vector;

import handler.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AccountCreationState extends State{
	
	public static final String filepath = "data//USERNAMES.txt";
	public static final String ScoresPath = "data//SCORES.txt";
	
	Button LandingButton; 
	TextField field;
	
	public AccountCreationState(){
		this.LandingButton = new Button(2, "Welcome", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		this.backgroundColor = Color.CYAN;
		this.field = new TextField(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3), true);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(Gdx.input.justTouched() && !fade)
		{
			LandingButton.update(Gdx.input.getX(), Gdx.input.getY());
			field.update(Gdx.input.getX(), Gdx.input.getY());
		}
		if(field.update() == true)
		{
			try {
				addUser(field.getCurrentText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
		{
			//System.out.println("woo");
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		super.render(batch);
		LandingButton.render(batch, fadeMultiplier);
		field.render(batch, fadeMultiplier);
	}

	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		super.render(sRender);
		LandingButton.render(sRender, fadeMultiplier);
		field.render(sRender, fadeMultiplier);
	}
	
	public void setUp() throws IOException
	{
		if(doesFileExist(ScoresPath) == false)
		{
			BufferedWriter writer = null; 
			writer = new BufferedWriter(new FileWriter(ScoresPath));
            writer.write("");
            writer.close();	
		}
	}
		
	
	// does nothing if user already exists
	public Boolean doesUserExist(String name) throws IOException
	{	
		File file = new File(filepath);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();; 
		
		while(line != null)
		{
			if(line.equals(name))
			{
				in.close();
				return true;
			}
			line = in.readLine();
		}
		in.close();
		return false;
	}
	
	
	public void addUser(String name) throws IOException
	{
		//System.out.println(doesUserExist(name));
		if(doesUserExist(name) == false)
		{
			// need to write to "login.txt" given a new NAME.
			BufferedWriter writer = null; 
			BufferedWriter writer2 = null; 
			try
			{
				File fnames = new File(filepath);
				writer = new BufferedWriter(new FileWriter(fnames, true));
	            writer.write(name);
	            writer.newLine();
	            
				File fscores = new File(ScoresPath);
				writer2 = new BufferedWriter(new FileWriter(fscores, true));
	            writer2.write(name);
	            writer2.newLine();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					writer.close();
					writer2.close();
				}
				catch(Exception e)
				{}
			}
		}
	}

}
