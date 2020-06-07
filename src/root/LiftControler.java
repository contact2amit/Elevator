package root;

import java.util.ArrayList;
import java.util.List;

import controler.Event;

public class LiftControler {

	List<Lift> lifts;
	int totalFloor;

	public LiftControler(int liftCount, int floorCount) {
		lifts = new ArrayList<>(liftCount);
		for(int i=0; i< liftCount; i++) {
			Lift l = new Lift(i);
			lifts.add(i, l);
			Thread t=new Thread(l);
			t.start();
		}
		this.totalFloor = floorCount;
	}

	public void pushButton(String string, int floorNumber, RequestType requestType) {
		if(!isRequest(0, floorNumber)) {
			System.out.println("Invalid input floorNumber--"+floorNumber);
			return;
		}
		System.out.println("Floor button of floor# "+ floorNumber+" pushed to go "+requestType);
		Lift lift = getClosestLift(floorNumber, requestType);
		System.out.println("request asigned to lift#"+lift.getId());
		lift.stopAt(floorNumber, requestType);
	}

	public void pushButton(String string, int liftId, int floorNumber) {
		if(!isRequest(liftId, floorNumber)) {
			System.out.println("Invalid input--liftId "+liftId+" floorNumber--"+floorNumber);
			return;
		}
		System.out.println("Lift button of lift# "+ liftId+" pushed for floor#"+floorNumber);
		Lift lift = lifts.get(liftId);
		lift.stopAt(floorNumber);
	}
	private Lift getClosestLift(int floorNumber, RequestType requestType) {
		int distance = Integer.MAX_VALUE;
		Lift l=null;
		for(Lift lift: lifts) {
			if(lift.currentStatus.isMoving && lift.currentStatus.curentDirection.name().equals(requestType.name())) {
				if(requestType==RequestType.UP) {
					if(lift.currentStatus.currentFloor < floorNumber) {
						if(distance < floorNumber - lift.currentStatus.currentFloor){
							distance = floorNumber - lift.currentStatus.currentFloor;
							l =lift;
						}
					}
				}else {
						if(lift.currentStatus.currentFloor > floorNumber) {
							if(distance <   lift.currentStatus.currentFloor - floorNumber){
								distance = lift.currentStatus.currentFloor - floorNumber;
								l =lift;
							}
						}
				}
			}
		}
		if(l==null) {
			l = lifts.get(0);
		}
		return l;
	}


	private boolean isRequest(int liftId, int floorNumber) {
		if(liftId < 0 || liftId> lifts.size()-1 || floorNumber<0 || floorNumber> totalFloor) {
			return false;
		}
		return true;
	}
}
