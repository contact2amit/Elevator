package root;

public class Status {
	Boolean isMoving;
	Direction curentDirection;
	int currentFloor;
	int numberOfPassangers;

	public Status() {
		isMoving = false;
		currentFloor =0 ;
		numberOfPassangers = 0;
		curentDirection=Direction.STOP;
	}
	
	public Boolean getIsMoving() {
		return isMoving;
	}

	public void setIsMoving(Boolean isMoving) {
		this.isMoving = isMoving;
	}

	public Direction getCurentDirection() {
		return curentDirection;
	}

	public void setCurentDirection(Direction curentDirection) {
		this.curentDirection = curentDirection;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public int getNumberOfPassangers() {
		return numberOfPassangers;
	}

	public void setNumberOfPassangers(int numberOfPassangers) {
		this.numberOfPassangers = numberOfPassangers;
	}

}
