public class Y extends X{
	private int y;
	public void fun(){
		System.out.println("y");
	}
	public static void main(String[] args){
		Y yy=new Y();
		((X) yy).fun();
		X xx=new X();
		yy.super.fun();
	}
}