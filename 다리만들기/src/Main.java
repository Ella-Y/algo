import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int mmap[][]=null;
	static boolean visited[][]=null;
	static int ary[]=null;
	
	static int N;
	static int M;
	static int di[]= {-1,1,0,0};
	static int dj[]= {0,0,-1,1};

	static PriorityQueue<bridge> pq=new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		mmap=new int[N][M];
		visited=new boolean [N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				mmap[i][j]=Integer.parseInt(st.nextToken());
				if(mmap[i][j]==1) mmap[i][j]=-1;
			}
		}
		
		//bfs 섬 숫자 매기기
		int cnt=1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!visited[i][j] && mmap[i][j]==-1) {
					checkIsland(i,j,cnt);
					cnt++;
				}
			}
		}
		
		//print(cnt);
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(mmap[i][j]==0) continue;

				for(int k=0;k<4;k++) {
					int ti=i+di[k];
					int tj=j+dj[k];
					if(0<=ti && ti<N && 0<=tj && tj<M && mmap[ti][tj]==0) {
						//바다로 간다. ->다리갯수를 알자
						getBridge(ti,tj,k);
					}
				}
			}
		}
		
		ary=new int [cnt];
		for(int i=1;i<ary.length;i++) {
			ary[i]=i;
		}
		
		int answer=0;
		cnt=0; //다리개수
		while(!pq.isEmpty()) {
			bridge b=pq.poll();
			//System.out.println(b);
			int p1=findSet(b.from);
			int p2=findSet(b.to);
			
			if(p1==p2) continue;
			
			//union
			ary[p1]=p2;
			cnt++;
			answer+=b.length;
			
		}
		
		//System.out.println(cnt+" "+(ary.length-2));
		
		if(cnt >= ary.length-2) {
			System.out.println(answer);
		}else {
			System.out.println(-1);
		}
		

	}

	@SuppressWarnings("unused")
	private static void print(int cnt) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(mmap[i][j]);
			}
			System.out.println();
		}
		
		System.out.println(cnt);
	}
	
	public static int findSet(int u1) {
		if(ary[u1]==u1) return u1;
		ary[u1]=findSet(ary[u1]);
		return ary[u1];
	}
	

	public static void getBridge(int si,int sj,int dir) {
		int cnt=0;
		int from = mmap[si-di[dir]][sj-dj[dir]];
		int to = -1;
		while(0<=si && si<N && 0<=sj && sj<M) {
			if(mmap[si][sj]==0) {
				cnt++;
				si+=di[dir];
				sj+=dj[dir];
			}else {
				/*visited[si][sj]=true;*/
				to=mmap[si][sj];
				break;
			}
		}
		
		if(to==-1) return;
		
		if(cnt>=2)
			pq.offer(new bridge(from,to,cnt));
		
	}
	
	private static void checkIsland(int si, int sj, int cnt) {
		LinkedList<pair> q=new LinkedList<>();
		visited[si][sj]=true;
		mmap[si][sj]=cnt;
		q.offer(new pair(si,sj));
		
		while(!q.isEmpty()) {
			pair cur = q.poll();
			for(int k=0;k<4;k++) {
				int ti=cur.i+di[k];
				int tj=cur.j+dj[k];
				if(0<=ti && ti<N && 0<=tj && tj<M && !visited[ti][tj] && mmap[ti][tj]==-1) {
					mmap[ti][tj]=cnt;
					visited[ti][tj]=true;
					q.offer(new pair(ti,tj));
				}
			}
		}
	}
	
	static class bridge implements Comparable<bridge>{
		int from;
		int to;
		int length;
		
		public bridge(int u1, int u2, int length) {
			super();
			this.from = u1;
			this.to = u2;
			this.length = length;
		}
		
		@Override
		public String toString() {
			return "bridge [u1=" + from + ", u2=" + to + ", length=" + length + "]";
		}

		@Override
		public int compareTo(bridge o) {			
			if(length == o.length) return 0;
			else if(length > o.length) return 1;
			else return -1;
		}
		
		
	}
	
	
	
	static class pair{
		int i;
		int j;
		public pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "pair [i=" + i + ", j=" + j + "]";
		}
		
	}
}
