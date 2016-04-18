package diskSchedulers;

import java.util.ArrayList;
import java.util.Collections;

public class LOOK extends SchedulingStrategy {
	private int direction = 1 ; 
	public LOOK() {
		super();
	}
	@Override
	public void serve(int startingPosition, ArrayList<Integer> requests) {
		int currentHeadPosition = startingPosition;
		 Collections.sort(requests);
		
		while(requests.size()>0){
			int nextRequest = getNext(currentHeadPosition , requests );
			totalHeadMovement+=Math.abs(currentHeadPosition- requests.get(nextRequest));
			result.add(requests.get(nextRequest));
			sequence.add(currentHeadPosition +" -> "+ requests.get(nextRequest));
			currentHeadPosition = requests.get(nextRequest) ;
			requests.remove(nextRequest);
		}

	}
	public int getNext(int currentPosition , ArrayList<Integer> requests) {
		
		int index = -1;
		boolean reachedEnd = true ;
		if(direction ==1){
			for(int i = 0 ; i<requests.size() ; i++){
				if(requests.get(i)>currentPosition){			
					index = i;
					reachedEnd= false;
					break;
				}
			}
		}
		else{
			for(int i = requests.size()-1 ; i>=0 ; i--){
				if(requests.get(i)<currentPosition){			
					index = i;
					reachedEnd= false;
					break;
				}
			}
		}
		//reached end -> reverse direction
		if(reachedEnd){
			if(direction==-1){
				index = 0;
			}else{
				index = requests.size()-1;
			}
			
			direction*=-1;
		}
			
		
		return index;
	}

}
