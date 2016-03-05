/* HashTableChained.java */

package dict;

import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  protected final int INIT_BUCKETS = 103;
  protected final int PRIME = 109345121;
	protected int bucketNum,entryNum;
	protected List[] hashTable;
	protected long scale,shift;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    entryNum=0;
    bucketNum=findBuckets(sizeEstimate);
    hashTable=new DList[bucketNum];
    for(int i=0;i<bucketNum;i++)
    	hashTable[i]=new DList();
    Random rand=new Random();
    scale=rand.nextInt(PRIME-1)+1;
    shift=rand.nextInt(PRIME);
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    entryNum=0;
    bucketNum=97;
    hashTable=new DList[bucketNum]; 
    for(int i=0;i<bucketNum;i++)
    	hashTable[i]=new DList();
    scale=rand.nextInt(PRIME-1)+1;
    shift=rand.nextInt(PRIME);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
	  int newCode=((scale*code+shift)%PRIME)%size;
	  //int newCode=code%bucketNum;
	  if(newCode<0)
		  newCode+=bucketNum;
    return newCode;//公式是readme的，但是数字不确定，看课本吧+还要测试另一个公式
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return entryNum;
  }
  
	/**
  *		Returns the number of buckets in this hash table.  
  */
  public int buckets() {
	   // Replace the following line with your solution.
	   return bucketNum;
  }
  
  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    if(entryNum==0)
    	return true;
    else
		return false;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry e=new Entry();
    e.key=key;
    e.value=value;
    int bucket=compFunction(key.hashCode());
//    if(hashTable[bucket]==null)
//    	hashTable[bucket]=new DList();
    hashTable[bucket].insertBack(e);
    entryNum++;
    return e;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int bucket=compFunction(key.hashCode());
    ListNode node=hashTable[bucket].front();
    try{
	    while(node.isValidNode()){
	    	if(((Entry)node.item()).key.equals(key))//equals方法？？
	    		return (Entry)node.item();
	    	else 
	    		node=node.next();
	    }
	  }catch(InvalidNodeException e){
	  	e.printStackTrace();
	  }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int bucket=compFunction(key.hashCode());
    ListNode node=hashTable[bucket].front();
    try{
	    while(node.isValidNode()){
	    	if(((Entry)node.item()).key.equals(key)){//equals方法？？
	    		Entry en=(Entry) node.item();
	    		node.remove();
	    		entryNum--;
	    		return en;
	    	}else 
	    		node=node.next();
	    }
	  }catch(InvalidNodeException e){
	  	e.printStackTrace();
	  }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    entryNum = 0;
    for(int i=0;i<bucketNum;i++)
    	hashTable[i]=new DList();
  }
  
  /**
  *		Self Define: Find the median of prime numbers from nearPrime/2 to nearPrime.
  */
  public static int findPrime(int nearPrime){
  	boolean prime[]=new boolean[nearPrime+1];
  	for(int i=2;i<=nearPrime;i++)
  		prime[i]=true;
  	for(int divisor=2;divisor*divisor<=nearPrime;divisor++){
  		if(prime[divisor]){
  			for(int i=2*divisor;i<nearPrime;i=i+divisor)
  				prime[i]=false;
  		}
  	}
  	//count the number of primes
  	int count=0;
  	for(int i=nearPrime/2;i<=nearPrime;i++)
  		if(prime[i])
  			count++;
  	//find median
  	count/=2;
  	for(int i=nearPrime/2;i<=nearPrime;i++){
  		if(prime[i]){
  			count--;
  			if(count==0)
  				return i;
  		}
  	} 	
  	return 0;
  }
  
  /**
  *		Self Define: Find a prime bucket number with "sizeEstimate".
  *		The loading factor should be between 0.5 and 1. 
  */
  public static int findBuckets(int sizeEstimate){
  	int size1=(int) (sizeEstimate/0.5);
  	return findPrime(size1);
  }
  
  /**
  *		Self Define: Get the expected collision number of good hash algorithm 
  *		using a given formula.
  */
  public double expectedCollisions() {
		double collision=1;
		for(int i=0;i<entryNum;i++){
			collision*=(1-(double)1/bucketNum);
		}
		collision*=bucketNum;
		collision=entryNum-bucketNum+collision;
		return collision;
  }
  
  /**
  *		Self Define: Get the actual collision number of this hash table.
  */
  public int actualCollisions() {
		int collision=0;
		for(int i=0;i<bucketNum;i++){
			if(hashTable[i].length()>1)
				collision++;
		}
		return collision;
  }
  
  /**
  *		Self Define: Print this hash table as a String.
  */
  public void String() throws InvalidNodeException{
		for(int i=0;i<bucketNum;i++){
			int num=hashTable[i].length();
			System.out.print("["+num+"]");
			if((i+1)%20==0)
				System.out.println();
	//		ListNode node=hashTable[i].front();
	//		while(node.isValidNode()){
	//			System.out.print("("+((Entry)node.item()).key+","+((Entry)node.item()).value+")");
	//			node=node.next();
	//		}
		}
		System.out.println();
  }
  
 
  
  public static void main(String[] args){
	  HashTableChained table=new HashTableChained(8);
	  
	  System.out.println("Testing size(), isEmpty()……");
	    System.out.println("table's size is: " + table.size());
	    System.out.println("table is Empty: " + table.isEmpty());
	    
	    System.out.println();
	    System.out.println("Testing insert()……");
	    table.insert("WTF", "WTF");
	  	table.insert("CAT", "MEOW");
	  	table.insert("DOG", "BARK");
	  	table.insert("HUMAN", "WHYYY");
	  	table.insert("ww12", 1);
	  	table.insert("88812", 54);
	  	System.out.println("n="+table.size()+"	N="+table.buckets());
	  	System.out.println("load factor="+(double)table.size()/table.buckets());
		double eCollision=table.expectedCollisions();
		System.out.println("The expected number of collisons is:"+eCollision);
		int aCollision=table.actualCollisions();
		System.out.println("The actual number of collisons is:"+aCollision);
//	    System.out.println("table's size is: " + table.size());
	    System.out.println("table is Empty: " + table.isEmpty());
	    try {
			table.String();
		} catch (InvalidNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
	    System.out.println("Testing find(), remove()……");	
	  	Entry a = table.find("WTF");
	    if(a != null)
	            System.out.println("The item found is: [ " + a.key.toString() +","+a.value.toString()+ " ]");
	    else
	            System.out.println("The is no such item in the table to be found.");
	  	Entry b = table.remove("DOG");
	    if(b != null)
	            System.out.println("The item deleted is: [ " + b.key.toString() +","+b.value.toString()+ " ]");
	    else
	            System.out.println("The is no such item in the table to be deleted.");
	    try {
			table.String();
		} catch (InvalidNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	System.out.println("Should be 5: "+table.size());
	    
	  	System.out.println();
	    System.out.println("Testing makeEmpty()……");
	    table.makeEmpty();
	    try {
			table.String();
		} catch (InvalidNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
  }
}
