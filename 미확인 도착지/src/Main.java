import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static ArrayList<pair> info[];
	static final int INF = 10_000_000+1;
	
	public static void main(String[] args) throws IOException{
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem();
		}
		bw.flush();
		bw.close();
	}
	
	private static void problem() throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int T=Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer(br.readLine());
		int s=Integer.parseInt(st.nextToken()); //예술가들의 출발지
		int g=Integer.parseInt(st.nextToken()); //지나가야 하는 위치 -1
		int h=Integer.parseInt(st.nextToken()); //지나가야하는 위치 -2
		
		info=new ArrayList[N+1];

		
		for(int i=0;i<N+1;i++) {
			info[i]=new ArrayList<pair>();
		}
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			info[a].add(new pair(b,c));
			info[b].add(new pair(a,c));
		}
		
		int s_dijk[]=dijkstra(s); //s에서 출발해서 각 도착지 최단경로
		int g_dijk[]=dijkstra(g); 
		int h_dijk[]=dijkstra(h);
		
		TreeSet <Integer> tset = new TreeSet<>();
		for(int i=0;i<T;i++) {
			int x=Integer.parseInt(br.readLine());
			int answer = s_dijk[x];
			if(answer == INF) continue; //갈 수 없다.
			
			//s->g->h->x
			int r1=check(s_dijk[g],g_dijk[h],h_dijk[x]);
			//s->h->g->x
			int r2=check(s_dijk[h],h_dijk[g],g_dijk[x]);
			
			int temp = r1>r2?r2:r1;
			
			if(answer == temp) //갈 수 있다.
				tset.add(x);
		}
		
		while(!tset.isEmpty())
			bw.write(tset.pollFirst()+" ");
		bw.newLine();
	}
	

	private static int check(int i, int j, int k) {
		if(i == INF) return INF;
		if(j == INF) return INF;
		if(k == INF) return INF;
		return i+j+k;
	}

	private static int[] dijkstra(int s) {
		int d[]=new int[info.length];
		
		for(int i=0;i<d.length;i++) {
			d[i]=INF;
		}
		d[s]=0;
		PriorityQueue<pair> pq=new PriorityQueue<>();
		pq.offer(new pair(s,0));
		
		while(!pq.isEmpty()) {
			pair cur = pq.poll();
			for(pair next : info[cur.index]) {
				if(next.w + d[cur.index] < d[next.index]) {
					d[next.index] = next.w+d[cur.index];
					pq.offer(new pair(next.index,d[next.index]));
				}
			}
		}
		
		
		return d;
	}

	static class pair implements Comparable<pair>{
		int index;
		int w;
		public pair(int index, int w) {
			super();
			this.index = index;
			this.w = w;
		}
		@Override
		public String toString() {
			return "pair [index=" + index + ", w=" + w + "]";
		}
		
		@Override
		public int compareTo(pair o) {
			if(w==o.w)
				return 0;
			else if(w>o.w) return 1;
			else return -1;
		}
		
	}

}
