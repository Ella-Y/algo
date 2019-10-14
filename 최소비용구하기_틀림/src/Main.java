import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	static final int INF= 100_000*100_000+1;
	static ArrayList<pair> edge;
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			edge.add(new pair(a,b,c));
		}
		st=new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int fin = Integer.parseInt(st.nextToken());
		
		int d[]=dijkstra(start,N);
		System.out.println(d[fin]);
		
	}
	
	private static int[] dijkstra(int start,final int N) {
		int d[]=new int [N+1];
		for(int i=0;i<=N;i++) {
			d[i] =INF;
		}
		
		d[start]=0;
		
		
		
		return d;
	}

	static class pair implements Comparable<pair>{
		int a;
		int b;
		int w;
		
		public pair(int a, int b, int w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}
		
		@Override
		public String toString() {
			return "pair [a=" + a + ", b=" + b + ", w=" + w + "]";
		}

		@Override
		public int compareTo(pair o) {
			if(o.w == w) return 0;
			if(w>o.w) return 1;
			else return -1;
		}
		
	}
}
