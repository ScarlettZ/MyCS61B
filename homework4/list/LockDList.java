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
 *  ������newNode���������½ڵ㣬Ŀ���Ǹ������ṩ��������ת���Ļ��ᡣ
 *  ������дnewNode�������첢����dynamic type=LockDListNode��static type=DListNode�Ľڵ㣬����ʹ�����о��������������Ҫ��д
 *  ����ֻ��Ҫ��д�ı��ʹ��isLocked��ķ�����
 *  remove()��lockNode()�������β���DListNode��ʵ����LockDListNode, ����isLocked�����ֻ�����ǿ��������ѣ�ʹ��cast֮����ܷ���
 *  everywhere you expect a father (DListNode), you can throw it a son (LockDList)�������һЩ��Ҫ��ת��������
 */
 
public class LockDList extends DList{
	
	//permanently locks "node"
	public void lockNode(DListNode node){
		((LockDListNode) node).isLocked=true;
	}
	
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item,prev,next);
		//����dynamic type=LockDListNode��static type=DListNode�Ľڵ㣬�Ӷ�������ڵ㱾���isLocked�����ص���
		//���ڲ��ı��ʹ��isLocked��ķ�������������д����Ϊ���ǵ���Ϊ��isLocked��Ӱ�죬������DListNode�Ϳ���ʵ�����ǵĹ���
		//������Ҫʹ��isLocked��ķ�����������������д��
  }
  
  //locked nodes cannot be removed from a list.
  public void remove(DListNode node) {
    // Your solution here.
    if(((LockDListNode)node).isLocked!=true){
			super.remove(node);
    }
  }
  
}
