import java.util.*;

public class AVL {
	
	Node root;
	int size;

	public AVL() {
		this.root = null;
		this.size = 0;
	}

	public void insert(int id, int pts, int penalty) {
		this.size += 1;
		this.root = this.insert(this.root, id, pts, penalty);
	}

	public Node insert(Node n, int id, int pts, int penalty) {

		if (n == null) {
			return new Node(id, pts, penalty);
		}

		if (this.compare(n, id, pts, penalty) < 0) {
			n.right = this.insert(n.right, id, pts, penalty);
		}

		else if (this.compare(n, id, pts, penalty) > 0) {
			n.left = this.insert(n.left, id, pts, penalty);
		} 

		else {
			return n;
		}

		changeHeight(n);
		return rotate(n);
	}

	public void remove(int id, int pts, int penalty) {
		this.size -= 1;
		this.root = this.remove(this.root, id, pts, penalty);
	}

	public Node remove(Node n, int id, int pts, int penalty) {

		if (n == null) {
			return null;
		}

		if (this.compare(n, id, pts, penalty) < 0) {
			n.right = this.remove(n.right, id, pts, penalty);
		}

		else if (this.compare(n, id, pts, penalty) > 0) {
			n.left = this.remove(n.left, id, pts, penalty);
		}

		else {
		
			if (n.right == null) {
				return n.left;
			} 

			else if (n.left == null) {
				return n.right;
			}

			else {
				Node max = this.max(n.left);
				n.id = max.id;
				n.pts = max.pts;
				n.penalty = max.penalty;
				n.left = this.remove(n.left, n.id, n.pts, n.penalty);
			}
		}

		changeHeight(n);
		return rotate(n);
	}

	public Node max(Node n) {
		return n.right != null ? max(n.right) : n;
	}

	public void changeHeight(Node n) {
		n.size = this.findSize(n.left) + this.findSize(n.right) + 1;
		n.height = Math.max(this.findHeight(n.left), this.findHeight(n.right)) + 1;
	}

	public int findHeight(Node n) {
		return n != null ? n.height : 0;
	}

	public int findSize(Node n) {
		return n != null ? n.size : 0;
	}

	public int balanceFactor(Node n) {
		return n != null ? this.findHeight(n.left) - this.findHeight(n.right) : 0;
	}

	public Node rotate(Node n) {
		
		int bF = this.balanceFactor(n);
		
		if (bF > 1) {
			if (this.balanceFactor(n.left) < 0) {
				n.left = left(n.left);
			}
			return right(n);
		}

		else if (bF < -1) {
			if (this.balanceFactor(n.right) > 0) {
				n.right = right(n.right);
			}
			return left(n);
		}

		return n;
	}

	public Node right(Node n) {
		Node p = n.left;
		n.left = p.right;
		p.right = n;
		changeHeight(n);
		changeHeight(p);
		return p;
	}

	public Node left(Node n) {
		Node p = n.right;
		n.right = p.left;
		p.left = n;
		changeHeight(n);
		changeHeight(p);
		return p;
	}

	public void inorder(Node n) {
		if (n != null) {
			inorder(n.left);
			System.out.print(n.size + " ");
			inorder(n.right);
		}
	}

	public int rank(int pts, int penalty) {
		if (pts == 0 && penalty == 0) {
			return this.size + 1;
		}

		else {
			return rank(this.root, 1, pts, penalty)+1;
		}
	}

	public int rank(Node n, int id, int pts, int penalty) {

		if (n == null) return 0;

		if (this.compare(n, id, pts, penalty) > 0) {
			return rank(n.left, id, pts, penalty);
		}

		else if (this.compare(n, id, pts, penalty) < 0) {
			return 1 + this.findSize(n.left) + rank(n.right, id, pts, penalty);
		}

		else {
			return this.findSize(n.left);
		}
	}

	public int compare(Node n, int id, int pts, int penalty) {
		if (n.pts != pts) {
			return pts - n.pts;
		}
		else if (n.penalty != penalty) {
			return n.penalty - penalty;
		}
		else {
			return n.id - id;
		}
	}

	static class Node {
		
		int id;
		int pts;
		int penalty;
		Node left;
		Node right;
		int height;
		int size;

		public Node(int id, int pts, int penalty) {
			this.id = id;
			this.pts = pts;
			this.penalty = penalty;
			this.left = null;
			this.right = null;
			this.height = 1;
			this.size = 1;
		}
	}
}