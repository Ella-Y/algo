import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int info[][];
	static final int  INF= 10*1000+20;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<pair>> edges=new ArrayList<>();
		
		
		info = new int[N][N];
		for(int i=0;i<N;i++) {
			edges.add(new ArrayList<>());
			for(int j=0;j<N;j++) {
				if(i==j) info[i][j]=0;
				else info[i][j]=INF;
			}
		}

		
		
	}
	
	static class pair{
		int v1;
		int v2;
		
		@Override
		public String toString() {
			return "pair [v1=" + v1 + ", v2=" + v2 + "]";
		}


		public pair(int v1, int v2) {
			super();
			this.v1 = v1;
			this.v2 = v2;
		}
		
	}
	
}
