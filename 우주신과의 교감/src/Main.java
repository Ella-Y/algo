import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int ary[];
	static PriorityQueue<Pair> pq=new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N,M;
		int info[][];
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		info=new int [N][3]; //xÁÂÇ¥, yÁÂÇ¥
		ary=new int [N];
		
		for(int i=0;i<N;i++) {
			ary[i]=i; //init
			//input
			st=new StringTokenizer(br.readLine());
			info[i][0]=Integer.parseInt(st.nextToken());
			info[i][1]=Integer.parseInt(st.nextToken());
			info[i][2]=i;
		}
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int u1=Integer.parseInt(st.nextToken())-1;
			int u2=Integer.parseInt(st.nextToken())-1;
			
			int p1=findSet(u1);
			int p2=findSet(u2);
			if(p1==p2) continue; //already connected
			
			ary[p1]=p2; 
		}

		for(int i=0;i<N;i++) {
			for(int j=i+1;j<N;j++) {
				pq.offer(new Pair(info[i][2],info[j][2],getDistance(info[i], info[j])));
			}
		}

		double answer=0;
		while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			//System.out.println(cur);
			int p1=findSet(cur.u1);
			int p2=findSet(cur.u2);
			if(p1==p2) continue;
			answer +=cur.dis;
			
			ary[p1]=p2;
		}
		
		System.out.println(String.format("%.2f", answer));

	}
	
	private static int findSet(int u1) {
		if(u1==ary[u1]) return u1;
		return ary[u1]=findSet(ary[u1]);
	}

	private static double getDistance(int[] is, int[] is2) {
		double x= is[0]-is2[0];
		double y= is[1]-is2[1];
		
		return Math.sqrt(x*x + y*y);
	}

	static class Pair implements Comparable<Pair>{
		int u1;
		int u2;
		double dis;
		
		public Pair(int u1, int u2, double dis) {
			super();
			this.u1 = u1;
			this.u2 = u2;
			this.dis = dis;
		}
		@Override
		public String toString() {
			return "Pair [u1=" + u1 + ", u2=" + u2 + ", dis=" + dis + "]";
		}
		
		@Override
		public int compareTo(Pair o) {
			if(o.dis == this.dis) return 0;
			else if(o.dis>this.dis) return -1;
			else return 1;
		}
		
	}

}
