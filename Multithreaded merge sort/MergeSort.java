package os ;

import java.util.Scanner;

public class MergeSort implements Runnable {
	int[] list;
	int depth;
	final int NUM_OF_THREADS = 2;
	MergeSort(int[] l, int d) {
		list = l;
		depth = d;
	}

	public void mergeSort(int[] list, int depth) {
		if (list.length > 1) {
			if (depth > NUM_OF_THREADS) {
				int[] firstHalf = new int[list.length / 2];
				System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
				mergeSort(firstHalf, depth + 1);
				int secondHalfLength = list.length - list.length / 2;
				int[] secondHalf = new int[secondHalfLength];
				System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
				mergeSort(secondHalf, depth + 1);
				merge(firstHalf, secondHalf, list);
				return;
			}
			// Merge sort the first half
			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			MergeSort a = new MergeSort(firstHalf, depth + 1);
			Thread t1 = new Thread(a);
			t1.start();
			// Merge sort the second half
			int secondHalfLength = list.length - list.length / 2;
			int[] secondHalf = new int[secondHalfLength];
			System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
			MergeSort b = new MergeSort(secondHalf, depth + 1);
			Thread t2 = new Thread(b);
			t2.start();

			while (t2.isAlive()) {

			}
			while (t1.isAlive()) {

			}
			// Merge firstHalf with secondHalf into list
			merge(firstHalf, secondHalf, list);
		}
	}

	/** Merge two sorted lists */
	public void merge(int[] list1, int[] list2, int[] temp) {
		int current1 = 0; // Current index in list1
		int current2 = 0; // Current index in list2
		int current3 = 0; // Current index in temp

		while (current1 < list1.length && current2 < list2.length) {
			if (list1[current1] < list2[current2])
				temp[current3++] = list1[current1++];
			else
				temp[current3++] = list2[current2++];
		}

		while (current1 < list1.length)
			temp[current3++] = list1[current1++];

		while (current2 < list2.length)
			temp[current3++] = list2[current2++];
	}
	/** A test method */
	public static void main(String[] args) {
		int n; // size of array
		Scanner in = new Scanner(System.in);
		// Fill array with numbers ;
		int[] arr = new int[1000000];
		for (int i = 0; i < 1000000; i++) {
			arr[i] = arr.length - i;
		}
		
		long startTime = System.currentTimeMillis();
		MergeSort test = new MergeSort(arr , 0);
		Thread a1 = new Thread(test);
		a1.start();
		while (a1.isAlive()) {

		}
		long endTime = System.currentTimeMillis();
		System.out.println("\nParallel time with " + Runtime.getRuntime().availableProcessors() + " processors is "
				+ (endTime - startTime) + " milliseconds");
		/*
		for (int i = 0; i < 1000000; i++) {
			System.out.println(arr[i]);
		}
		*/
	}

	@Override
	public void run() {
		
		mergeSort(list, depth);

	}
}
