package scheduling;

import java.util.Comparator;

public class CompareProcesses implements Comparator<Process> {

	@Override
	public int compare(Process one, Process two) {
		if(one.arrivalTime > two.arrivalTime){
			return 1;
		}
		else if(one.arrivalTime < two.arrivalTime){
			return -1;
		}
		else{
			
			if(one.burstTime > two.burstTime){
				return 1;
			}
			else if(one.burstTime < two.burstTime){
				return -1;
			}
			else{
				return 0;
			}
		}

	}

}
