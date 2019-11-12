import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int R,C;
	static char map[][];
	
	static final int di[]= {-1,0,1,0};
	static final int dj[]= {0,-1,0,1};
	
	public static void main(String[] args) throws IOException{
		StringTokenizer stk= new StringTokenizer(br.readLine());
		R=Integer.parseInt(stk.nextToken());
		C=Integer.parseInt(stk.nextToken());
		map = new char [R][];
		Pair start=new Pair();
		Pair fin=new Pair();
		
		Queue<Pair> water = new LinkedList<>();
		
		for(int i=0;i<R;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				if(map[i][j] == 'S') {
					map[i][j]='.';
					start =new Pair(i,j);
				}else if(map[i][j]=='D') {
					fin = new Pair(i,j);
				}else if(map[i][j]=='*') {
					water.offer(new Pair(i,j));
				}
			}
		}
		boolean visited[][]=new boolean [R][C];
		
		Queue<Pair> queue =new LinkedList<>(); //고슴도치가 이동하는 큐
		queue.offer(start);
		visited[start.i][start.j]=true; 
		int ti,tj;
		int turn =0;
		boolean exit= false;
		outer:while(!queue.isEmpty()) {
			turn++;
			//고슴도치가 먼저 이동한다.
			int size=queue.size();
			for(int s=0;s<size;s++) {
				Pair cur = queue.poll();
				if(map[cur.i][cur.j]== '*' ) continue;
				if(cur.i == fin.i&& cur.j == fin.j) {
					exit=true;
					break outer;
				}
				for(int k=0;k<4;k++) {
					ti = cur.i+di[k];
					tj = cur.j+dj[k];
					if(0<=ti && ti<R && 0<=tj && tj<C) {
						if(!visited[ti][tj] && map[ti][tj]=='.' || map[ti][tj]=='D') {
							//물  또는 돌이 아니라면 어디든지 갈 수 있다.
							visited[ti][tj]=true;
							queue.offer(new Pair(ti,tj));
						}
					}
				}
			}
			
			//물이 이동한다.
			size = water.size();
			for(int s=0;s<size;s++) {
				Pair cur = water.poll(); //물
				for(int k=0;k<4;k++) {
					ti = cur.i+di[k];
					tj = cur.j+dj[k];
					if(0<=ti && ti<R && 0<=tj && tj<C) {
						if(map[ti][tj]=='.') {
							//물  또는 돌 또는 비버의 영역이 아니라면 어디든지 갈 수 있다.
							map[ti][tj] = '*'; //물로 채워짐!
							visited[ti][tj]=true;
							water.offer(new Pair(ti,tj));
						}
					}
				}
			}
			//System.out.println(water);
			
		}
		
		if(exit) {
			System.out.println(turn-1);
		}else System.out.println("KAKTUS");
		
		
		
		
	}

	static class Pair{
		int i;
		int j;
		
		public Pair() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + "]";
		}
		
	}
}
