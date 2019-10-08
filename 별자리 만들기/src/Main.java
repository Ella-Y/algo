import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int ary[];
	static PriorityQueue<pair> pq=new PriorityQueue<>();
	public static void main(String[] args) throws IOException{
		int N=Integer.parseInt(br.readLine());
		ary=new int[N];
		
		double x[]=new double[N];
		double y[]=new double[N];
		
		for(int i=0;i<N;i++) {
			ary[i]=i;
			StringTokenizer st=new StringTokenizer(br.readLine());
			x[i]=Double.parseDouble(st.nextToken());
			y[i]=Double.parseDouble(st.nextToken());
		}
		
		for(int i=0;i<N;i++) {
			for(int j=i+1;j<N;j++) {
				double dis = (x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]);
				dis=Math.sqrt(dis);
				pq.offer(new pair(i,j,dis));
			}
		}
		
		double answer=0;
		
		while(!pq.isEmpty()) {
			pair ta= pq.poll();
			int pa = findSet(ta.u1);
			int pb= findSet(ta.u2);
			if(pa==pb) continue;
			ary[pa]=pb;
			answer+=ta.dis;
		}
		
		System.out.println(String.format("%.2f", answer));
		
	}
	
	public static int findSet(int k) {
		if(ary[k]==k) return k;
		return ary[k]=findSet(ary[k]);
	}
	
	public static class pair implements Comparable<pair>{
		int u1;
		int u2;
		double dis;
		public pair(int u1, int u2, double dis) {
			super();
			this.u1 = u1;
			this.u2 = u2;
			this.dis = dis;
		}
		
		@Override
		public String toString() {
			return "pair [u1=" + u1 + ", u2=" + u2 + ", dis=" + dis + "]";
		}
		
		@Override
		public int compareTo(pair o) {
			if(dis == o.dis) return 0;
			else if(dis>o.dis) return 1;
			return -1;
		}
		
		
		
		
	}
}
