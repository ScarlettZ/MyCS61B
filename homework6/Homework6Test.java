/* Homework6Test.java */

import list.InvalidNodeException;
import dict.*;
import java.util.*;

/**
 *  Initializes a hash table, then stocks it with random SimpleBoards.
 **/

public class Homework6Test {

  /**
   *  Generates a random 8 x 8 SimpleBoard.
   **/

  private static SimpleBoard randomBoard() {
    SimpleBoard board = new SimpleBoard();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
				double fval = Math.random() * 12;
				int value = (int) fval;
				board.setElementAt(x, y, value);
      }
    } 
    return board;
  }

  /**
   *  Empties the given table, then inserts "numBoards" boards into the table.
   *  @param table is the hash table to be initialized.
   *  @param numBoards is the number of random boards to place in the table.
   **/

  public static void initTable(HashTableChained table, int numBoards) {
    table.makeEmpty();
    for (int i = 0; i < numBoards; i++) {
      table.insert(randomBoard(), new Integer(i));
    }
  }

  /**
   *  Creates a hash table and stores a certain number of SimpleBoards in it.
   *  The number is 100 by default, but you can specify any number at the
   *  command line.  For example:
   *
   *    java Homework6Test 12000
   **/

  public static void main(String[] args) {
    int numBoards;

    if (args.length == 0) {
      numBoards = 100;
    } else {
      numBoards = Integer.parseInt(args[0]);
    }
    HashTableChained table = new HashTableChained(numBoards);
    initTable(table, numBoards);

    // To test your hash function, add a method to your HashTableChained class
    // that counts the number of collisions--or better yet, also prints
    // a histograph of the number of entries in each bucket.  Call this method
    // from here.
    try {
		table.String();
	} catch (InvalidNodeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("n="+table.size()+"	N="+table.buckets());
	System.out.println("load factor="+(double)table.size()/table.buckets());
	double eCollision=table.expectedCollisions();
	System.out.println("The expected number of collisons is:"+eCollision);
	int aCollision=table.actualCollisions();
	System.out.println("The actual number of collisons is:"+aCollision);
  }

}
