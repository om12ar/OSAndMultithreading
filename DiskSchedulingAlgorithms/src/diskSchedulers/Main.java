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
		System.out.println("Disc size -for SCAN and CSCAN- :");
		int discSize = in.nextInt();		
		String[] tempStrings = SRequests.split("\\s* \\s*");
		
		ArrayList<Integer> requests = new ArrayList<>();
		for(String s : tempStrings){
			requests.add(Integer.parseInt(s));
		}
		
		System.out.println("-----------------FCFS--------------------");
		FCFS fcfs = new FCFS();
		fcfs.serve(startingPosition, new ArrayList<>(requests));
		fcfs.print();
		
		GUI frame1 = new GUI("FCFS",startingPosition, discSize, fcfs.result, fcfs.sequence);
		frame1.setVisible(true);
		
		System.out.println("-----------------SSTF--------------------");
		SSTF sstf = new SSTF();
		sstf.serve(startingPosition, new ArrayList<>(requests));
		sstf.print();
		
		GUI frame2 = new GUI("SSTF",startingPosition, discSize, sstf.result, sstf.sequence);
		frame2.setVisible(true);
		
		System.out.println("-----------------CLOOK-------------------");
		CLOOK clook = new CLOOK();
		clook.serve(startingPosition, new ArrayList<>(requests));
		clook.print();
		
		GUI frame3 = new GUI("CLOOK",startingPosition, discSize, clook.result, clook.sequence);
		frame3.setVisible(true);
		
		System.out.println("-----------------LOOK--------------------");
		LOOK look = new LOOK();
		look.serve(startingPosition, new ArrayList<>(requests));
		look.print();
		
		GUI frame4 = new GUI("LOOK",startingPosition, discSize, look.result, look.sequence);
		frame4.setVisible(true);
		
		System.out.println("-----------------CSCAN-------------------");
		CSCAN cscan = new CSCAN(discSize);
		cscan.serve(startingPosition, new ArrayList<>(requests));
		cscan.print();
		
		GUI frame5 = new GUI("CSCAN",startingPosition, discSize, cscan.result, cscan.sequence);
		frame5.setVisible(true);
		
		System.out.println("-----------------SCAN--------------------");
		SCAN scan = new SCAN(discSize);
		scan.serve(startingPosition, new ArrayList<>(requests));
		scan.print();
		
		GUI frame6 = new GUI("SCAN",startingPosition, discSize, scan.result, scan.sequence);
		frame6.setVisible(true);
		
	}

}
