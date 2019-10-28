import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException{
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
				
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
						
		int dis[][]=new int [N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				dis[i][j]=INF;
				if(i==j)dis[i][j]=0;
			}
		}
		
		int a,b;
		for(int i=0;i<M;i++) {
			stk = new StringTokenizer(br.readLine());
			a=Integer.parseInt(stk.nextToken())-1;
			b=Integer.parseInt(stk.nextToken())-1;
			dis[a][b]=1;
		}
		
		floyd(dis);
		
		
		int answer = 0;
		int cnt;
		
		for(int i=0;i<N;i++) {
			cnt=N;
			for(int j=0;j<N;j++) {
				if(dis[i][j]!=INF) cnt--;
				else if(dis[i][j]==INF && dis[j][i]!=INF) {
					dis[i][j]=dis[j][i];
					cnt--;
				}
			}
			if(cnt==0) ++answer;
		}
		
		//print(dis);
		System.out.println(answer);
		
	}
	
	public static void floyd(int [][]dis){
		int N=dis.length;
		
		for(int mid=0;mid<N;mid++) {
			for(int a=0;a<N;a++) {
				for(int b=0;b<N;b++) {
					
					if(dis[a][mid]==INF || dis[mid][b]==INF) continue;
					
					if(dis[a][b] > dis[a][mid]+dis[mid][b]) {
						dis[a][b] = dis[a][mid]+dis[mid][b];
					}
					
				}
			}
		}
		
	}
	
	public static void print(int dis[][]) {
		for(int i=0;i<dis.length;i++) {
			for(int j=0;j<dis.length;j++) {
				if(dis[i][j] == INF) {
					System.out.print("I ");
				}else
				System.out.print(dis[i][j]+" ");
			}
			System.out.println();
		}
	}

}
