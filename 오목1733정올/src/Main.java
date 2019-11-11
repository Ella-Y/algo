import java.util.Scanner;

public class Main 
{
	public static Scanner sc= new Scanner(System.in); 
	public static final int N = 19;	
	public static int[][] ary;
	public static int ti, tj;
	public static int answer;
	public static boolean[][] visited;
	public static boolean pass = false;
	public static int[] di = {0, -1, 1, -1 ,1, -1, 1, 0, 0 };
	public static int[] dj = {0, -1, 1, 0, 0, 1, -1, 1, -1 };
	
	public static void main(String[] args){
		ary=new int[N][N];
		visited=new boolean[N][N];
		answer = 0;
		for(int i = 0 ; i < N ; i++){
			for(int j=0; j<N; j++){
				ary[i][j] = sc.nextInt();
			}
		}
		
		logic();
		if(answer == 0){
			System.out.println(answer);
			return;
		}
		
		System.out.println(answer);
		
		for(int i=0;i<N; i++){
			for(int j=0; j<N; j++){
				if(ary[j][i] == 3){
					System.out.println((j+1) + " " + (i+1));
					return;
				}
			}
		}
	}
	


	
	private static void logic() {
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				
				if(ary[i][j] == 0) continue;
				
				for(int dir = 1; dir < di.length; dir=dir+2){
					int sum = 0;
					for(int k = dir ; k <= dir+1 ; k++){
						sum += dfs(i,j,k,ary[i][j]);
					}
					
					if(sum == 6){
						answer = ary[i][j];
						
						for(int d = dir ; d <= dir+1 ; d++){
							dfs2(i,j,d,answer);
						}
						return;
					}
					
				}
			}
		}
	}
	
	private static void dfs2(int i, int j, int dir, int val) {
		int ti = di[dir] + i;
		int tj = dj[dir] + j;
		ary[i][j]= 3;
		
		if(0 <= ti && ti < N && 0 <= tj && tj < N && val == ary[ti][tj]){
			dfs2(ti,tj,dir,val);
		}
		
	}
	
	private static int dfs(int i, int j, int dir, int val){
		int sum = 1;
		int ti = di[dir] + i;
		int tj = dj[dir] + j;
		
		if(0 <= ti && ti < N && 0 <= tj && tj < N && val == ary[ti][tj]){
			sum += dfs(ti,tj,dir,val);
		}
		
		return sum;
	}
	
	
}