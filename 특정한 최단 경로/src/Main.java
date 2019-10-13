import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static final int INF = 200_000_000;
	static ArrayList<pair> info[];
	public static void main(String[] args) throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		info = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			info[i]=new ArrayList<pair>();
		}
		
		for(int i=0;i<E;i++) {
			st=new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c= Integer.parseInt(st.nextToken());
			info[a].add(new pair(b,c));
			info[b].add(new pair(a,c));
		}
		
		st=new StringTokenizer(br.readLine());
		int pa= Integer.parseInt(st.nextToken()); //pass a
		int pb= Integer.parseInt(st.nextToken()); //pass b
		
		
		//다익스트라
		//1->a->b->N -1
		//1->b->a->N -2
		
		
		int answer1 = extracted(pa, pb, N);
		int answer2 = extracted(pb, pa, N);	
				
		if(answer1==-1 && answer2==-1) System.out.println(-1);
		else if(answer1==-1) {
			System.out.println(answer2);
		}else if(answer2==-1 || answer1<answer2) {
			System.out.println(answer1);
		
		}else System.out.println(answer2);
		
		
	}

	private static int extracted(int pa, int pb, int N) {
		int answer= 0; 
		int temp = dijkstra(1,pa);
		//System.out.println(1+" "+pa+" "+temp);
		if(temp !=INF) {
			answer+=temp;
			temp = dijkstra(pa,pb);
			//System.out.println(pa+" "+pb+" "+temp);
			if(temp!=INF) {
				answer+=temp;
				temp = dijkstra(pb,N);
				//System.out.println(pb+" "+N+" "+temp);
				if(temp!=INF)
					answer+=temp;
				else answer= -1;
				
			}else answer= -1;
			
		}else answer = -1;
		
		//System.out.println(answer);
		return answer;
	}
	
	public static int dijkstra(int st,int fin) {
		int N= info.length;
		int d[]=new int[N+1]; //항상 적은 비용을 가지고 있음
		
		for(int i=0;i<N+1;i++) {
			if(i==st) continue;
			d[i]=INF;
		}
		
		PriorityQueue<pair> pq=new PriorityQueue<>();
		pq.offer(new pair(st,0));
		
		while(!pq.isEmpty()) {
			pair cur= pq.poll();
			//자신이 갈 수 있는 모든 노드에 대해서
			for(pair next : info[cur.u1]) {
				//u1->item.u1 으로 가는 비용이 원래 적은 비용으로 오는 d[item.u1]보다 작으면
				if(d[cur.u1] + next.w < d[next.u1]) {
					d[next.u1]= d[cur.u1] + next.w;
					pq.offer(new pair(next.u1, d[next.u1]));
				}
			}
		}
		
		return d[fin];
	}
	
	static class pair implements Comparable<pair>{
		int u1;
		int w;
		public pair(int u1, int w) {
			super();
			this.u1 = u1;
			this.w = w;
		}
		@Override
		public String toString() {
			return "pair [u1=" + u1 + ", w=" + w + "]";
		}
		
		@Override
		public int compareTo(pair o) {
			if(w==o.w)
				return 0;
			else if(w>o.w) return 1;
			else return -1;
		}
		
	}
}
