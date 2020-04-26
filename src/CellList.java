// ----------------------------------------------
// COMP 249
// Assignment 4
// Written by: Xin Yuan Zhang (26373127)
// ----------------------------------------------
import java.util.NoSuchElementException;

public class CellList {
	/**
	 * inner CellNode class
	 * @author Jason Zhang
	 *
	 */
	private class CellNode {
		
		private CellPhone cell;
		private CellNode link;
		/**
		 * default constructor
		 */
		public CellNode() {
			cell = null;
			link = null;
		}
		/**
		 * parameterized constructor
		 * @param cell	for CellPhone
		 * @param cellNode	CellNode
		 */
		public CellNode(CellPhone cell, CellNode cellNode) {
			// this method could cause a privacy leak
			// any change to cell object, will reflect on this CellNode
			this.cell = cell;
			this.link = cellNode;
		}
		/**
		 * copy constructor
		 * @param cellNode	for copied CellNode
		 */
		public CellNode(CellNode cellNode) {
			this.cell = cellNode.cell.clone();
			this.link = cellNode.link;
		}		
		@Override
		public CellNode clone() {
			return new CellNode(this.cell.clone(), this.link);		
		}
	}
	
	private CellNode head;
	private int size;
	/**
	 * default constructor
	 */
	public CellList() {
		head = null;
		size = 0;
	}
	/**
	 * copy constructor
	 * @param cl for copied CellList
	 */
	public CellList(CellList cl) {
		if (cl.head == null) {
			head = null;
		} else {
			head = null;
			CellNode cn1, cn2;
			cn1 = cl.head;	// reference to go through copied list
			cn2 = null;		// reference to construct this list
			while(cn1 != null) {
				if (head == null) {
					cn2 = new CellNode(cn1.cell, null);
					head = cn2;
					++size;
				} else {
					cn2.link = new CellNode(cn1.cell, cn1.link);
					cn2 = cn2.link;
					++size;
				}
				cn1 = cn1.link;
			}
		}		
	}
	
	public void addToStart(CellPhone cell) {
		if (!this.contains(cell.getSerialNum())) {
			head = new CellNode(cell, head);
			++size;
		}
	}
	
	public void insertAtIndex(CellPhone cell, int index) {
		// this method could cause a privacy leak
		// any change to cell object, will reflect on this CellList
		if (index < 0 || index > size - 1) {
			try {
				throw new NoSuchElementException("Index invalid!");
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage() + "Program will exit!");
				System.exit(0);
			}
		} if (head == null) {
			return;
		} else if (index == 0) {
			this.addToStart(cell);
		} else if (!this.contains(cell.getSerialNum())){
			CellNode cellNode = new CellNode(cell, null);
			CellNode cn = head;
			for (int i = 0; i < index - 1; ++i) {
				cn = cn.link;
			}
			cellNode.link = cn.link;
			cn.link = cellNode;
			++size;
		}
	}
	
	public void deleteFromIndex(int index) {
		if (index < 0 || index > size - 1) {
			try {
				throw new NoSuchElementException("Index invalid!");
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage() + "Program will exit!");
				System.exit(0);
			}
		}
		if (head == null) {
			return;
		} else if (index == 0) {
			this.deleteFromStart();
		} else {
			CellNode cn = head;
			for (int i = 0; i < index - 1; ++i) {
				cn = cn.link;
			}
			cn.link = cn.link.link;
			--size;
		}
	}
	
	public void deleteFromStart() {
		if (head == null) {
			return;
		}
		head = head.link;
		--size;
	}
	
	public void replaceAtIndex(CellPhone cell, int index) {
		// this method could cause a privacy leak
		// any change to cell object, will reflect on this CellList
		if (index < 0 || index > size - 1) {
			return;
		}
		if (head == null) {
			return;
		} else {
			CellNode cn = head;
			for (int i = 0; i < index; ++i) {
				cn = cn.link;
			}
			cn.cell = cell;
		}
	}
	
	public CellNode find(long serialNum) {
		CellNode cellNode = head;
		int count = 0;
		while (cellNode != null) {
			++count;
			if (cellNode.cell.getSerialNum() == serialNum) {
				System.out.println("It takes " + count + " iterations to find the cellphone");
				return cellNode;
			}
			cellNode = cellNode.link;
		}
		System.out.println("It takes " + count + " iterations, but the cellphone couldn't be found.");
		return null;
	}
	
	public boolean contains(long serialNum) {
		CellNode cellNode = head;
		while (cellNode != null) {
			if (cellNode.cell.getSerialNum() == serialNum) {
				return true;
			}
			cellNode = cellNode.link;
		}
		return false;
	}
	
	public void showContents() {
		System.out.println("\nThe current size of the list is " + size +
				". Here are the contents of the list");
		System.out.println("=====================================================================");
		CellNode cellNode = head;
		int count = 0;
		while (cellNode != null) {
			System.out.print(cellNode.cell.toString() + " ---> ");
			cellNode = cellNode.link;
			++count;
			if (count % 3 == 0 && cellNode == null) {
				System.out.println("X\n");
			} else {
				if (count % 3 == 0) {
					System.out.println();
				}
				if (cellNode == null) {
					System.out.print("X\n");
				}
			}
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj.getClass() != getClass()) {
			return false;
		} else {
			CellList cl = (CellList)obj;
			if (cl.size != size) {
				return false;
			} else {
				CellNode cn = head;
				CellNode cn1 = cl.head;
				for (int i = 0; i < size; ++i) {
					if (!cn.cell.equals(cn1.cell)) {
						return false;
					}
					cn = cn.link;
					cn1 = cn1.link;
				}
			}
		}
		return true;
	}
	
}
