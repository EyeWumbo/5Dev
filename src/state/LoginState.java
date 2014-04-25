package state;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import handler.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class LoginState extends State {
		
	public static final String filepath = ".//data//USERNAMES.txt";

	
	public LoginState(){ 
		
		try
		{
			System.out.println("BEFORE ENTERING SET UP");
			setUp();
			run(); // should get names and display
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		
		this.backgroundColor = Color.GREEN;
		 
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();

		if(Gdx.input.justTouched() && !fade){
			for(int i = 0; i < ButtonNames.size(); i++)
			{
				Button button = ButtonList.get(i);
				button.update(Gdx.input.getX(), Gdx.input.getY());
			}
		}

	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		super.render(batch);
		for(int i = 0; i < ButtonNames.size(); i++)
		{
			Button button = ButtonList.get(i);
			button.render(batch, fadeMultiplier);
		}

	}
	
	@Override
	public void render(ShapeRenderer sRender) {
		// TODO Auto-generated method stub
		
		super.render(sRender);

		for(int i = 0; i < ButtonNames.size(); i++)
		{
			Button button = ButtonList.get(i);
			button.render(sRender, fadeMultiplier);
		}
	}
	
	public void createUserButtons()
	{
		makeButtonNames();
		for(int i = 0; i < ButtonNames.size(); i++)
		{
			int h = Gdx.graphics.getHeight();
			int w = Gdx.graphics.getWidth();
			if(i != ButtonNames.size() - 1)
			{
				Button UserButton = new Button(3, ButtonNames.get(i), w/2, h - 50 - (i*(h/(ButtonNames.size() ))));
				ButtonList.add(UserButton);
			}
			else
			{
				Button UserButton = new Button(1, ButtonNames.get(i), w/2, h - 50 - (i*(h/(ButtonNames.size() ))));
				ButtonList.add(UserButton);
			}
		}
	}
	
	
	public void makeButtonNames()
	{
		ButtonNames = users; 
		ButtonNames.add("CREATE NEW ACCOUNT");
		//ButtonNames.add("DELETE ALL USERS");
	}
	
	public void run() throws IOException
	{
		getNames();
		createUserButtons();
	}
	
	public void setUp() throws IOException
	{
		ButtonList = new Vector<Button> ();
		ButtonNames = new Vector<String> ();

		if(doesFileExist(filepath) == false)
		{
			System.out.println("INSIDE SETUP FX");
			BufferedWriter writer = null; 
			writer = new BufferedWriter(new FileWriter(filepath));
            writer.write("");
            writer.close();	
		}
	}
	

	
	public void deleteAllUsers() throws IOException
	{
		BufferedReader b = new BufferedReader(new FileReader(filepath));     
		if (b.readLine() != null) // if line not empty
		{
			b.close();
			BufferedWriter writer = null; 
			writer = new BufferedWriter(new FileWriter(filepath));
            writer.write("");
            writer.close(); 
		}
	}
	
	
	
	
	public void getNames() throws IOException 
	{
		File file = new File(filepath);;
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		String line = ""; 
		Vector<String> names = new Vector<String>();

		try
		{
			while(line != null)
			{
				line = in.readLine();
				if(line != null)
					{
						names.add(line);
					}
			} 
		}
		catch(IOException e)
		{
		}
		finally
		{
			users = names;
			in.close();
		}
	}

	public void displayUsers()
	{
		for(int i = 0; i < numUsers(); i++)
		{
			System.out.println(users.get(i));
		}
	}
	
	public int numUsers()
	{
		return users.size();
	}
	
	
	
	private Vector<String> users;  
	private Vector<Button> ButtonList;
	private Vector<String> ButtonNames;

}



