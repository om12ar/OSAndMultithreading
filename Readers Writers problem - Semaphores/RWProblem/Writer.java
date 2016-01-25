package RWProblem;

public class Writer implements Runnable {

	String writerName;
	Control c;
	String toWrite;

	Writer(Control a, String n, String s) {
		c = a;
		writerName = n;
		toWrite = s;
	}

	@Override
	public void run() {
		System.out.println(writerName + " Start Writing ");
		if (c.r > 0) {
			System.out.println(writerName + " Blocked");
			try {
				c.getsC().acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				c.getsA().acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.w++;
			c.data += toWrite;
			System.out.println(writerName + " wrote :" + c.data);
			c.w--;
			System.out.println(writerName + " Finished Writing ");
			c.getsA().release();
			c.getsC().release();
		} else {
			try {
				c.getsA().acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.w++;
			c.data += toWrite;
			System.out.println(writerName + " wrote :" + c.data);
			c.w--;
			System.out.println(writerName + " Finished Writing ");
			c.getsA().release();
		}
	}

}
