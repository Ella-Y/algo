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
		
		minRoute=new HashSet<>();
		
		int min = dijkstra(S,D);
		//System.out.println(minRoute);
		//System.out.println(min);
		
		for(Edge e:minRoute) {
			ary[e.i][e.j]=INF; 
		}
		
		min = dijkstra(S,D);
		if(min==INF) //System.out.println(-1);
			bw.write("-1\n");
		else //System.out.println(min);
			bw.write(min+"\n");
		//System.out.println(dijkstra(S,D));
	}
	
	private static HashSet<Edge> minRoute;
	
	public static int dijkstra(int S,int D) {
		PriorityQueue<Pair> pq=new PriorityQueue<>();

		int dis[]=new int [ary.length];
		
		for(int i=0;i<dis.length;i++) {
			dis[i]=INF;
		}
		
		dis[S]=0; //시작지점은 0
		pq.offer(new Pair(S,0,String.valueOf(S)));
		
		outer:while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			//System.out.println(cur);
			if(cur.idx == D) {
				if(dis[D] == cur.w) {
					//System.out.println(cur.str);
					StringTokenizer stk= new StringTokenizer(cur.str);
					int u1= Integer.parseInt(stk.nextToken());
					int u2= Integer.parseInt(stk.nextToken());
					
					minRoute.add(new Edge(u1,u2));
					
					while(stk.hasMoreTokens()) {
						u1=u2;
						u2=Integer.parseInt(stk.nextToken());
						
						minRoute.add(new Edge(u1,u2));
					}
				}
			}
			
			//cur의 위치에서 출발
			for(int k=0;k<ary[cur.idx].length;k++) { //cur에 연결된 것들
				if(ary[cur.idx][k]==INF || ary[cur.idx][k] ==0) continue;
				
				if(dis[k] >= ary[cur.idx][k]+cur.w) {
					dis[k] = ary[cur.idx][k]+cur.w;
					StringBuilder sb= new StringBuilder(cur.str);
					sb.append(" ");
					sb.append(k);
					pq.offer(new Pair(k,dis[k],sb.toString()));
				}
				
			}
		}
		
		
		return dis[D];
	}
	static class Edge{
		int i;
		int j;
		@Override
		public String toString() {
			return "Edge [i=" + i + ", j=" + j + "]";
		}
		public Edge(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Edge) {
				Edge ob=(Edge) obj;
				if(ob.i ==this.i && ob.j==this.j) return true;
				else return false;
			}
			return false;
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode();
		}
		
	}
	static class Pair implements Comparable<Pair>{
		int idx;
		int w;
		String str;
		public Pair(int idx, int w, String t) {
			this.idx = idx;
			this.w = w;
			this.str=t;
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
