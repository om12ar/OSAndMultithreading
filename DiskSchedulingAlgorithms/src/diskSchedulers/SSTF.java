package diskSchedulers;

import java.util.ArrayList;

public class SSTF extends SchedulingStrategy {

	public SSTF() {
		super();
	}

	@Override
	public void serve(int startingPosition, ArrayList<Integer> requests) {
		int currentHeadPosition = startingPosition;
		while(requests.size()>0){
			int SST = getSST(currentHeadPosition , requests);
			totalHeadMovement+=Math.abs(currentHeadPosition- requests.get(SST));
			result.add(requests.get(SST));
			sequence.add(currentHeadPosition +" -> "+ requests.get(SST));
			currentHeadPosition = requests.get(SST) ;
			requests.remove(SST);
		}
	}
	public int getSST(int currentPosition , ArrayList<Integer> requests) {
		int SST =Integer.MAX_VALUE;
		int index = -1;
		for(int i = 0 ; i<requests.size() ; i++){
			if(Math.abs(currentPosition -requests.get(i)) < SST){
				SST = Math.abs(currentPosition -requests.get(i));
				index = i;
			}
		}
		return index;
	}

}
