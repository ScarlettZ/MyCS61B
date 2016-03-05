package list;

/**
 *  A LockDList is a "lockable" doubly-linked list ADT, a list in 
 *  which any node can be "locked." Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 *
 *  Your LockDList class should override just enough methods to ensure that
 *  (1) LockDListNodes are always used in LockDLists (instead of DListNodes), and
 *  (2) locked nodes cannot be removed from a list.
 *
 *  父类用newNode方法构建新节点，目的是给子类提供向上类型转换的机会。
 *  子类重写newNode方法构造并返回dynamic type=LockDListNode而static type=DListNode的节点，可以使父类中绝大多数方法不需要重写
 *  子类只需要重写改变或使用isLocked域的方法。
 *  remove()和lockNode()函数：形参是DListNode而实参是LockDListNode, 所以isLocked这个域只是我们看不到而已，使用cast之后便能访问
 *  everywhere you expect a father (DListNode), you can throw it a son (LockDList)，再填加一些必要的转换就行了
 */
 
public class LockDList extends DList{
	
	//permanently locks "node"
	public void lockNode(DListNode node){
		((LockDListNode) node).isLocked=true;
	}
	
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item,prev,next);
		//返回dynamic type=LockDListNode而static type=DListNode的节点，从而把子类节点本身的isLocked域隐藏掉。
		//对于不改变或使用isLocked域的方法根本无需重写，因为他们的行为对isLocked无影响，被当作DListNode就可以实现它们的功能
		//对于需要使用isLocked域的方法必须在子类中重写。
  }
  
  //locked nodes cannot be removed from a list.
  public void remove(DListNode node) {
    // Your solution here.
    if(((LockDListNode)node).isLocked!=true){
			super.remove(node);
    }
  }
  
}
