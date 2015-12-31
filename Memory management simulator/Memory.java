
import java.util.ArrayList;
import java.util.Scanner;

public class Memory {
	static ArrayList<Partiton> arr;

	Memory(ArrayList<Partiton> b) {
		arr = new ArrayList<Partiton>(b);
	}

	Memory() {
		arr = new ArrayList<Partiton>();
	}

	int allcoateBestfit(int space) {
		System.out.println("Best Fit\n");
		int tempindex = -1;
		int ForR = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (!arr.get(i).isAllocated() && arr.get(i).getSize() >= space) {
				if (tempindex == -1)
					tempindex = i;
				else {
					if (arr.get(tempindex).getSize() > arr.get(i).getSize()) {
						tempindex = i;
					}
				}
			}
		}

		if (tempindex == -1) {
			arr.add(new Partiton(space, 0, true, space));
			if (arr.size() > 1) {
				arr.get(arr.size() - 1).setIndex(
						arr.get(arr.size() - 2).getSize()
								+ arr.get(arr.size() - 2).getIndex());
			}
			ForR = arr.size() - 1;
		} else {
			arr.get(tempindex).setAllocated(true);
			arr.get(tempindex).setSizeAllocated(space);
			ForR = tempindex;
		}
		
		defrag(ForR);
		
		System.out.println("address\t\tAllocated\t\tIsAllocated\tsize");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getIndex() + "\t\t"
					+ arr.get(i).getSizeAllocated() + "\t\t\t"
					+ arr.get(i).isAllocated() + "\t\t" + arr.get(i).getSize());
		}
		System.out.println();
		System.out.println();
		return ForR;
	}

	int allcoateWorstfit(int space) {
		System.out.println("Worst Fit\n");
		int tempindex = -1;
		int ForR = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (!arr.get(i).isAllocated() && arr.get(i).getSize() >= space) {
				if (tempindex == -1)
					tempindex = i;
				else {
					if (arr.get(tempindex).getSize() < arr.get(i).getSize()) {
						tempindex = i;
					}
				}
			}
		}

		if (tempindex == -1) {
			arr.add(new Partiton(space, 0, true, space));
			if (arr.size() > 1) {
				arr.get(arr.size() - 1).setIndex(
						arr.get(arr.size() - 2).getSize()
								+ arr.get(arr.size() - 2).getIndex());
			}
			ForR = arr.size() - 1;
		} else {
			arr.get(tempindex).setAllocated(true);
			arr.get(tempindex).setSizeAllocated(space);
			ForR = tempindex;
		}
		defrag(ForR);
		
		System.out.println("address\t\tAllocated\t\tIsAllocated\tsize");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getIndex() + "\t\t"
					+ arr.get(i).getSizeAllocated() + "\t\t\t"
					+ arr.get(i).isAllocated() + "\t\t" + arr.get(i).getSize());
		}
		System.out.println();
		System.out.println();
		return ForR;
	}

	int allcoateFirsttfit(int space) {
		System.out.println("First Fit\n");
		int tempindex = -1;
		int ForR = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (!arr.get(i).isAllocated() && arr.get(i).getSize() >= space) {
				tempindex = i;
				break;
			}
		}

		if (tempindex == -1) {
			arr.add(new Partiton(space, 0, true, space));
			if (arr.size() > 1) {
				arr.get(arr.size() - 1).setIndex(
						arr.get(arr.size() - 2).getSize()
								+ arr.get(arr.size() - 2).getIndex());
			}
			ForR = arr.size() - 1;
		} else {

			arr.get(tempindex).setAllocated(true);
			arr.get(tempindex).setSizeAllocated(space);
			ForR = tempindex;
		}
		defrag(ForR);
		System.out.println("address\t\tAllocated\t\tIsAllocated\tsize");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getIndex() + "\t\t"
					+ arr.get(i).getSizeAllocated() + "\t\t\t"
					+ arr.get(i).isAllocated() + "\t\t" + arr.get(i).getSize());
		}
		System.out.println();
		System.out.println();
		return ForR;
	}

	int deallcoate(int sAddress) {
		System.out.println("Deallcoate\n");
		int ForR = -1;
		for (int i = 0; i < arr.size(); i++) {
			if (sAddress == arr.get(i).getIndex() && arr.get(i).isAllocated()) {
				arr.get(i).setSizeAllocated(0);
				arr.get(i).setAllocated(false);
				ForR = i;
			}
		}
		defrag(ForR);
		
		System.out.println("address\t\tAllocated\t\tIsAllocated\tsize");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getIndex() + "\t\t"
					+ arr.get(i).getSizeAllocated() + "\t\t\t"
					+ arr.get(i).isAllocated() + "\t\t" + arr.get(i).getSize());
		}
		System.out.println();
		System.out.println();
		return ForR;

	}
	void defragNonContagious(){
		
		int totalFreeSize = 0;
		boolean found = false;
		for(int i = 0; i < arr.size(); i++){
			
			if(arr.get(i).isAllocated() == false){
				
				totalFreeSize += arr.get(i).getSize();
				for(int j = i + 1; j < arr.size(); j++){
					arr.get(j).setIndex(arr.get(j).getIndex() - arr.get(i).getSize());
					
				}
				if(arr.size() > 1){
				found = true;
				arr.remove(i);
				i--;
				}
			}
			
		}
		
		if(found){
		Partiton freeSpace = new Partiton(totalFreeSize, arr.get(arr.size()-1).getIndex() + arr.get(arr.size()-1).getSize(), false, 0 );
		arr.add(freeSpace);
		}
	}
	
	void defrag(int index){
		
		if(index == -1){
			return;
		}
		// case 1:
		if(arr.get(index).isAllocated() && arr.get(index).getSize() > arr.get(index).getSizeAllocated()){


			int partitionSize = arr.get(index).getSize();
			int allocatedSize = arr.get(index).getSizeAllocated();


			int newPartitionSize = partitionSize - allocatedSize;
			partitionSize = allocatedSize;

			arr.get(index).setSize(partitionSize);

			Partiton newFreePart = new Partiton(newPartitionSize, arr.get(index).getIndex() + arr.get(index).getSize(), false, 0);
			arr.add(index + 1, newFreePart);

		}
		//case 2:
		if(index < arr.size() - 1 && index > 0){		

			if(arr.get(index).isAllocated() == false && arr.get(index + 1).isAllocated() == false){
				arr.get(index).setSize(arr.get(index).getSize()+ arr.get(index+1).getSize());
				arr.remove(index + 1);
			}

			if(arr.get(index).isAllocated() == false && arr.get(index - 1).isAllocated() == false){
				arr.get(index - 1).setSize(arr.get(index - 1).getSize() + arr.get(index).getSize());
				arr.remove(index);
			}
		}
		
		
	}

	public static void main(String[] args) {
		Memory mem = new Memory();
		
//		mem.allcoateBestfit(10);
//		mem.allcoateBestfit(12);
//		mem.allcoateBestfit(20);
//		mem.deallcoate(11);
//		mem.allcoateWorstfit(10);
//		mem.allcoateFirsttfit(12);
//		mem.deallcoate(0);
//		mem.allcoateFirsttfit(1);

		
//		mem.allcoateFirsttfit(10);
//		mem.allcoateFirsttfit(10);
//		mem.allcoateFirsttfit(10);
//		mem.deallcoate(10);
//		mem.allcoateFirsttfit(5);	
//		mem.allcoateFirsttfit(10);
//		mem.deallcoate(20);

		Scanner input = new Scanner(System.in);
		int choice;
		System.out.println("1.First Fit\n2.Best Fit\n3.Worst Fit");
		int allocationType = input.nextInt();
		
	do{
		System.out.println("1.Allocate\n2.Deallocate\n3.Defrag All\n4.Exit");
		choice = input.nextInt();
		
		if(choice == 1){
			System.out.println("Enter Space to be Allocated:");
			int space = input.nextInt();
			
			if(allocationType == 1){
				mem.allcoateFirsttfit(space);
			}
			if(allocationType == 2){
				mem.allcoateBestfit(space);
			}
			if(allocationType == 3){
				mem.allcoateWorstfit(space);
			}
				
		}
		
		if(choice == 2){
			System.out.println("Enter Address to be Deallocated:");
			int address = input.nextInt();
			mem.deallcoate(address);
		}
		if(choice == 3){
			mem.defragNonContagious();
			System.out.println("Memory Defraged All");
		}
		
	
}while(choice!=4);
	
		
		System.out.println("Address\tSize\tIs Allocated\tAllocated Size");
		for(int i = 0; i < arr.size(); i++){
			System.out.println(arr.get(i).getIndex() + "\t\t" + arr.get(i).getSize() + "\t" + arr.get(i).isAllocated() +"\t\t" + arr.get(i).getSizeAllocated());
		}
		
	}
}
