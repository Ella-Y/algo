import java.io.*;
import java.util.*;
/*
 * 5719. 백준 거의 최단 경로
 * */
public class Main {
	static private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static private BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static private int ary[][];
	static private int INF=10000*1000+1;
	public static void main(String[] args) throws IOException{
		int N,M; 
		StringTokenizer stk;
		while(true) {
			stk=new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			if(M==0 && N==0) break;
			else problem(N,M);
		}
		bw.flush();
	}
	public static void problem(int N, int M) throws IOException{
		StringTokenizer stk;
		
		ary=new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				ary[i][j]=INF;
				if(i==j) ary[i][j]=0;
			}
		}
		
		stk=new StringTokenizer(br.readLine());
		int S=Integer.parseInt(stk.nextToken());
		int D=Integer.parseInt(stk.nextToken());
		
		int u,v,p;
		for(int i=0;i<M;i++) {
			stk=new StringTokenizer(br.readLine());
			u = Integer.parseInt(stk.nextToken());
			v = Integer.parseInt(stk.nextToken());
			p = Integer.parseInt(stk.nextToken());
			ary[u][v]=p;
		}
		
		int min = dijkstra(S,D);
		int t=0;
		while((t = dijkstra(S,D)) ==min);
		
		if(t==INF) //System.out.println(-1);
			bw.write("-1\n");
		else //System.out.println(t);
			bw.write(t+"\n");
		
	}

	public static int dijkstra(int S,int D) {
		PriorityQueue<Pair> pq=new PriorityQueue<>();
		
		int path[]=new int [ary.length];
		int dis[]=new int [ary.length];
		
		for(int i=0;i<dis.length;i++) dis[i]=INF;
		
		dis[S]=0; //시작지점은 0
		pq.offer(new Pair(S,0));
		
		outer:while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			if(cur.idx == D) break;
			//cur의 위치에서 출발
			for(int k=0;k<ary[cur.idx].length;k++) { //cur에 연결된 것들
				if(ary[cur.idx][k]==INF || ary[cur.idx][k] ==0) continue;
				
				if(dis[k] > ary[cur.idx][k]+cur.w) {
					dis[k] = ary[cur.idx][k]+cur.w;
					path[k]= cur.idx; //출발 인덱스
					pq.offer(new Pair(k,dis[k]));
				}

			}
		}
		
		int prev=D;
		int cc=path[prev]; //cc->prev
		
		while(prev!=0) {
			//System.out.println(cc+" "+prev);
			ary[cc][prev] = 0; //경로 제거
			prev=cc;
			cc=path[prev];
			
		}
		
		//System.out.println(Arrays.toString(path));
		//System.out.println(Arrays.toString(dis));
		
		return dis[D];
	}
	
	static class Pair implements Comparable<Pair>{
		int idx;
		int w;
		public Pair(int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
		@Override
		public String toString() {
			return "Pair [idx=" + idx + ", w=" + w + "]";
		}
		
		@Override
		public int compareTo(Pair o) {
			if(o.w > w) return -1;
			else if(o.w < w) return 1;
			else {
				if(o.idx == idx) return 0;
				else if(o.idx > idx) return 1;
				else return -1;
			}
		}
		
	}

}
