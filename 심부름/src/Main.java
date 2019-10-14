import java.util.Scanner;

public class Main {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		int sec=0;
		for(int i=0;i<4;i++) {
			sec +=sc.nextInt();
		}
		System.out.println(sec/60+"\n"+sec%60);
		
	}

}
