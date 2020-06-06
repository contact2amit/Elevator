package root;

import controler.Event;

public class LiftButton implements Button{
	
	ButtonState state;
	Event event;

	public Event push() {
		if(state==ButtonState.ON)
		{
			state = ButtonState.OFF;
			return null;
		}
		state = ButtonState.ON;
		return event;
	}

	public ButtonState getState() {
		return state;
	}

	public void setState(ButtonState state) {
		this.state = state;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
