import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException{
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem(test);
		}
	}

	private static void problem(int test) throws IOException{
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		pair start = new pair(st.nextToken(), st.nextToken());
		
		pair store[]=new pair[N];
		
		pair t;
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			t=new pair(st.nextToken(), st.nextToken());
			store[i]=t;
		}
		
		st=new StringTokenizer(br.readLine());
		pair end = new pair(st.nextToken(), st.nextToken());
		
		
		Queue q=new LinkedList<person>();
		
		q.offer(new person(start,20));
		
		while(!q.isEmpty()) {
			pair cur = q.offer
		}
		
		
	}
	
	static class person{
		pair p;
		int beer;
		
		public person(pair p, int beer) {
			super();
			this.p = p;
			this.beer = beer;
		}
		
		@Override
		public String toString() {
			return "person [p=" + p + ", beer=" + beer + "]";
		}
		
		
		
	}
	
	static class pair{
		int x;
		int y;
		
		public pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		public pair(String nextToken, String nextToken2) {
			this(Integer.parseInt(nextToken),Integer.parseInt(nextToken2));
		}
		
		@Override
		public String toString() {
			return "pair [x=" + x + ", y=" + y + "]";
		}

		public int getDistance(pair p) {
			return Math.abs(this.x - p.x ) - Math.abs(this.y - p.y);
		}
		
		public int getDistance(int x,int y) {
			return Math.abs(this.x -x)+Math.abs(this.y - y);
		}
		
		
		
		
	}

}
