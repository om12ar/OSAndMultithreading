package diskSchedulers;

import java.util.ArrayList;

public class FCFS extends SchedulingStrategy {

	public FCFS() {
		super();
	}

	@Override
	public void serve(int startingPosition, ArrayList<Integer> requests) {
		int currentHeadPosition = startingPosition;
		for(int i =0 ;i < requests.size() ;i++){
			totalHeadMovement+=Math.abs(currentHeadPosition - requests.get(i));
			result.add(requests.get(i));
			sequence.add(currentHeadPosition +" -> "+ requests.get(i));
			currentHeadPosition = requests.get(i) ;
		}
	}
	@Override
	public int getNext(int currentPosition , ArrayList<Integer> requests) {
		return -1;
	}

}
