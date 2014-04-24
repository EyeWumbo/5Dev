package state;
import java.io.*;

import handler.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AccountCreationState extends State{
	
	public static final String filepath = "data//USERNAMES.txt";
	
	Button testButton;
	
	public AccountCreationState(){
		this.testButton = new Button(2, "Landing", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		this.backgroundColor = Color.CYAN;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(Gdx.input.justTouched()){
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
			try
			{
				File fnames = new File(filepath);
				writer = new BufferedWriter(new FileWriter(fnames, true));
	            writer.write(name);
	            writer.newLine();
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
				}
				catch(Exception e)
				{}
			}
		}
	}

}
