import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 정올. TSP 1681. 해밀턴 순환회로
 */

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static final int INF = 100000000;
	static int info[][];
	static int dp[][];
	static int N;
	static final int START = 0;
	public static void main(String[] args) throws IOException {
		
		N=Integer.parseInt(br.readLine());
		
		info=new int [N][N];
		dp=new int [N][1<<N];
		
		StringTokenizer stk=null;
		
		for(int i=0;i<N;i++) {
			stk=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				info[i][j]=Integer.parseInt(stk.nextToken());
				if(info[i][j]==0) info[i][j]=INF;
				if(i==j) info[i][j]=0;
			}
		}
		
		for(int i=0;i<N;i++) {
			Arrays.fill(dp[i], -1);
		}
		
		
		System.out.println(solve(START,1<<START));
		

	}
	
	private static int solve(int cur, int B) {
		
		if(dp[cur][B]!=-1) return dp[cur][B];
		
		if(B == (1<<N) -1) {
			return dp[cur][B]=info[cur][START]!=INF?info[cur][START]:INF;
		}
		
		dp[cur][B] = INF;
		
		for(int i=0;i<N;i++) {
			if(info[cur][i]==INF || (B&(1<<i)) >0) continue;
			
			dp[cur][B] = Math.min(dp[cur][B], solve(i,B|(1<<i)) + info[cur][i]);
			
		}
		
		return dp[cur][B];
	}

}
