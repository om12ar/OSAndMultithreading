package os;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> result;
		Scanner in = new Scanner(System.in);
		int row1 = 0;
		int col1 = 0;
		int row2 = 0;
		int col2 = 0;
		ArrayList<ArrayList<Double>> mat1 = null;
		ArrayList<ArrayList<Double>> mat2 = null;
		boolean canMult = false;
		while (!canMult) {
			System.out.println("Enter number of row for mat#1");
			row1 = in.nextInt();
			System.out.println("Enter number of cols for mat#1");
			col1 = in.nextInt();
			mat1 = new ArrayList<ArrayList<Double>>(row1);
			System.out.println("Enter mat#1");
			for (int i = 0; i < row1; i++) {
				ArrayList<Double> temp = new ArrayList<>();
				for (int j = 0; j < col1; j++) {
					temp.add(in.nextDouble());
				}
				mat1.add(temp);
			}
			System.out.println("Enter number of row for mat#2");
			row2 = in.nextInt();
			System.out.println("Enter number of row for mat#2");
			col2 = in.nextInt();
			if (row1 != col2 || col2 != row1) {
				System.out.println("Can't multiply matrices of those dimensions .. try again");
			} else {
				canMult = true;
			}
		}
		mat2 = new ArrayList<ArrayList<Double>>(row2);
		System.out.println("Enter mat#2");
		for (int i = 0; i < row2; i++) {
			ArrayList<Double> temp = new ArrayList<>();
			for (int j = 0; j < col2; j++) {
				temp.add(in.nextDouble());
			}
			mat2.add(temp);
		}

		result = new ArrayList<>();
		for (int i = 0; i < row1; i++) {
			result.add(new ArrayList<Double>());
			for (int j = 0; j < col2; j++) {
				result.get(i).add(0.0);
			}
		}

		ArrayList<Thread> multiTread = new ArrayList<>();

		for (int i = 0; i < row1; i++) {
			for (int j = 0; j < col2; j++) {
				ArrayList<Double> temp = new ArrayList<Double>();
				for (int k = 0; k < row2; k++) {
					temp.add(mat2.get(k).get(j));
				}

				multiTread.add(new Thread(new Mult(mat1.get(i), temp, i, j, result)));
				multiTread.get(multiTread.size() - 1).run();
			}

		}

		for (int i = 0; i < multiTread.size(); i++) {
			while (multiTread.get(i).isAlive())
				;
		}
		for (int i = 0; i < row1; i++) {
			for (int j = 0; j < col2; j++) {
				System.out.print(result.get(i).get(j) + " ");
			}
			System.out.println();
		}

	}

}
