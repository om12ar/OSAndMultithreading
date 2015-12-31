package os;

import java.util.ArrayList;

public class Mult implements Runnable {

	ArrayList<Double> row;
	ArrayList<Double> col;
	int ii;
	int jj;
	ArrayList<ArrayList<Double>> result;

	public Mult(ArrayList<Double> r, ArrayList<Double> c, int i, int j, ArrayList<ArrayList<Double>> res) {
		row = r;
		col = c;
		ii = i;
		jj = j;
		result = res;
	}

	@Override
	public void run() {
		Double elem = 0.0;
		for (int i = 0; i < row.size(); i++) {
			elem += row.get(i) * col.get(i);
		}
		putElem(elem);
	}

	public void putElem(Double elem) {
		//System.out.println("Mult.putElem() :" + elem);
		result.get(ii).set(jj, elem);
	}

}
