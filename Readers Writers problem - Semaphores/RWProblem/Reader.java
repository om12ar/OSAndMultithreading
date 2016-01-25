package RWProblem ;
public class Reader implements Runnable {

	String readerName;
	Control c;

	Reader(Control a, String n) {
		c = a;
		readerName = n;
	}

	public void run() {
		System.out.println(readerName + " Start Reading ");
		if (c.w > 0) {
			System.out.println(readerName + " Blocked");
			try {
				c.getsA().acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				c.getsC().acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			c.r++;
			System.out.println(readerName + " Read :" + Control.data);
			System.out.println(readerName + " Finished reading ");
			c.r--;
			c.getsC().release();
			c.getsA().release();
		} else {
			try {
				c.getsC().acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.r++;
			System.out.println(readerName + " Read :" + Control.data);
			System.out.println(readerName + " Finished reading ");
			c.r--;
			c.getsC().release();
		}
	}

}

