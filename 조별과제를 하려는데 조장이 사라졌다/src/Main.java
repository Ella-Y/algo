import java.util.Scanner;

public class Main {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		int l= sc.nextInt();
		int d=l/5;
		int r=l%5;
		if(r==0) System.out.println(d);
		else System.out.println(d+1);

	}

}
