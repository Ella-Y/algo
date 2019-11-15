import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M; //세로, 가로
	static boolean keys[]; //a,b,c,d,e,f
	static char mmap[][];
	static boolean visited[][][]; //[열쇠의 타입][i][j]
	static int di[]={-1,0,1,0};
	static int dj[]={0,-1,0,1};
	static Pair start;
	static BufferedReader br;

	public static void main(String[] args) throws Exception {
		//여행길을 떠나기 위해 미로를 탈출하려고 한다.
		//# : 이동불가 , ABCDEF 열쇠가 있어야 갈 수 있음
		//. ,abcdef : 열쇠
		//탈출하는 곳은 여러 곳일 수도 있다.
		//열쇠를 얻어서 길을 돌아갈 수도 있다.
		input();

		int cnt = problem();

		System.out.println(cnt);

	}

	private static int problem() {
		Queue<Pair>queue=new LinkedList<>();
		queue.offer(start);
		visited[0][start.i][start.j]=true;
		int cnt=0;
		int ti,tj,nkey;
		Pair cur;

		outer:while(!queue.isEmpty()) {
			int size=queue.size();
			//System.out.println(queue);
			for(int s=0;s<size;s++) {
				cur = queue.poll();
				if(mmap[cur.i][cur.j]=='1') {
					return cnt;
				}
				for(int z=0;z<4;z++) {
					ti = cur.i+di[z];
					tj = cur.j+dj[z];
					//열쇠를 가지고 해당위치를 방문하지 않았다면...
					if(0<=ti && ti<N && 0<=tj && tj<M && mmap[ti][tj]!='#' && !visited[cur.key][ti][tj]) {
						if('A'<=mmap[ti][tj] && mmap[ti][tj]<='F') {
							//문일 경우에는 나한테 해당 키가 있는지 살펴본다.
							int temp = 1<<(mmap[ti][tj]-'A');
							
							if( (cur.key & (temp)) == temp ) {
								//열쇠가 있다.
								visited[cur.key][ti][tj]=true;
								queue.offer(new Pair(ti,tj,cur.key));
							}
							
						}
						else if('a'<= mmap[ti][tj] && mmap[ti][tj] <='f') {
							nkey = cur.key | (1<<(mmap[ti][tj]-'a'));
							
							visited[cur.key][ti][tj]=true; //현재 위치에서 의 방문체크
							visited[nkey][ti][tj] = true; //새로운 키를 가지고 방문체크
							queue.offer(new Pair(ti,tj,nkey));
						}
						else { //열쇠도 아니고 문도 아니고 그냥 갈 수 있는 경우
							visited[cur.key][ti][tj] = true;
							queue.offer(new Pair(ti,tj,cur.key));
						}
					}
				}//end forz


			}
			cnt++;
		}
		
		return -1;
	}

	private static void input() throws Exception{
		//br=new BufferedReader(new FileReader("src/input.txt"));
		br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		mmap=new char[N][];
		keys=new boolean[8];

		visited=new boolean[(1<<6)][N][M];

		for(int i=0;i<N;i++) {
			mmap[i]=br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				if(mmap[i][j]=='0') {
					start = new Pair(i,j,0);
				}
			}
		}
		//print();
	}

	private static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(mmap[i][j]);
			}
			System.out.println();
		}
	}

	static class Pair{
		int i;
		int j;
		int key;
		public Pair(int i, int j, int key) {
			super();
			this.i = i;
			this.j = j;
			this.key = key;
		}
		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + ", key=" + key + "]";
		}
		

	}



}