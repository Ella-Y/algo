import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException{
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		ArrayList<Integer> ary1[];
		ArrayList<Integer> ary2[];
		
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		ary1 = new ArrayList[N];
		ary2 = new ArrayList[N];
		
		for(int i=0;i<N;i++) {
			ary1[i] = new ArrayList<>();
			ary2[i] = new ArrayList<>();
		}
		
		int a,b;
		for(int i=0;i<M;i++) {
			stk = new StringTokenizer(br.readLine());
			a=Integer.parseInt(stk.nextToken())-1;
			b=Integer.parseInt(stk.nextToken())-1;
			ary1[a].add(b);
			ary2[b].add(a);
		}
		
		int dis1[][]=floyd(ary1);
		int dis2[][]=floyd(ary2);
		
		print(dis1);
		System.out.println("=======");
		print(dis2);
		
		
		int answer=0;
		for(int i=0;i<N;i++) {
			
			
			
			/*for(int j=0;j<N;j++) {
				if(dis1[i][j] + dis2[i][j] == N) answer++;
			}*/
		}
		
		System.out.println(answer);
		
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
	
	public static int[][] floyd(ArrayList<Integer> ary[]){
		int[][] dis = new int[ary.length][ary.length];
		
		for(int i=0;i<ary.length;i++) {
			for(int j=0;j<ary.length;j++) {
				dis[i][j]=0;
			}
			
			for(int next:ary[i]) {
				dis[i][next] = 1;
			}
		}
		
		for(int mid=0;mid<ary.length;mid++) {
			for(int a=0;a<ary.length;a++) {
				for(int b=0;b<ary.length;b++) {
					if(dis[a][mid]!=0 && dis[mid][b]!=0 &&
							dis[a][b] < dis[a][mid] +dis[mid][b]) {
						dis[a][b] = dis[a][mid]+dis[mid][b];
					}
				}
			}
		}
		
		return dis;
	}

}
