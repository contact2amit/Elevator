package root;

import java.util.List;

import controler.Event;

public class LiftControler {

	List<Lift> lifts;
	int totalFloor;

	public void excecute(String string, int floorNumber, String direction) {
		Lift lift = getClosestLift(floorNumber, direction);
		lift.stopAt(floorNumber);
	}
	
	private Lift getClosestLift(int floorNumber, String direction) {
		int distance = Integer.MAX_VALUE;
		Lift l=null;
		for(Lift lift: lifts) {
			
		}
		return l;
	}

	public void excecute(String string, int liftId, int floorNumber) {
		Lift lift = lifts.get(liftId);
		lift.stopAt(floorNumber);
		if(!lift.getCurrentStatus().isMoving) {
			lift.start();
		}
	}
}
