import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int info[][];
	static final int INF= 100_000*100_000+1;
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		/*
		 * 도시 A에서 도시 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램 만들기
		 */
		int n=Integer.parseInt(br.readLine())+1; //도시의 개수
		int m=Integer.parseInt(br.readLine()); //버스의 개수
		
		StringTokenizer st;
		info=new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i==j) info[i][j]=0;
				else info[i][j]=INF;
			}
		}
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			info[a][b]=Math.min(c, info[a][b]);
		}
		
		
		for(int k=1;k<n;k++) {
			for(int i=1;i<n;i++) {
				for(int j=1;j<n;j++) {
					
					if(info[i][k]!=INF && info[k][j]!=INF && info[i][j] > info[i][k] + info[k][j])
						info[i][j] =info[i][k]+info[k][j];
				}
			}
		}
		
		for(int i=1;i<n;i++) {
			for(int j=1;j<n;j++) {
				if(info[i][j]==INF) bw.write(0+" ");
				else bw.write(info[i][j]+" ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		
		
	}

}
