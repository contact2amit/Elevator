package root;

import controler.Event;

public class FloorButton implements Button {

	int id;
	ButtonState state;
	Event event;

	public FloorButton(Event event, int id) {
		this.event = event;
		this.id = id;
	}

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
