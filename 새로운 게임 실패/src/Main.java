import java.util.*;
import java.io.*;


public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static final int BLUE = 2;
	static final int RED = 1;
	static final int WHITE =0;
	static int board[][];
	static int cnt[][]; //쌓인 말들의 수
	static Pin pins[][][]; //board[][] + front,end
	static boolean alive[];
	static int ip[]; //말들의 위치
	static int jp[]; //말들의 위치
	static boolean stackTrace=false; //4개 이상 쌓이면 true

	public static void main(String[] args) throws IOException{
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());

		board=new int[N][N];
		cnt = new int [N][N];
		pins=new Pin[N][N][2];
		alive=new boolean[K];
		ip = new int[K];
		jp = new int[K];
		Arrays.fill(alive, true);

		for(int i=0;i<N;i++) {
			stk= new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(stk.nextToken());
				pins[i][j]=new Pin[2];
			}
		}

		//행, 열, 모두 -1/ 이동번호 는 다르게
		int row,col,dis;
		for(int i=0;i<K;i++) {

			stk= new StringTokenizer(br.readLine());
			row = Integer.parseInt(stk.nextToken())-1;
			col = Integer.parseInt(stk.nextToken())-1;
			dis = Integer.parseInt(stk.nextToken());

			if(dis==1) dis=3;
			else if(dis==2) dis=1;
			else if(dis==3) dis=0;
			else dis=2;
			pins[row][col][0] = new Pin(i,dis);
			pins[row][col][1] = new Pin(i,dis);
			ip[i]=row;
			jp[i]=col;
			cnt[row][col]=1; //처음에는 한 개

		}
		int turn=1;
		for(;turn<=1000;turn++) {
			for(int i=0;i<K;i++) {
				if(alive[i])
					move(i, ip[i],jp[i]);
			}
			if(stackTrace) break;
		}

		if(stackTrace) System.out.println(turn);
		else System.out.println(-1);
	}

	public static void move(int idx, int i,int j) {
		//i,j위치에 있는 말이 이동한다.
		Pin p = pins[i][j][1]; //end
		int dir= p.dir;

		//다음에 이동할 색깔 가져오기
		int ni,nj;
		ni = i+di[dir];
		nj = j+dj[dir];
		int color = getColor(i,j,dir);
		Pin[] next;


		switch(color) {
		case WHITE:
			stackInWhite(idx, i, j, dir);
			break;
		case RED:
			stackInRed(idx, i, j, dir);
			break;
		case BLUE:
			ni=i+di[dir]; nj=j+dj[dir];
			//한 칸 이동하기 전에 다음 위치에서 어떻게 움직이는지 알아야 한다.
			color = getColor(ni,nj,dir);
			p.dir=(p.dir+2)%4;
			if (color== WHITE){
				stackInWhite(idx, i,j,p.dir);
			}else {
				stackInRed(idx, i,j,p.dir);
			}
			break;
		}

	}
	//인덱스 추가

	private static void stackInWhite(int idx, int i, int j, int dir) {
		Pin[] next;
		Pin[] cur = pins[i][j];
		//다음 위치 핀 확인
		next = pins[i+di[dir]][j+dj[dir]];
		if(cnt[i+di[dir]][j+dj[dir]]==0) { //해당 위치에 말이 없음
			cnt[i+di[dir]][j+dj[dir]] = cnt[i][j];

			next[0] = new Pin(cur[0]);
			next[1] = new Pin(cur[1]);
			
		}else { //해당 위치에 말이 있음
			cnt[i+di[dir]][j+dj[dir]]+=cnt[i][j];
			if(cnt[i+di[dir]][j+dj[dir]] >=4) stackTrace=true;
			
			//새로운 말이 합쳐진다.
			alive[cur[0].idx]=false;
			alive[cur[1].idx]=false;
			alive[next[0].idx]= false;
			alive[next[1].idx]=false;
			
			next[0] = new Pin(cur[0]);
			alive[next[1].idx]=true;

		}

		//위치 업데이트
		for(int k=0;k<2;k++) {
			ip[cur[k].idx]=ip[next[k].idx]=i+di[dir];
			jp[cur[k].idx]=jp[next[k].idx]=j+dj[dir];
		}

		//이전 위치에 있던 내용 삭제

		cnt[i][j]=0;
		pins[i][j] = new Pin[2];
	}

	private static void stackInRed(int idx, int i, int j, int dir) {
		Pin[] next;
		Pin[] cur = pins[i][j];

		next = pins[i+di[dir]][j+dj[dir]];
		if(cnt[i+di[dir]][j+dj[dir]]==0) { //해당 위치에 말이 없음
			cnt[i+di[dir]][j+dj[dir]] = cnt[i][j];

			next[0] = new Pin(cur[1]);
			next[1] = new Pin(cur[0]);
			
		}else { //해당 위치에 말이 있음
			cnt[i+di[dir]][j+dj[dir]]+=cnt[i][j];
			if(cnt[i+di[dir]][j+dj[dir]] >=4) stackTrace=true;

			//새로운 말이 합쳐진다.
			alive[cur[0].idx]=false;
			alive[cur[1].idx]=false;
			alive[next[0].idx]= false;
			alive[next[1].idx]=false;

			next[0]= new Pin(cur[1]);
			//next[1]은 유지
			alive[next[1].idx]=true;
		}
		
		//위치 업데이트
		for(int k=0;k<2;k++) {
			ip[cur[k].idx]=ip[next[k].idx]=i+di[dir];
			jp[cur[k].idx]=jp[next[k].idx]=j+dj[dir];
		}
		
		//이전 위치에 있던 내용 삭제
		cnt[i][j]=0;
		pins[i][j]=new Pin[2];
	}

	static final int di[]= {-1,0,1,0};
	static final int dj[]= {0,-1,0,1};	

	private static int getColor(int i,int j, int dir) {
		i=i+di[dir];
		j=j+dj[dir];
		if(0<=i && i<board.length && 0<=j && j<board.length) {
			return board[i][j];
		}
		return BLUE; //범위를 벗어나면 파란색
	}

	static class Pin{
		int idx;
		int dir;

		public Pin() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Pin(Pin p) {
			this.idx=p.idx;
			this.dir= p.dir;

		}
		public Pin(int idx, int dir) {
			super();
			this.idx = idx;
			this.dir = dir;

		}

		@Override
		public String toString() {
			return "Pin [idx=" + idx + ", dir=" + dir + "]";
		}
	}

}
