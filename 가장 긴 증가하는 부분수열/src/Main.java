import java.util.Scanner;

public class Main {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		int N =sc.nextInt();
		int ary[]=new int[N];
		int memo[]=new int [N];
		
		String in[]=sc.next().split(" ");
		for(int i=0;i<N;i++) {
			ary[i]=Integer.parseInt(in[i]);
		}
		
		memo[0]=1;
		for(int i=1;i<N;i++) {
			
		}
		
		System.out.println(memo[N-1]);
		
	}
	
	

}

















