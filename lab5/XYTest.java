public class XYTest{
	public static void main(String[] args){
		X[] xa=new X[5];
		Y[] ya=new Y[5];
		xa=ya;
		//ya=xa;//compile-time error
		ya=(Y[])xa;
		
	}
}