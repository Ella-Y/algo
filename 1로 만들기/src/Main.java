import java.io.*;
import java.util.*;
public class Main {
	static Scanner sc=new Scanner(System.in);
	static int d[]=new int[1000000+1];
	
	public static void main(String[] args) {

		int X= sc.nextInt();
		d[1]=0;
		for(int i=2;i<=X;i++) {
			d[i] = d[i-1]+1;
			if(i%2==0)
				d[i] = Math.min(d[i], d[i/2]+1);
			if(i%3==0)
				d[i] = Math.min(d[i], d[i/3]+1);
		}
		System.out.println(d[X]);
	}
	
	

}
