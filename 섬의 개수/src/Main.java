import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int di[]= {-1,-1,-1,0,0,1,1,1};
	static int dj[]= {-1,0,1,-1,1,-1,0,1};
	static int mmap[][];
	static boolean visited[][];
	static Queue<pair> q=new LinkedList<>();
	static int w=1;
	static int h=1;
	public static void main(String[] args) throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		w=Integer.parseInt(st.nextToken());
		h=Integer.parseInt(st.nextToken());
		int answer=0;
		do {
			input();
			answer=problem();
			System.out.println(answer);
			st=new StringTokenizer(br.readLine());
			w=Integer.parseInt(st.nextToken());
			h=Integer.parseInt(st.nextToken());
		}while(w!=0 && h!=0);
	}
	
	private static int problem() {
		int answer=0;
		if(w==1 && h==1) return mmap[0][0]==1?1:0;
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(q.isEmpty()) {
					if(mmap[i][j]==1 && !visited[i][j]) {
						visited[i][j]=true;
						q.offer(new pair(i,j));
						answer+=1;
					}else continue;
				}
				while(!q.isEmpty()) {
					go();
				}
			}
		}
		return answer;
	}
	
	private static void go() {
		pair cur=q.poll();
		for(int k=0;k<8;k++) {
			int j=cur.j+dj[k];
			int i=cur.i+di[k];
			if(0<=j && j<w && 0<=i && i<h) {
				if(mmap[i][j]==1 && !visited[i][j]) {
					visited[i][j]=true;
					q.offer(new pair(i,j));
				}
			}
			
		}
		
		
	}

	private static void input() throws IOException{
		mmap=new int [50][50];
		visited=new boolean [50][50];
		for(int i=0;i<h;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<w;j++) {
				mmap[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		q.clear();
		
	}
	
	static class pair{
		int i;
		int j;
		
		public pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
}
