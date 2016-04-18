package diskSchedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String SRequests = in.nextLine();
		System.out.println("Initial head start cylinder:");
		int startingPosition = in.nextInt();
		System.out.println("Disc size -for SCAN and CSCAN- :");
		int discSize = in.nextInt();		
		String[] tempStrings = SRequests.split("\\s*,\\s*");
		
		ArrayList<Integer> requests = new ArrayList<>();
		for(String s : tempStrings){
			requests.add(Integer.parseInt(s));
		}
		
		System.out.println("-----------------FCFS--------------------");
		FCFS fcfs = new FCFS();
		fcfs.serve(startingPosition, new ArrayList<>(requests));
		fcfs.print();
		System.out.println("-----------------SSTF--------------------");
		SSTF sstf = new SSTF();
		sstf.serve(startingPosition, new ArrayList<>(requests));
		sstf.print();
		System.out.println("-----------------CLOOK-------------------");
		CLOOK clook = new CLOOK();
		clook.serve(startingPosition, new ArrayList<>(requests));
		clook.print();
		System.out.println("-----------------LOOK--------------------");
		LOOK look = new LOOK();
		look.serve(startingPosition, new ArrayList<>(requests));
		look.print();
		System.out.println("-----------------CSCAN-------------------");
		CSCAN cscan = new CSCAN(discSize);
		cscan.serve(startingPosition, new ArrayList<>(requests));
		cscan.print();
		System.out.println("-----------------SCAN--------------------");
		SCAN scan = new SCAN(discSize);
		scan.serve(startingPosition, new ArrayList<>(requests));
		scan.print();
		
		
		
	}

}
