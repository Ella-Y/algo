import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<pair> ary ;
	static int d[];
	static final int INF= Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem();
		}
	}

	private static void problem() throws IOException {
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		d=new int[N+1];
		ary = new ArrayList<>();

		int s,e,t;
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			s=Integer.parseInt(st.nextToken());
			e=Integer.parseInt(st.nextToken());
			t=Integer.parseInt(st.nextToken());
			ary.add(new pair(s,e,t));
			ary.add(new pair(e,s,t));
		}

		for(int i=0;i<W;i++) {
			st=new StringTokenizer(br.readLine());
			s=Integer.parseInt(st.nextToken());
			e=Integer.parseInt(st.nextToken());
			t=Integer.parseInt(st.nextToken());

			ary.add(new pair(s,e,-t));

		}

		for(int i=0;i<d.length;i++) {
			d[i] = INF;
		}

		d[1] =0;
		
		for(int i=1;i<N-1;i++) {
			for(pair p:ary) {
				if(d[p.u]!=INF && d[p.v]>d[p.u]+p.w) {
					d[p.v]=d[p.u]+p.w;
				}
			}
		}
		
		int []solv = d.clone();
		
		for(pair p:ary) {
			if(d[p.u]!=INF && d[p.v]>d[p.u]+p.w) {
				d[p.v] = d[p.u]+ p.w;  
			}
		}
		
		boolean pass = true;
		for(int i = 2 ; i <= N ; i++)
		{
			if(solv[i] != d[i])
			{
				pass = false;
				break;
			}
		}
		
		if(!pass) System.out.println("YES");
		else System.out.println("NO");
		


	}
	static class pair{
		int u;
		int v;
		int w;

		public pair(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "pair [u=" + u + ", v=" + v + ", w=" + w + "]";
		}

		@Override
		public int hashCode() {
			return (u+v);
		}

		@Override
		public boolean equals(Object o) {
			pair obj = (pair) o;
			if(u==obj.u && v==obj.v) return true;
			return false;
		}

	}
}
