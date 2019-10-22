import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem(test);
		}
		bw.flush();
	}

	private static void problem(int test) throws IOException{
		int N = Integer.parseInt(br.readLine());
		StringTokenizer ST=new StringTokenizer(br.readLine());
		
		ArrayList<pair> ary = new ArrayList<>();
		//==input==
		pair start = new pair(ST.nextToken(), ST.nextToken());
		ary.add(start);
		
		pair t;
		for(int i=0;i<N;i++) {
			ST=new StringTokenizer(br.readLine());
			t=new pair(ST.nextToken(), ST.nextToken());
			ary.add(t);
		}
		
		ST=new StringTokenizer(br.readLine());
		pair end = new pair(ST.nextToken(), ST.nextToken());
		ary.add(end);
		//==input - end==
		
		
		int [][]routeInfo = new int[ary.size()][ary.size()];
		
		int tDis;
		//간선들의 가중치 구하기
		for(int i=0;i<routeInfo.length;i++) {
			for(int j=0;j<routeInfo.length;j++) {
				tDis = ary.get(i).getDistance(ary.get(j));
				if(tDis<=1000)
					routeInfo[i][j] =tDis;
				else {
					routeInfo[i][j]= INF;
				}
			}
		}
		
		for(int mid=0;mid<ary.size();mid++) { //중간거점
			for(int st=0;st<ary.size();st++) {
				for(int fin=0;fin<ary.size();fin++) {
					if(routeInfo[st][mid]!=INF && routeInfo[mid][fin]!=INF
							&&routeInfo[st][fin] > routeInfo[st][mid]+routeInfo[mid][fin] )
						routeInfo[st][fin] = routeInfo[st][mid] +routeInfo[mid][fin];
				}
			}
		}
		
		if(routeInfo[0][routeInfo.length-1] == INF) //System.out.println("sad");
			bw.write("sad\n");
		else //System.out.println("happy");
			bw.write("happy\n");
		
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
			return Math.abs(this.x - p.x )+Math.abs(this.y - p.y);
		}
		
	}

}
