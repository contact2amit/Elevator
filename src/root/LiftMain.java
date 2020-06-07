package root;

import controler.*;

public class LiftMain {

	public static void main(String[] args) throws Exception {
		
		
		LiftControler controler = initLift(1, 25);
		Command cmd ;
		//
		controler.pushButton("OUT", 2, RequestType.UP);
		controler.pushButton("OUT", 14, RequestType.UP);
		controler.pushButton("OUT", 8, RequestType.UP);
		Thread.sleep(500);

		controler.pushButton("IN", 0, 4);
		controler.pushButton("IN", 0, 16);
		Thread.sleep(500);
		
		controler.pushButton("OUT", 13, RequestType.DOWN);
		controler.pushButton("OUT", 2, RequestType.DOWN);
		controler.pushButton("OUT", 14, RequestType.DOWN);
		controler.pushButton("OUT", 8, RequestType.DOWN);
		Thread.sleep(500);
		
		controler.pushButton("IN", 0, 22);
		controler.pushButton("IN", 0, 4);
		Thread.sleep(500);
		
		controler.pushButton("OUT", 2, RequestType.DOWN);
		controler.pushButton("OUT", 14, RequestType.DOWN);
		Thread.sleep(500);
		
		controler.pushButton("IN", 0, 16);
		controler.pushButton("IN", 0, 14);
		
		

	}


	private static LiftControler initLift(int liftCount, int floorCount) {
		
		return new LiftControler(liftCount, floorCount);
		
	}

}
