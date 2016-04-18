package diskSchedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String SRequests = in.nextLine();
		System.out.println("Initial head start cylinder:");
		int startingPosition = in.nextInt();
		System.out.println("Disc size -for CSCAN and CLOOK:");
		int discSize = in.nextInt();
		String[] tempStrings = SRequests.split("\\s*,\\s*");
		
		ArrayList<Integer> requests = new ArrayList<>();
		for(String s : tempStrings){
			requests.add(Integer.parseInt(s));
		}
		
		FCFS fcfs = new FCFS();
		fcfs.serve(startingPosition, new ArrayList<>(requests));
		fcfs.print();
		System.out.println("-----------------------------------------");
		SSTF sstf = new SSTF();
		sstf.serve(startingPosition, new ArrayList<>(requests));
		sstf.print();
		System.out.println("-----------------------------------------");
		CLOOK clook = new CLOOK();
		clook.serve(startingPosition, new ArrayList<>(requests));
		clook.print();
	}

	
}
