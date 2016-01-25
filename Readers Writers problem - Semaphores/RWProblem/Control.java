package RWProblem ;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Control {

	private Semaphore a;
	private Semaphore c;
	static String data;
	static int w = 0; // number of active writers
	static int r = 0; // number of active readers

	Control() {
		a = new Semaphore(1);
		c = new Semaphore(1);
	}

	Semaphore getsA() {
		return a;
	}

	Semaphore getsC() {
		return c;
	}

	public static void main(String[] args) {
		System.out.println("Initial Buffer Content : ");
		Scanner in = new Scanner(System.in);
		data = in.nextLine();
		System.out.print("Number of reader threads : ");
		int numReaders = in.nextInt();
		System.out.print("Number of writer threads : ");
		int numWriters = in.nextInt();
		Control c = new Control();
		Thread[] readerThreads = new Thread[numReaders];
		Thread[] writerThreads = new Thread[numWriters];
		System.out.println("Reader Threads:");
		in.nextLine();
		for (int i = 0; i < numReaders; i++) {
			System.out.println(i);
			String name = in.nextLine();
			//in.nextLine();
			
			readerThreads[i] = (new Thread(new Reader(c, name)));
		}
		System.out.println("Writers Threads:");
		for (int i = 0; i < numWriters; i++) {
			String name = in.nextLine();
			String toWrite = in.nextLine();
			writerThreads[i] = (new Thread(new Writer(c, name, toWrite)));
		}
		int it = 0;
		while (it < writerThreads.length && it < readerThreads.length) {
			if (it < writerThreads.length)
				writerThreads[it].start();
			if (it < readerThreads.length)
				readerThreads[it].start();
			it++;
		}

	}

}

