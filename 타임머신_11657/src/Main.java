import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static ArrayList<pair> edge= new ArrayList<>();
	static int d[];
	static final int INF = 6000*10000+1;
	public static void main(String[] args) throws IOException{
		/*
		 * 가장 빠른 시간 구하기
		 * 무한히 오래 전으로 돌아갈 수 있으면-> 음의 사이클이 존재하면, -1
		 * 경로가 없으면 -> -1
		 */

		StringTokenizer st=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			edge.add(new pair(a,b,c));
		}
		
		d=new int[N+1];
		
		for(int i=0;i<=N;i++) {
			d[i] = INF;
		}
		
		d[1] =0;

		for(int i=1;i<=N-1;i++) {
			for(int j=0;j<M;j++) { //모든 edge에 대하여
				int u = edge.get(j).src;
				int v = edge.get(j).dest;
				int w = edge.get(j).w;
				if(d[u]!=INF && d[v] > d[u] + w) {
					d[v] = d[u]+w;
				}
			}
		}
		
		int answer[]=new int[N+1];
		for(int i=0;i<answer.length;i++) {
			answer[i] = d[i];
		}
		
		for(int i=0;i<M;i++) {
			int u = edge.get(i).src;
			int v = edge.get(i).dest;
			int w = edge.get(i).w;
			if(d[u]!=INF && d[v]>d[u]+w) {
				//음의 사이클이 존재
				System.out.println(-1);
				return;
			}
		}
		
		for(int i=2;i<=N;i++) {
			if(d[i]==INF)
				System.out.println(-1);
			else System.out.println(d[i]);
		}
		

	}

	static class pair{
		int src;
		int dest;
		int w;
		public pair(int src, int dest, int w) {
			super();
			this.src = src;
			this.dest = dest;
			this.w = w;
		}
		@Override
		public String toString() {
			return "pair [src=" + src + ", dest=" + dest + ", w=" + w + "]";
		}



	}

}
