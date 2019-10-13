import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<pair> info[];
	static final long INF = 2100000000;
	public static void main(String[] args) throws IOException{
		System.out.println(INF);
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		info = new ArrayList[N+1];
		
		for(int i=0;i<N+1;i++) {
			info[i]=new ArrayList<>();
		}
		
		StringTokenizer st;
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			info[a].add(new pair(b,c));
			info[b].add(new pair(a,c));
		}
		
		st=new StringTokenizer(br.readLine());
		int s=Integer.parseInt(st.nextToken());
		int f=Integer.parseInt(st.nextToken());
		
		long d[]=dijkstra(s);
		
		System.out.println(d[f]);

	}
	private static long[] dijkstra(int s) {
		long d[]=new long[info.length];
		
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
					System.out.println(next.index+" "+d[next.index]);
					pq.offer(new pair(next.index,d[next.index]));
				}
			}
		}
		
		
		return d;
	}
	
	static class pair implements Comparable<pair>{
		int index;
		long w;
		public pair(int index, long w) {
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
			if(o.w == w) return 0;
			if(w>o.w) return 1;
			else return -1;
		}
		
	}
}
