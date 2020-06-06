package root;

import controler.*;

public class LiftMain {

	public static void main(String[] args) {
		
		
		LiftControler controler = initLift(1, 10);
		Command cmd ;
		//
		controler.excecute("OUT", 2, "UP");
		controler.excecute("OUT", 14, "UP");
		controler.excecute("OUT", 8, "DOWN");
		controler.excecute("OUT", 13, "UP");
		
		
		controler.excecute("IN", 1, 22);
		controler.excecute("IN", 1, 4);
		controler.excecute("IN", 1, 16);
		
		controler.excecute("IN", 2, 14);
		controler.excecute("IN", 4, 8);
		controler.excecute("IN", 3, 13);
		
		
	}


	private static LiftControler initLift(int liftCount, int floorCount) {
		
		return new LiftControler(liftCount, floorCount);
		
	}

}
