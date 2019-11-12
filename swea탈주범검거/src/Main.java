import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M; // 세로 가로
	static int R, C;
	static int L; // 탈출에 소요된시간.
	static int mmap[][];

	public static void main(String[] args) throws IOException {
		//br = new BufferedReader(new FileReader("input.txt"));
		int T = Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++){
			input();
			problem(test);	
		}
		
	}

	private static void input() throws IOException {
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());

		mmap = new int[N][M];

		for(int i=0;i<N;i++){
			stk = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++){
				mmap[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
	}

	public static void problem(int test) {
		// 탈주범은 R,C에서 시작한다.
		// L시간동안 움직이는데, 과연 어디까지 갈 수 있는지 체크한다.
		// L시간동안 움직이는데, 움직이다가 한 자리에서 계속 있을 수 있다.
		// 지하터널의 구조물 타입에 따라서 갈 수 있는 경우가 모두 다르다.
		// 구조물 경우의 가짓 수는 총 7개이다.

		Queue<Pair> queue = new LinkedList<Pair>();
		int answer = 0; // 처음에 있는 위치를 포함해야 한다.
		boolean visited[][] = new boolean[N][M];
		queue.offer(new Pair(R, C));
		visited[R][C]=true;
		int ti, tj;

		for (int time = 0; time < L; time++) {
			int size = queue.size();
			//System.out.println(queue);
			for (int p = 0; p < size; p++) {
				Pair cur = queue.poll(); // 탈주범
				answer++;
				int d = mmap[cur.i][cur.j];
				int di[] = dir[d][0];
				int dj[] = dir[d][1];

				for (int z = 0; z < di.length; z++) {
					ti = cur.i + di[z];
					tj = cur.j + dj[z];
					if (0 <= ti && ti < N && 0 <= tj && tj < M && !visited[ti][tj] && mmap[ti][tj] >= 1) {
						// 그런데 연결되어 있어야 갈 수 있다.
						int mazeNum = mmap[ti][tj]; // 갈 곳의 수도관 타입
						if (di[z] + dj[z] + dir[mazeNum][0][(z + 2) % di.length]
								+ dir[mazeNum][1][(z + 2) % di.length] == 0) {
							visited[ti][tj] = true;
							queue.offer(new Pair(ti, tj));
							//answer++;
						}

					}
				}

			}
		}

		System.out.println("#"+test+" "+answer);

	}

	static int dir[][][] = {
			// [터널 구조물 타입], di=[d][0], dj=[d][1]
			{}, { { -1, 0, 1, 0 }, { 0, -1, 0, 1 } }, // 1 상,좌,하,우 -> 어디든지 갈 수 잇음
			{ { -1, 0, 1, 0 }, { 0, 0, 0, 0 } }, // 2 상, 하 -> 4,5,
			{ { 0, 0, 0, 0 }, { 0, -1, 0, 1 } }, // 3 좌, 우
			{ { -1, 0, 0, 0 }, { 0, 0, 0, 1 } }, // 4 상 우
			{ { 0, 0, 1, 0 }, { 0, 0, 0, 1 } }, // 5 하,우
			{ { 0, 0, 1, 0 }, { 0, -1, 0, 0 } }, // 6 하 좌
			{ { -1, 0, 0, 0 }, { 0, -1, 0, 0 } }, // 7 상 좌
	};

	static class Pair {
		int i;
		int j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + "]";
		}
		
	}

}
