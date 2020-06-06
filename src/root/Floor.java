package root;

import controler.Event;

public class Floor {
	int id;
	FloorButton up ;
	FloorButton down ;

	public Floor(int id) {
		this.id=id;
		up =new FloorButton(Event.MOVE_UP, id);
		down = new FloorButton(Event.MOVE_DOWN, id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
