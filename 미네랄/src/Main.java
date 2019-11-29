import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static BufferedReader br;
	static int R,C;
	static int N;
	static char board[][];
	static boolean visited[][];
	static ArrayList<ArrayList<Pair>> color;
	static final int di[]= {-1,0,1,0};
	static final int dj[]= {0,-1,0,1};
	public static void main(String[] args) throws IOException{
		//br=new BufferedReader(new InputStreamReader(System.in));
		br=new BufferedReader(new FileReader("src/input.txt"));
		int height = 1;
		int dir = 0;
		color = new ArrayList<>();
		color.add(new ArrayList<>()); //coloring은 1부터 시작하기 때문에
		
		throwStick(height,0);
		
	}
	
	private static void throwStick(int height, int dir) {
		int j;
		if(dir==3) j=0;
		else if(dir==1) j=C-1;
		else {
			System.out.println("something wrong");
			return;
		}
	
		
		while(0<=j && j<C) {
			if(board[height][j]=='x') { //미네랄에 부딪히면
				board[height][j]='.'; //미네랄을 없애고
				//바닥부터 컬러링을 시작한다.
				coloring(); //컬러링을 할때 자기 노드를 arraylist가 가지고 있다.
				//바닥에서부터 위로 1칸씩 보면서 다른 색깔이 있으면 해당색깔을 옮긴다
				//위에 닿을때까지 계속한다.
				move();
				
			}
			j=j+dj[dir];
		}
		
		
	}

	private static void move() {
		// TODO Auto-generated method stub
		
	}
		
	private static void coloring() {
		
		ArrayList<Pair> ary = new ArrayList<>();
		Queue<Pair> queue = new LinkedList<>();
		boolean visited[][]=new boolean[R][C];
		for(int j=0;j<C;j++) {
			if(board[R-1][j]=='x') {
				queue.offer(new Pair(R-1,j));
				visited[R-1][j]=true;
			}
		}
		int colorNum=1;
		int ti,tj;
		
		while(!queue.isEmpty()) {
			Pair cur = queue.poll();
			for(int z=0;z<4;z++) {
				ti = cur.i+di[z];
				tj = cur.j+dj[z];
				if(0<=ti && ti<R && 0<=tj && tj<C) {
					
				}
			}
			
		}
		colorNum++;
		
	}
	
	static class Pair{
		int i;
		int j;
		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + "]";
		}
		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
	

}
