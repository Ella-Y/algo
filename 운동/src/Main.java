import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int info[][];
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		StringTokenizer st=new StringTokenizer(br.readLine());
		int V=Integer.parseInt(st.nextToken())+1;
		int E=Integer.parseInt(st.nextToken());
		
		info=new int[V][V];
		for(int i=0;i<V;i++) {
			for(int j=0;j<V;j++) {
				if(i==j) info[i][j]=0;
				else info[i][j]=INF;
			}
		}
		
		int a,b,c;
		for(int i=0;i<E;i++) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			info[a][b]=c;
		}
		
		for(int k=1;k<V;k++) {
			for(int i=1;i<V;i++) {
				for(int j=1;j<V;j++) {
					
					if(info[i][k]!=INF && info[k][j]!=INF && info[i][j] > info[i][k]+info[k][j]) {
						info[i][j] =info[i][k]+info[k][j];
					}
				}
			}
		}
		
		/*//test
		   for(int i=1;i<V;i++) {
			for(int j=1;j<V;j++) {
				if(info[i][j]==INF) System.out.print("INF ");
				else System.out.print(info[i][j]+" ");
			}
			System.out.println();
		}*/
		
		int ans=INF;
		int temp;
		for(int i=1;i<V;i++) {
			for(int j=1;j<V;j++) {
				if(i==j) continue;
				if((info[i][j]!=INF)&&(info[j][i]!=INF)) {
					temp=info[i][j] + info[j][i];
					ans=Math.min(temp, ans);
				}
						
			}
		}
		
		System.out.println(ans==INF?-1:ans);
		
		
		
	}
	
}
