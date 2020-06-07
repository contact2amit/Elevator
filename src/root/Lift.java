package root;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lift implements Runnable {
	List<Button> buttons;
	Status currentStatus;
	int maxCapacity;
	List<Integer> floorsToStopUp;
	List<Integer> floorsToStopDown;
	int id;

	public Lift(int id) {
		this.id = id;
		currentStatus = new Status();
		floorsToStopUp = new ArrayList<>();
		floorsToStopDown = new ArrayList<>();
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public synchronized void stopAt(int floorNumber) {
		if (currentStatus.getCurrentFloor() < floorNumber) {
			floorsToStopUp.add(floorNumber);
		} else {
			floorsToStopDown.add(floorNumber);

		}
	}

	public synchronized void stopAt(int floorNumber, RequestType requestType) {
		if(requestType==RequestType.UP) {
			floorsToStopUp.add(floorNumber);
		}else {
			floorsToStopDown.add(floorNumber);
		}
			
		
		
	}
	/*
	 * 
	 * 0-->10 4--:18
	 * 
	 * 10-->down
	 */
	public synchronized void process() throws Exception {
		currentStatus.isMoving = true;
		if (floorsToStopUp.size() > 0) {
			if (currentStatus.getCurrentFloor() < floorsToStopUp.get(0)) {
				moveUP();

			} else {
				currentStatus.curentDirection = Direction.DOWN;
				moveDown();
			}
		}
		if (floorsToStopDown.size() > 0) {
			if (currentStatus.getCurrentFloor() < floorsToStopDown.get(0)) {
				moveUP();

			} else {
				currentStatus.curentDirection = Direction.DOWN;
				moveDown();
			}
		}

	}

	private synchronized void moveDown() throws Exception {
		currentStatus.curentDirection = Direction.DOWN;
		while (!floorsToStopDown.isEmpty() && currentStatus.currentFloor > min(floorsToStopDown)) {
			currentStatus.currentFloor--;
			if (floorsToStopDown.contains(currentStatus.currentFloor)) {
				System.out.println("Lift# "+getId()+", Stoping at floor# " + currentStatus.currentFloor+", current direction->"+currentStatus.curentDirection);
				Thread.sleep(500);
				floorsToStopDown.remove((Integer) currentStatus.currentFloor);
			}
		}
			currentStatus.isMoving=false;
		
	}

	private synchronized int min(List<Integer> list) {
		Collections.sort(list);
		return list.get(0);
	}

	private synchronized void moveUP() throws Exception {
		currentStatus.curentDirection = Direction.UP;
		while (!floorsToStopUp.isEmpty() && currentStatus.currentFloor < max(floorsToStopUp)) {
			currentStatus.currentFloor++;
			if (floorsToStopUp.contains(currentStatus.currentFloor)) {
				System.out.println("Lift# "+getId()+" Stoping at floor# " + currentStatus.currentFloor+", current direction->"+currentStatus.curentDirection);
				Thread.sleep(500);
				floorsToStopUp.remove((Integer) currentStatus.currentFloor);
			}
		}
			currentStatus.isMoving=false;
	}

	private synchronized int max(List<Integer> list) {
		Collections.sort(list);
		return list.get(list.size() - 1);
	}

	@Override
	public void run() {
		while (true) {
			try {
			if (this.floorsToStopUp.isEmpty() && floorsToStopDown.isEmpty()) {
				
					Thread.sleep(1000);
				
			} else {
				if(!currentStatus.isMoving)
					process();
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<Integer> getFloorsToStopUp() {
		return floorsToStopUp;
	}

	public void setFloorsToStopUp(List<Integer> floorsToStopUp) {
		this.floorsToStopUp = floorsToStopUp;
	}

	public List<Integer> getFloorsToStopDown() {
		return floorsToStopDown;
	}

	public void setFloorsToStopDown(List<Integer> floorsToStopDown) {
		this.floorsToStopDown = floorsToStopDown;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
