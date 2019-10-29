import java.util.Scanner;

public class Main {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args){
		char [] st1=sc.next().toCharArray();
		char [] st2=sc.next().toCharArray();
		
		int [][] dp=new int[st1.length+1][st2.length+1];
		
		
		for(int i=1;i<dp.length;i++) {
			for(int j=1;j<dp[i].length;j++) {
				//만약 같은 문자가 나오면, 현재 비교문자를 포함하지 않았던 LCS 값에서 +1을 해준다.
				if(st1[i-1]==st2[j-1]) {
					dp[i][j]=dp[i-1][j-1]+1;
				}
				//만약 서로 다른 문자라면,
				//현재 비교문자를 포함하지 않았던 LCS 값과, 현재 비교문자를 포함한 LCS값 중 길이가 긴 값을 가져온다.
				else {
					
					
					dp[i][j] = dp[i-1][j]> dp[i][j-1] ? dp[i-1][j] : dp[i][j-1];	
				}
				
			}
		}
		System.out.println(dp[st1.length][st2.length]);
		
		
		
		
		
	}

}
