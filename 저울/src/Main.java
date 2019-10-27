import java.io.*;
import java.util.*;


/**
 * 백준 10159 저울
 *
 */
public class Main {
	private static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	private static int INF = 2000*100+1;
	private static int answer[];
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int ary1[][];
		ary1=new int[N][N];
		answer= new int[N];
		for(int i=0;i<N;i++) {
			answer[i]=N-1;
		}
		
		StringTokenizer stk=null;
		int a,b;
		for(int i=0;i<M;i++){
			stk= new StringTokenizer(br.readLine());
			a=Integer.parseInt(stk.nextToken())-1;
			b=Integer.parseInt(stk.nextToken())-1;
			ary1[a][b]=1;
		}
		
		floyd(ary1);
		//print(ary1);
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i==j) continue;
				
				if(ary1[i][j]==0 && ary1[j][i]>=1) { //경로가 없다 그런데 j -> i로 가는 경로가 있으면 반대도 있겠지..
					ary1[i][j] = ary1[j][i];
				}
				
				if(ary1[i][j]>=1) answer[i]--; //그래서 결국 경로를 가지게 된다면 무언가랑 비교할 수 있다는 뜻이 됨.
				
			}
		}
		
		
		for(int i=0;i<N;i++){
			bw.write(answer[i]+"\n");
		}
		bw.close();
	}
	
	private static void floyd(int[][] ary){
		for(int mid = 0;mid<ary.length;mid++){
			for(int i=0;i<ary.length;i++){
				for(int j=0;j<ary.length;j++){
					if(ary[i][mid] >=1 && ary[mid][j]>=1) {
						ary[i][j]=ary[i][mid]+ary[mid][j];
					}
				}}}
		
	}
	
	private static void print(int [][] ary) {
		for(int i=0;i<ary.length;i++) {
			for(int j=0;j<ary.length;j++) {
				if(ary[i][j]==INF) System.out.print("I ");
				else System.out.print(ary[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
}
