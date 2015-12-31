package scheduling;

public class Process{
		String name;
		int arrivalTime;
		int burstTime ;
		public int responseTime ;
		public int waitingTime;
		
		public Process(String n , int Atime , int Btime) {
			name = n ;
			arrivalTime =Atime;
			burstTime = Btime ;
			waitingTime=0;
			int responseTime = -1 ;
		}
		
		@Override
		public String toString(){
			return (name +" : "+ arrivalTime +" "+ burstTime +" "+ waitingTime);
		}
	}
