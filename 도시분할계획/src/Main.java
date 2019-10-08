import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int ary[];
	static PriorityQueue<pair> pq=new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		int N,M;
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		ary=new int [N];
		int cnt = N; //마을의 개수 
		int answer=0;
		
		for(int i=0;i<N;i++) ary[i]=i;
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			int C = Integer.parseInt(st.nextToken());
			pq.offer(new pair(A,B,C));
		}
		
		while(!pq.isEmpty()) {
			pair cur= pq.poll();
			int p1=findSet(cur.u1);
			int p2=findSet(cur.u2);
			if(p1==p2) {continue;}
			//union
			ary[p1]=p2;
			answer+=cur.val;
			
			cnt--;
			if(cnt == 2) break; //마을이 2개 남으면 중단
		}
		System.out.println(answer);
	}
	
	static int findSet(int k) {
		if(ary[k]==k) return k;
		return ary[k]=findSet(ary[k]);
	}
	static class pair implements Comparable<pair>{
		int u1;
		int u2;
		int val;
		public pair(int u1, int u2, int val) {
			super();
			this.u1 = u1;
			this.u2 = u2;
			this.val = val;
		}
		@Override
		public String toString() {
			return "pair [u1=" + u1 + ", u2=" + u2 + ", val=" + val + "]";
		}
		@Override
		public int compareTo(pair o) {
			if(o.val == this.val) return 0;
			if(o.val > this.val) return -1;
			else return 1;
		}
		
	}
}
