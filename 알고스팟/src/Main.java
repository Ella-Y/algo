import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
	private static int M,N;
	private static int dis[][];
	private static char mmap[][];
	private static final int WALL = 1;
	
	private static final int di[]= {-1,0,1,0};
	private static final int dj[]= {0,-1,0,1};
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		StringTokenizer stk=new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		
		dis=new int [N][M];
		mmap=new char [N][];
		
		for(int i=0;i<N;i++) {
			mmap[i]=br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				dis[i][j]=INF;
			}
		}
		
		dis[0][0]=0; //시작지점
		
		//--------------------
		int answer=0,ti,tj;
		
		PriorityQueue<Pair> pq=new PriorityQueue<>(); //i,j,w
		pq.offer(new Pair(0,0,0));
		
		while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			
			if(cur.i == N-1 && cur.j == M-1) {
				answer=dis[N-1][M-1];
				break;
			}
			
			//4방향으로 갈 수 있는데
			for(int k=0;k<4;k++) {
				ti=cur.i+di[k];
				tj=cur.j+dj[k];
				
				if(0<=ti && ti<N && 0<=tj && tj<M) {
					//만약 0이라면 dis는 그대로...
					if(mmap[ti][tj]=='1') {
						
						if(dis[ti][tj] > cur.w +1) {
							dis[ti][tj] = cur.w+1;
							pq.offer(new Pair(ti,tj,dis[ti][tj]));
						}
						
						
					}
					else {
						if(dis[ti][tj] > cur.w) {
							dis[ti][tj]= cur.w;
							pq.offer(new Pair(ti,tj,dis[ti][tj]));
						}
					}
					
					
				}
				
			}
			
		}
		printMap(dis);
		System.out.println(answer);
		
	}
	
	private static void printMap(int[][] dis2) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(dis2[i][j]+" ");
			}
			System.out.println();
		}
		
	}

	static class Pair implements Comparable<Pair>{
		
		int i;
		int j;
		int w;
		
		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + ", w=" + w + "]";
		}
		
		public Pair(int i, int j, int w) {
			super();
			this.i = i;
			this.j = j;
			this.w = w;
		}

		@Override
		public int compareTo(Pair o) {
			return w-o.w;
		}
		
		
	}
	
	
}
