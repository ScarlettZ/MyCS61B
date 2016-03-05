package list;

/**
 *  A LockDListNode is a node in a LockDList ("lockable" doubly-linked list).
 */

public class LockDListNode extends DListNode{
	protected boolean isLocked;
	//prev,next域继承自DListNode类，全部是DListNode类型，
	
	  /**
   *  LockDListNode() constructor.
   *  @param i the item to store in the node.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  LockDListNode(Object i, DListNode p, DListNode n) {//包访问权限
		super(i,p,n);
		isLocked=false;
  }
}