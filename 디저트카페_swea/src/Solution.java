import java.io.*;
import java.util.*;

public class Solution {
	private static BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem(test);
		}
	}
	private static int [][] ary;
	
	private static void problem(int test) throws IOException{
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		ary=new int [N][N];
		answer = -1;
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				ary[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				set.clear();
				//set.add(ary[i][j]);
				//LinkedList<Pair> link=new LinkedList<>();
				//link.add(new Pair(i,j));
				dfs(i,j,i,j,0/*,link*/);
			}
		}
		
		System.out.println("#"+test+" "+answer);
		
	}
	
	private static int di[]= {1,1,-1,-1};
	private static int dj[]= {-1,1,1,-1};
	private static int answer;
	private static HashSet<Integer> set=new HashSet<>();
	
	private static void dfs(int si, int sj, int i, int j, int d/*, LinkedList<Pair> link*/) {
		
		int N = ary.length;
		
		if(si==i && sj==j && set.size() >= 4) {
			answer = Math.max(answer, set.size());
			return;
		}
		
		int ti,tj;
		
		for(int k=d;k<4;k++) {
			ti=i+di[k];
			tj=j+dj[k];
			
			if(0<=ti && ti<N && 0<=tj && tj<N && !set.contains(ary[ti][tj])) {
				set.add(ary[ti][tj]);
				/*link.add(new Pair(ti,tj));*/
				/*System.out.println(link);*/
				dfs(si,sj,ti,tj,k/*,link*/);
				set.remove(ary[ti][tj]);
				/*link.pollLast();*/
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
