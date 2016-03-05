package list;

/**
 *  A LockDListNode is a node in a LockDList ("lockable" doubly-linked list).
 */

public class LockDListNode extends DListNode{
	protected boolean isLocked;
	//prev,next��̳���DListNode�࣬ȫ����DListNode���ͣ�
	
	  /**
   *  LockDListNode() constructor.
   *  @param i the item to store in the node.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  LockDListNode(Object i, DListNode p, DListNode n) {//������Ȩ��
		super(i,p,n);
		isLocked=false;
  }
}