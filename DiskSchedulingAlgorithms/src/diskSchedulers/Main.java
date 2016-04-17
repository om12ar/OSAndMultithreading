package diskSchedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String SRequests = in.nextLine();
		int startingPosition = in.nextInt();
		String[] tempStrings = SRequests.split("\\s*,\\s*");
		
		ArrayList<Integer> requests = new ArrayList<>();
		for(String s : tempStrings){
			requests.add(Integer.parseInt(s));
		}
		
		FCFS fcfs = new FCFS();
		fcfs.serve(startingPosition, requests);
		fcfs.print();
		System.out.println("-----------------------------------------");
		SSTF sstf = new SSTF();
		sstf.serve(startingPosition, requests);
		sstf.print();
		
	}

	
}
