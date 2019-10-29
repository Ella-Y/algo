import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		StringTokenizer stk;
		int L,R,C;
		while(true) {
			stk=new StringTokenizer(br.readLine());
			L=Integer.parseInt(stk.nextToken());
			R=Integer.parseInt(stk.nextToken());
			C=Integer.parseInt(stk.nextToken());
			if(L+R+C==0) break;
			problem(L,R,C);
		}
		
	}
	private static char building[][][];
	private static boolean visited[][][];
	private static int dl[]= {-1,1};
	private static int di[]= {-1,0,1,0};
	private static int dj[]= {0,-1,0,1};
	
	private static void problem(int L, int R, int C) throws IOException {
		
		building=new char [L][R][C];
		visited=new boolean [L][R][C];
		
		Crd start,fin;
		start=fin=null;
		
		for(int l=0;l<L;l++) {
			for(int r=0;r<R;r++) {
				char in[]=br.readLine().toCharArray();
				for(int c=0;c<C;c++) {
					building[l][r][c]=in[c];
					if(in[c]=='S') {
						start=new Crd(l,r,c,0);
					}
					else if(in[c]=='E') {
						building[l][r][c]='.';
						fin=new Crd(l,r,c,0);
					}
					else if(in[c]=='#') {
						visited[l][r][c]= true;
					}
				}
			}
			br.readLine();
		}
		
		
		Queue<Crd> q=new LinkedList<>();
		
		q.offer(start);
		
		Crd cur;
		int answer=0;
		while(!q.isEmpty()) {
			cur = q.poll();
			//System.out.println(cur);
			if(cur.l == fin.l && cur.r == fin.r && cur.c==fin.c) {
				answer = cur.pass;
				break;
			}
			
			//아래로 내려갈 수 있거나 위로 올라갈 수 있으면
			for(int k=0;k<2;k++) {
				int tl=cur.l+dl[k];
				if(0<=tl && tl<L && !visited[tl][cur.r][cur.c]&& building[tl][cur.r][cur.c]=='.' ) {
					visited[tl][cur.r][cur.c]=true;
					q.offer(new Crd(tl,cur.r,cur.c,cur.pass+1));
				}
			}
			
			//4방향탐색
			for(int k=0;k<4;k++) {
				int tr=cur.r+di[k];
				int tc=cur.c+dj[k];
				if(0<=tr&&tr<R && 0<=tc && tc<C && !visited[cur.l][tr][tc]
						&& building[cur.l][tr][tc]=='.') {
					visited[cur.l][tr][tc]=true;
					q.offer(new Crd(cur.l,tr,tc,cur.pass+1));
				}
			}
			
		}
		
		if(answer==0) System.out.println("Trapped!");
		else System.out.println("Escaped in "+answer+" minute(s).");
		
		
	}
	
	
	
	static class Crd{
		int l;
		int r;
		int c;
		int pass;
		
		public Crd(int l, int r, int c, int pass) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
			this.pass = pass;
		}
		public Crd(int l, int r, int c) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Crd [l=" + l + ", r=" + r + ", c=" + c + ", pass=" + pass + "]";
		}
		
	}

}
