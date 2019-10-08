import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static PriorityQueue<Road> pq=new PriorityQueue<>();
	static int ary[]=null;
	public static void main(String[] args) throws IOException{
		StringTokenizer st=null;
		int M,N; //집의수, 거리의 수
		M=1;N=1;
		
		while(true) {
			st=new StringTokenizer(br.readLine());
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			if(M==0 && N==0) break;
			problem(M,N);
		}
		
	}
	
	public static void problem(int M,int N) throws IOException {
		StringTokenizer st;
		
		ary=new int [M];
		
		for(int i=0;i<M;i++) {
			ary[i]=i;
		}
		
		int answer=0;
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int z=Integer.parseInt(st.nextToken());
			pq.offer(new Road(x,y,z));
			answer +=z;
		}
			
		while(!pq.isEmpty()) {
			
			Road cur=pq.poll();
			
			int p1=findSet(cur.x);
			int p2=findSet(cur.y);			
			if(p1==p2) continue;
			ary[p1]=p2;
			answer -=cur.val;
			
		}
		
		System.out.println(answer);
	}
	
	private static int findSet(int y) {
		if(ary[y]==y) return y;
		return ary[y]=findSet(ary[y]);
	}

	static class Road implements Comparable<Road>{
		int x;
		int y;
		int val;
		
		@Override
		public String toString() {
			return "Road [x=" + x + ", y=" + y + ", val=" + val + "]";
		}

		public Road(int x, int y, int val) {
			super();
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Road o) {
			if(val == o.val ) return 0;
			if(o.val > val) return -1;
			else return 1;
		}
		
	}

}
