package handler;

import state.State;

public class Button {
	
	private State stateToMove;
	String buttonText;
	
	public Button(State state, String buttonText){
		this.stateToMove = state;
		this.buttonText = buttonText;
	}
	
	
	
}
