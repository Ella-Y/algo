import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int W;
	static int H;
	static int board[][];
	static int answer;
	public static void main(String[] args) throws IOException{
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem(test);
		}

	}

	private static void problem(int test) throws IOException{
		answer=Integer.MAX_VALUE;
		input();
		shoot(0);


		System.out.println("#"+test+" "+answer);
	}

	private static void input() throws IOException{
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N=Integer.parseInt(stk.nextToken());
		W=Integer.parseInt(stk.nextToken());
		H=Integer.parseInt(stk.nextToken());

		board = new int [H][W];
		for(int i=0;i<H;i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				board[i][j]=Integer.parseInt(stk.nextToken());
			}
		}

	}

	private static void shoot(int cnt) {
		if(cnt==N) { //다 쏘았다.
			//print();

			int cc=0;
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(board[i][j]>=1) {
						cc++;
					}
				}
			}
			answer = answer<cc?answer:cc;
			return;
		}

		int copyBoard[][]=new int[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				copyBoard[i][j]=board[i][j];
			}
		}

		//구슬을 쏜다.
		boolean flag=false;
		nextLine:for(int j=0;j<W;j++) {
			for(int i=0;i<H;i++) {
				if(board[i][j]>=1) {
					flag=true;
					//System.out.println("shoot:"+i+" "+j);
					bomb(i,j); //board[i][j]구슬을 터트리자
					down();//구슬을 내리자
					shoot(cnt+1);//또 쏘자
					//구슬 복원
					recover(copyBoard);
					continue nextLine;
				}
			}
		}

		if(!flag) {
			//더 이상 쏠 수 없는 경우를 위함
			int cc=0;
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(board[i][j]>=1) {
						cc++;
					}
				}
			}
			answer = answer<cc?answer:cc;
		}


	}

	private static void print() {
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("--------");
	}

	private static void down() {
		int resultBoard[][]=new int[H][W];
		LinkedList<Integer> ary = new LinkedList<Integer>();
		for(int j=0;j<W;j++) {
			for(int i=0;i<H;i++) {
				if(board[i][j]>=1) {
					ary.add(board[i][j]);
				}
			}
			for(int i=H-1;!ary.isEmpty();i--) {
				resultBoard[i][j]=ary.pollLast();
			}
		}
		board = resultBoard;
	}

	private static void recover(int[][] copyBoard) {
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				board[i][j]=copyBoard[i][j];
			}
		}

	}



	static final int di[]= {1, -1,0,0};
	static final int dj[]= {0, 0,-1,1};
	private static void bomb(int si, int sj) {
		boolean visited[][]=new boolean[H][W];
		//구슬을 터트린다.
		Queue<Pair> queue=new LinkedList<>();

		Pair start = new Pair(si,sj,board[si][sj]); 
		queue.offer(start);
		//bombAry.add(start);
		board[si][sj]=0;
		visited[si][sj]=true;

		while(!queue.isEmpty()) {
			final Pair cur= queue.poll(); //구슬이 터진 그 위치에서
			int cnt = cur.val-1;
			//위쪽, 오른쪽, 왼쪽, 아래 가면 됨.
			for(int z=0;z<4;z++) {
				int ti = cur.i, tj=cur.j;
				for(int c=0;c<cnt;c++) {
					ti = ti+di[z];
					tj = tj+dj[z];
					if(0<=ti && ti<H && 0<=tj && tj<W
							&& !visited[ti][tj] && board[ti][tj]>=1) {
						Pair p = new Pair(ti,tj,board[ti][tj]);
						visited[ti][tj]=true;
						//bombAry.add(p); //터지는 구슬들
						if(board[ti][tj]>=2) { //2 이상이면은 연쇄작용이 일어남
							queue.offer(p);
						}
						board[ti][tj]=0;
					}
				}
			}
		}
		//System.out.println("----bfs--");
		//print();
		//System.out.println("------");
	}

	static class Pair{
		int i;
		int j;
		int val; //그때 당시 값.

		public Pair(int i, int j, int val) {
			super();
			this.i = i;
			this.j = j;
			this.val = val;
		}


	}
}
