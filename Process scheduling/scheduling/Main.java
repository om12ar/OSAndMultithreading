package scheduling;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the number of processes  : ");
		int numOfProcesses= in.nextInt();
		System.out.println("Enter the RR quantum  : ");
		int quantum = in.nextInt();
		ArrayList<Process> processes = new ArrayList<>();
		for(int i =0 ;i < numOfProcesses ; i++){
			System.out.println("Enter Process name :");
			String n =in.next();
			System.out.println("Enter Arrival Time :");
			int Atime = in.nextInt();
			System.out.println("Enter burst Time :");
			int Btime = in.nextInt();
			processes.add(new Process(n, Atime, Btime));
		}
		System.err.println(processes);
		
		processes.sort(new CompareProcesses());
		System.out.println("FCFS");
		System.out.println("-------------------------------------------");
		FCFS(new ArrayList<Process>(processes));
		System.out.println("SJF");
		System.out.println("-------------------------------------------");
		SJF(new ArrayList<Process>(processes));
		System.out.println("RR");
		System.out.println("-------------------------------------------");
		RoundRobin(new ArrayList<Process>(processes),quantum);
		
	}
	
	private static void FCFS(ArrayList<Process> processes){
		
		int currentTime = processes.get(0).arrivalTime;
		int sumResponseTime = 0;
		
		for(int i = 0; i < processes.size(); i++){
			
			processes.get(i).responseTime = currentTime - processes.get(i).arrivalTime;
			processes.get(i).waitingTime = currentTime - processes.get(i).arrivalTime;
			System.out.println(processes.get(i) + " " + processes.get(i).responseTime);
			currentTime += processes.get(i).burstTime;
			sumResponseTime += processes.get(i).responseTime;
		}
		
		System.out.println("Average Response Time: " + sumResponseTime/(double)processes.size());
	}
	
	
	private static void SJF(ArrayList<Process> sent){
		
		LinkedList<Process> processes = new LinkedList<>();
		for(int i =0 ; i < sent.size(); i++){
			processes.add(sent.get(i));
		}
		
	
		
		int currentTime = processes.getFirst().arrivalTime;
		int sumResponseTime = 0;
		
		while(!processes.isEmpty()){
			
			Process nextExecuted = new Process("temp", Integer.MAX_VALUE, Integer.MAX_VALUE);
			
			if(processes.getFirst().arrivalTime >= currentTime){
				nextExecuted = processes.getFirst();

			}
			else{
				for(int i=0 ; i < processes.size(); i++){
					if(processes.get(i).arrivalTime > currentTime){
						break;
					}
					else{
						if(processes.get(i).burstTime < nextExecuted.burstTime ){
							nextExecuted = processes.get(i);
						}
					}
					
				}
				
			}
			
			
			nextExecuted.responseTime = currentTime - nextExecuted.arrivalTime;
			nextExecuted.waitingTime = currentTime - nextExecuted.arrivalTime;
			
			currentTime += nextExecuted.burstTime;
			
			sumResponseTime += nextExecuted.responseTime;
			System.out.println(nextExecuted + " "+ nextExecuted.responseTime);
			processes.remove(nextExecuted);
		}
		
		System.out.println("Average Response Time: " + sumResponseTime/(double)sent.size());
	}

	private static void RoundRobin(ArrayList<Process> processes, int quantum) {
		 LinkedList<Process> queue = new LinkedList<Process>();
		 LinkedList<Process> WorkingQueue = new LinkedList<Process>();
		 LinkedHashMap<String, Boolean> started = new LinkedHashMap<>();
		 LinkedHashMap<String, Integer> PrevWaiting = new LinkedHashMap<>();
		 int time = processes.get(0).arrivalTime;
		 
		 for(int i =0 ; i < processes.size() ; i++){
			 PrevWaiting.put(processes.get(i).name, processes.get(i).arrivalTime);
			 started.put(processes.get(i).name, false);
		 }
		 
		 for(int i =0 ;i < processes.size() ; i++){
			 queue.add(processes.get(i));
		 }
		 
		 while(!queue.isEmpty() || !WorkingQueue.isEmpty()){
			 while(!queue.isEmpty() && queue.peek().arrivalTime<=time){
				 WorkingQueue.addFirst(queue.poll());
			 }
			 if(WorkingQueue.isEmpty()){
				 time = queue.peek().arrivalTime;
				 continue;
			 }
			 Process p = WorkingQueue.poll();
			 System.out.println("Working on : " + p.name + " Time now is :" + time);
			 if(!started.get(p.name)){
				p.waitingTime += ((time - p.arrivalTime > 0) ? time-p.arrivalTime : 0 );
				p.responseTime = ((time - p.arrivalTime > 0) ? time-p.arrivalTime : 0 ) ;
				PrevWaiting.replace(p.name, time);
			 	started.replace(p.name, true);
			 }
			 else{
				 p.waitingTime +=  time-quantum-PrevWaiting.get(p.name);
				 PrevWaiting.replace(p.name, time);
			 }
			 System.out.println(p.name + " waited till now for : " + p.waitingTime);
			 p.burstTime-=quantum;
			 if(p.burstTime>0){
				 WorkingQueue.addLast(p);
				 System.out.println(p.name + " have left :" + p.burstTime);
				 time += quantum;
			 }
			 else{
				 System.out.println(p.name + " is done ");
				 time += quantum-(-1*(p.burstTime));
			 }
			 
			 
			 //System.out.println(p.toString());
		 }
		System.out.println("process\tresponse waiting");
		double avgRes = 0.0;
		double avgWait = 0.0;
		for(int i =0 ;i < processes.size() ; i++){
			System.out.println(processes.get(i).name + "\t" + processes.get(i).responseTime+"\t"+processes.get(i).waitingTime);
			avgRes+=processes.get(i).responseTime;
			avgWait+=processes.get(i).waitingTime;
		}
		avgRes/=processes.size();
		avgWait/=processes.size();
		System.out.println("avg respons Time :" +avgRes );
		System.out.println("avg waiting Time :" +avgWait );
	}
	

}


