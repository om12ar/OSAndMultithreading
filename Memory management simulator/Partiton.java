
public class Partiton {
	private int size;
	private int index;
	private boolean allocated;
	private int sizeAllocated;

	Partiton(int s, int i, boolean b ,int sa) {
		size = s;
		index = i;
		allocated = b;
		sizeAllocated= sa;
		
	}

	Partiton(Partiton a) {
		this.size = a.size;
		this.index = a.index;
		this.allocated = a.allocated;
		this.sizeAllocated=a.sizeAllocated;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isAllocated() {
		return allocated;
	}

	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getSizeAllocated() {
		return sizeAllocated;
	}

	public void setSizeAllocated(int sizeAllocated) {
		this.sizeAllocated = sizeAllocated;
	}
}
