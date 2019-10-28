import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
	private static int M,N;
	private static int [][]mmap;
	public static void main(String[] args) throws IOException{
		StringTokenizer stk=new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		
		mmap=new int [N][];
		
		char in[];
		for(int i=0;i<N;i++) {
			in=br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				mmap[i][j]=in[j]-'0';
			}
		}
		
		//numbering
		int cnt=2;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(mmap[i][j]==1) {
					continue;
				}
				if(mmap[i][j] ==0) {
					numbering(cnt,i,j);
					cnt++;
				}
			}
		}
		
		
		
	}
	
	private static final int di[]= {-1,0,1,0};
	private static final int dj[]= {0,-1,0,1};
	
	private static void numbering(int cnt,int si,int sj) {
		Queue<Pair> q= new LinkedList<Pair>();
		q.offer(new Pair(si,sj));
		
		
		int i,j;
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int z=0;z<4;z++) {
				i=di[z]+cur.i;
				j=dj[z]+cur.j;
				
				if(0<=i && i<N && 0<=j && j<M && mmap[i][j]==0) {
					mmap[i][j]=cnt;
					q.offer(new Pair(i,j));
				}
			}
		}
		
		
	}
	
	static class Pair{
		int i;
		int j;
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
