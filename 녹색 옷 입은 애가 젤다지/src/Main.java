import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException{
		int N;
		int test=1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			problem(N,test);
			test++;
		}
	}
	
	private static final int INF = 125*125*9+1;
	private static final int di[]= {-1,0,1,0};
	private static final int dj[]= {0,-1,0,1};
	
	private static void problem(final int N,int test) throws IOException{
		StringTokenizer stk;
		
		int dis[][]=new int [N][N];
		int info[][]=new int [N][N];
		
		//make INF
		//init info
		for(int i=0;i<N;i++) {
			stk=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				dis[i][j]=INF;
				info[i][j]=Integer.parseInt(stk.nextToken());
			}
		}
		
		dis[0][0]=info[0][0]; //시작지점
		//도착지점인 [N-1][N-1]로 가야한다.
		
		//다익스트라는 큐에 갈 수 있는 edge를 담아둔다.
		PriorityQueue<Edge> pq=new PriorityQueue<>();
		pq.offer(new Edge(0,0,info[0][0]));
		
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			//여기서 갈 수 있는 곳으로 간다. ->4방향
			for(int k=0;k<4;k++) {
				int ti=cur.ni+di[k]; //다음에 갈 수 있는 노드
				int tj=cur.nj+dj[k];
				if(0<=ti && ti<N && 0<=tj && tj<N) { //들어갈 수 있으면
					if(dis[ti][tj] > dis[cur.ni][cur.nj]+info[ti][tj]) {
						dis[ti][tj] = dis[cur.ni][cur.nj]+info[ti][tj];
						pq.offer(new Edge(ti,tj,dis[ti][tj]));
					}
				}
			}
			
		}
		
		
		System.out.println("Problem "+test+": "+dis[N-1][N-1]);
		
	}
	
	static class Edge implements Comparable<Edge>{
		int ni;
		int nj;
		int w; //가중치 : 지금까지 걸어온 값
		
		public Edge(int i, int j, int w) {
			super();
			this.ni = i;
			this.nj = j;
			this.w = w;
		}
		
		@Override
		public String toString() {
			return "Edge [i=" + ni + ", j=" + nj + ", w=" + w + "]";
		}
		
		@Override
		public int compareTo(Edge o) {
			if(w == o.w) return 0;
			return w>o.w ? 1:-1;
		}
		
	}

}
