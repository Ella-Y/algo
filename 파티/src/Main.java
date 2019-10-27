import java.io.*;
import java.util.*;

/**
 * 백준 1238 파티
 * 플로이드 와샬 알고리즘
 * @author Ella-Y
 */
public class Main {

	private static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	private static int ary[][];
	private static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		StringTokenizer stk=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		int X = Integer.parseInt(stk.nextToken())-1;
		
		ary=new int[N][N];
		
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(i==j) ary[i][j] =0;
				else
					ary[i][j]=INF;
		}}
		
		int a,b,c;
		for(int i=0;i<M;i++){
			stk=new StringTokenizer(br.readLine());
			a=Integer.parseInt(stk.nextToken());
			b=Integer.parseInt(stk.nextToken());
			c=Integer.parseInt(stk.nextToken());
			ary[a-1][b-1]=c;
		}
		
		//floyd
		
		for(int mid=0;mid<N;mid++){
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					if(ary[i][mid]==INF || ary[mid][j]==INF) continue;
					if(ary[i][j] > ary[i][mid]+ary[mid][j]){
						ary[i][j] = ary[i][mid]+ary[mid][j];
					}
		}}}
		
		//house -> X ->house
		//find max
		int max=0;
		int temp=0;
		for(int i=0;i<N;i++){
			temp = ary[i][X] + ary[X][i];
			max = temp>max?temp:max;
		}
		System.out.println(max);
		
	}

}
