package diskSchedulers;

import java.util.ArrayList;

public abstract class SchedulingStrategy {
	int totalHeadMovement ;
	ArrayList<Integer> result ;
	ArrayList<String> sequence ;
	
	public SchedulingStrategy() {
		totalHeadMovement = 0;
		result = new ArrayList<>();
		sequence = new ArrayList<>();
	}
	
	public abstract void serve(int startingPosition ,  ArrayList<Integer> requests ) ;
	public void print() {
		
		System.out.println("sequence : "+sequence);
		System.out.println("result : " + result);
		System.out.println("total Head Movement :" + totalHeadMovement);
	}
	
}
