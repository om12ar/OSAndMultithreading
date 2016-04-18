package diskSchedulers;

import java.util.ArrayList;
import java.util.Collections;

public class SCAN extends SchedulingStrategy {
	private int diskSize ;
	private boolean seekToEdge;
	private int direction  ;
	
	public SCAN(int ds) {
		super();
		diskSize =ds;
		direction=1;
		seekToEdge = false;
	}

	public void serve(int startingPosition, ArrayList<Integer> requests) {
		int currentHeadPosition = startingPosition;
		 Collections.sort(requests);
		
		while(requests.size()>0){
			int nextRequest = getNext(currentHeadPosition , requests);
			
			if(seekToEdge){
				seekToEdge = false;
				if(direction==-1){
					totalHeadMovement+=diskSize-currentHeadPosition;
					sequence.add(currentHeadPosition +" -> "+ diskSize);
					currentHeadPosition=diskSize;
				}
				else{
					totalHeadMovement+=currentHeadPosition;
					sequence.add(currentHeadPosition +" -> "+ 0);
					currentHeadPosition=0;
				}
				
			}
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
		if(reachedEnd){
			if(direction==-1){
				index = 0;
			}else{
				index = requests.size()-1;
			}
			seekToEdge=true;
			direction*=-1;
		}
			
		
		return index;
	}

}
