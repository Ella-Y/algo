import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int dt[];
	static int dc[];
	static ArrayList<pair> ary[];
	static final int INF = 10000*10000+1;
	
	public static void main(String[] args) throws IOException {
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem();
		}
	}
	
	private static void problem() throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken())+1; //공항의 수 N<=100
		int M=Integer.parseInt(st.nextToken()); //지원비용
		int K=Integer.parseInt(st.nextToken()); //티켓 정보의 수
		
		dt=new int[N];
		dc=new int[N];
		ary=new ArrayList[N];
		
		for(int i=0;i<N;i++)
			ary[i]=new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			dt[i]=INF;	//시간
			dc[i]=INF; //비용
		}
		
		int u,v,c,t;
		for(int i=0;i<K;i++) {
			st=new StringTokenizer(br.readLine());
			u=Integer.parseInt(st.nextToken());
			v=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			t=Integer.parseInt(st.nextToken());
			ary[u].add(new pair(v,c,t));
			
		}
		
		PriorityQueue<pair> pq=new PriorityQueue<>();
		pq.offer(new pair(1,0,0));
		dt[1]=0;
		dc[1]=0;
		
		int tcost,ttime,cur;
		
		while(!pq.isEmpty()) {
			pair cc = pq.poll(); //지금까지 온...
			cur=cc.idx;
			for(pair next : ary[cur]) {
				
				if(dc[cur]==INF || dt[cur]==INF) continue;
				
				tcost=dc[cur]+next.cost;
				ttime=dt[cur]+next.time;
				
				if(M == tcost) { //이미 주어진 비용을 다 씀
					if(dt[next.idx] >= ttime) {
						dt[next.idx] =ttime;
						dc[next.idx] =Math.min(dc[next.idx], tcost);
					}
				}
				else if(M < tcost) {continue;}
				else {
					if(dt[next.idx] > ttime) {
						dt[next.idx]= ttime;
						dc[next.idx]= tcost;
						pq.offer(new pair(next.idx,dc[next.idx],dt[next.idx]));
					}
				}
			}
			
		}
		
		//System.out.println("cost:"+ Arrays.toString(dc));
		//System.out.println("time:"+ Arrays.toString(dt));
		
		if(dt[N-1] == INF)
			System.out.println("Poor KCM");
		else
			System.out.println(dt[N-1]);
		
		
		
	}
	static class pair implements Comparable<pair>{
		int idx;
		int cost;
		int time;
		
	
		@Override
		public String toString() {
			return "pair [idx=" + idx + ", cost=" + cost + ", time=" + time + "]";
		}


		public pair(int idx, int cost, int time) {
			super();
			this.idx = idx;
			this.cost = cost;
			this.time = time;
		}


		@Override
		public int compareTo(pair o) {
			
			if(o.time == time) {
				if(o.cost == cost) {
					return 0;
				}
				else if(o.cost> cost) return -1;
				else return 1;
			}
			else if(o.time>time) return -1;
			else return 1;
			
		}
		
		
	}

}
