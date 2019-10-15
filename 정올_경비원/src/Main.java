import java.io.*;
import java.util.*;
public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static boolean [][]mmap;
	static boolean [][]visited;
	static int answer=0;
	public static void main(String[] args) throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken())+1;
		h = Integer.parseInt(st.nextToken())+1;
		visited = new boolean[h][w];
		mmap = new boolean[h][w];
		for(int i=1;i<h-1;i++) {
			for(int j=1;j<w-1;j++) {
				visited[i][j]=true;
			}
		}
		int M = Integer.parseInt(br.readLine());
		int p,x,i,j;
		for(int k=0;k<=M;k++) {
			st=new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			switch(p) {
			case 1:
				i=0; j=x; break;
			case 2:
				i=h-1; j = x; break;
			case 3:
				i=x; j=0; break;
			default:
				i=x; j=w-1;
			}
			if(k==M) bfs(i,j);
			else mmap[i][j]=true; //상점이 있는 위치이다.
		}
		System.out.println(answer);
		
	}
	
	static int di[]= {-1,1,0,0};
	static int dj[]= {0,0,-1,1};
	public static int w;
	public static int h;

	private static void bfs(int si, int sj) {
		Queue<pair> q=new LinkedList<pair>();
		q.offer(new pair(si,sj,0));
		visited[si][sj]=true;
		
		pair cur;
		int ti,tj;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			if(mmap[cur.i][cur.j]) { //상점
				answer+=cur.cnt;
			}
			
			for (int k = 0; k < di.length; k++) {
				ti=di[k]+cur.i;
				tj=dj[k]+cur.j;
				if(0<=ti&& ti<h && 0<=tj && tj<w && !visited[ti][tj]) {
					q.offer(new pair(ti,tj,cur.cnt+1));
					visited[ti][tj]=true;
				}
			}
			
		}
		
	}



	static class pair{
		int i;
		int j;
		int cnt;
		
		
		public pair(int i, int j, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}


		@Override
		public String toString() {
			return "pair [i=" + i + ", j=" + j + ", cnt=" + cnt + "]";
		}
		
	}

}
