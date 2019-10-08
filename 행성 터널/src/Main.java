import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int info[][]=null;
	static int ary[]=null;
	static PriorityQueue<pair> pq=new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		info = new int [N][4];//x,y,z,행성번호
		ary=new int [N];
		
		for(int i=0;i<N;i++) {
			ary[i]=i;
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				info[i][j]=Integer.parseInt(st.nextToken());
			}
			info[i][3]=i;
		}
		
		//====================
		Arrays.sort(info,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]- o2[0];
			}
		});
		
		for(int i=1;i<N;i++) {
			pq.offer(new pair(info[i-1][3],info[i][3],Math.abs(info[i-1][0]-info[i][0])));
		}
		
		Arrays.sort(info,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]- o2[1];
			}
		});
		
		for(int i=1;i<N;i++) {
			pq.offer(new pair(info[i-1][3],info[i][3],Math.abs(info[i-1][1]-info[i][1])));
		}
		
		Arrays.sort(info,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]- o2[2];
			}
		});
		
		for(int i=1;i<N;i++) {
			pq.offer(new pair(info[i-1][3],info[i][3],Math.abs(info[i-1][2]-info[i][2])));
		}
		
		long answer =0;
		
		while(!pq.isEmpty()) {
			pair cur = pq.poll();
			
			int p1=findSet(cur.u1);
			int p2=findSet(cur.u2);
			if(p1==p2) continue;
			
			answer = answer + cur.val;
			ary[p1]=p2;
			
		}
		
		System.out.println(answer);
		
	}
	
	private static int findSet(int u2) {
		if(ary[u2]== u2) return u2;
		return ary[u2]=findSet(ary[u2]);
	}

	static class pair implements Comparable<pair>{
		int u1;
		int u2;
		long val;
		
		public pair(int u1, int u2, long val) {
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
			if(o.val == val) return 0;
			if(val > o.val) return 1;
			return -1;
		}
		
		
	}

}
