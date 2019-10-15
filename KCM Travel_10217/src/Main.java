import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int d[];
	static boolean visited[];
	static pair ary[][];
	static int N,M;
	static int answer;
	static int answerTime;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem();
		}
	}
	
	private static void problem() throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		answer = Integer.MAX_VALUE;
		N=Integer.parseInt(st.nextToken())+1; //공항의 수 N<=100
		M=Integer.parseInt(st.nextToken()); //지원비용
		int K=Integer.parseInt(st.nextToken()); //티켓 정보의 수
		
		d=new int[N];
		visited=new boolean[N];
		ary=new pair[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++)
			{
				if(i==j) {
					ary[i][j]=new pair(0,0); continue;
				}
				ary[i][j]=new pair(INF,INF);
			}
		}
		
		int u,v,c,t;
		for(int i=0;i<K;i++) {
			st=new StringTokenizer(br.readLine());
			u=Integer.parseInt(st.nextToken());
			v=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			t=Integer.parseInt(st.nextToken());
			ary[u][v]=new pair(c,t);
			
		}
		answer =answerTime = Integer.MAX_VALUE;
		
		visited[1]=true;
		
		dfs(1,0,0,0);
		if(answer == Integer.MAX_VALUE) System.out.println("Poor KCM");
		else System.out.println(answer);
		
	}
	
	private static void dfs(int cur,int time,int cost, int deep) {
		if(cost>M) return;
		if(cur==N-1) {
			answer = Math.min(time, answer);
			answerTime = time;
			return;
		}
		if(deep == N) {
			//모든 공항을 다 돌았다.
			return;
		}
		
		if(answerTime!=Integer.MAX_VALUE && answerTime<time) {
			return;
		}
		
		for(int i=0;i<ary[cur].length;i++) {
			if(ary[cur][i].time == INF || ary[cur][i].time ==0) continue; //갈 수 없음
			
			d[i] = Math.min(d[i], dfs())
		}
		
		/*for(pair next : ary[cur]) {
			if(!visited[next.idx]) {
				visited[next.idx] =true;
				dfs(next.idx,time+next.time,cost+next.cost,deep+1);
				visited[next.idx] = false;
			}
		}*/
		
		
	}
	
	static class pair{
		int cost;
		int time;
		
		public pair(int cost, int time) {
			super();
			this.cost = cost;
			this.time = time;
		}
		@Override
		public String toString() {
			return "pair [cost=" + cost + ", time=" + time + "]";
		}

		
		
		
	}

}
