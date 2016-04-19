package diskSchedulers;

import java.util.ArrayList;
import java.util.Collections;

public class CSCAN extends SchedulingStrategy {
	private int diskSize ;
	private boolean seekToEnd;
	public CSCAN(int ds) {
		super();
		diskSize = ds;
		seekToEnd =false;
	}


	@Override
	public void serve(int startingPosition, ArrayList<Integer> requests) {
		int currentHeadPosition = startingPosition;
		 Collections.sort(requests);
		
		while(requests.size()>0){
			int nextRequest = getNext(currentHeadPosition , requests);
			
			if(seekToEnd){
				
				seekToEnd=false;
				totalHeadMovement+=diskSize-currentHeadPosition;
				sequence.add(currentHeadPosition +" -> "+ diskSize);
				currentHeadPosition=diskSize;
				
			}
			totalHeadMovement+=Math.abs(currentHeadPosition- requests.get(nextRequest));
			
			result.add(requests.get(nextRequest));
			sequence.add(currentHeadPosition +" -> "+ requests.get(nextRequest));
			currentHeadPosition = requests.get(nextRequest) ;
			requests.remove(nextRequest);
		}

	}
	@Override
	public int getNext(int currentPosition , ArrayList<Integer> requests) {
		
		int index = -1;
		boolean reachedEnd = true ;
		for(int i = 0 ; i<requests.size() ; i++){
			if(requests.get(i)>currentPosition){			
				index = i;
				reachedEnd= false;
				break;
			}
		}
		if(reachedEnd){
			System.out.println("CSCAN.getNext()");
			seekToEnd=true;
			index =0 ;
		}
			
		
		return index;
	}
}
