package vfs;
import java.util.List;
import java.util.HashMap;

public class Tree<T> {
    private Node<T> rootNode;    
    private List<Node<T>> children;

  
	public Node<T> getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node<T> rootNode) {
		this.rootNode = rootNode;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}

}