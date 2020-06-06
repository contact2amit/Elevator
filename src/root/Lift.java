package root;

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

	public void stopAt(int floorNumber) {
		if (currentStatus.getCurrentFloor() < floorNumber) {
			floorsToStopUp.add(floorNumber);
		} else {
			floorsToStopDown.add(floorNumber);
		}
	}

	/*
	 * 
	 * 0-->10 4--:18
	 * 
	 * 10-->down
	 */
	public void process() {
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

	private void moveDown() {
		currentStatus.curentDirection = Direction.DOWN;
		while (!floorsToStopDown.isEmpty() && currentStatus.currentFloor > min(floorsToStopDown)) {
			currentStatus.currentFloor--;
			if (floorsToStopDown.contains(currentStatus.currentFloor)) {
				System.out.println("Stoping at-->" + currentStatus.currentFloor);
				floorsToStopDown.remove((Integer) currentStatus.currentFloor);
			}
		}
		if(floorsToStopUp.isEmpty() && floorsToStopDown.isEmpty()) {
			currentStatus.isMoving=false;
		}else {
			moveUP();
		}
	}

	private int min(List<Integer> list) {
		Collections.sort(list);
		return list.get(0);
	}

	private void moveUP() {
		currentStatus.curentDirection = Direction.UP;
		while (!floorsToStopUp.isEmpty() && currentStatus.currentFloor < max(floorsToStopUp)) {
			currentStatus.currentFloor++;
			if (floorsToStopUp.contains(currentStatus.currentFloor)) {
				System.out.println("Stoping at-->" + currentStatus.currentFloor);
				floorsToStopUp.remove((Integer) currentStatus.currentFloor);
			}
		}
		if(floorsToStopUp.isEmpty() && floorsToStopDown.isEmpty()) {
			currentStatus.isMoving=false;
		}else {
			moveDown();
		}
	}

	private int max(List<Integer> list) {
		Collections.sort(list);
		return list.get(list.size() - 1);
	}

	@Override
	public void run() {
		while (true) {
			if (this.floorsToStopUp.isEmpty() && floorsToStopDown.isEmpty()) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if(!currentStatus.isMoving)
					process();
			}
		}
	}

}
