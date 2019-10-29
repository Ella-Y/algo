import java.util.*;

public class Main {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		
		long dp[]=new long[101];
		
		int ary[]= {0,1,1,1,2,2,3,4,5,7,9};
		
		for(int i=1;i<ary.length;i++) {
			dp[i]=ary[i];
		}
		
		for(int i=11;i<101;i++) {
			dp[i]=dp[i-5]+dp[i-1];
		}
		
		int T = sc.nextInt();
		for(int test=1;test<=T;test++) {
			System.out.println(dp[sc.nextInt()]);
		}
			

	}

}
