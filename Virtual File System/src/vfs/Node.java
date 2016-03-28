package vfs;

import java.util.List;

public class Node<T> {

    private T data;
    private List<Node<T>> childern ;
    private Node<T> parent;
    public Node(T data, Node<T> next) {
        this.data = data;

    }

    public T getData() { return data; }

	public List<Node<T>> getChildern() {
		return childern;
	}

	public void setChildern(List<Node<T>> childern) {
		this.childern = childern;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

}
