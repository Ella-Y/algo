import java.io.*;
import java.util.*;

public class Main {
	static Scanner sc=new Scanner(System.in);
	static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] ary;
	static LinkedHashSet<String> hash=new LinkedHashSet<>();
	
	public static void main(String[] args) throws IOException {
		int N= sc.nextInt();
		int M = sc.nextInt();

		ary=new int[N];
		for(int i=0;i<N;i++) {
			ary[i]=sc.nextInt();
		}
		
		Arrays.sort(ary);
		dfs(new int[M],0,0,new boolean[N]);
		
		for (String string : hash) {
			bw.write(string+"\n");
		}
		
		bw.flush();
		
	}

	private static void dfs(int[] picked, int pi,int k, boolean[] visited) throws IOException {
		if(pi == picked.length) {
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<picked.length;i++) {
				sb.append(picked[i]+" ");
			}
			hash.add(sb.toString().trim());
			
			return;
		}

		for(int i=0;i<ary.length;i++) {
			//if(!visited[i]) {
			//	visited[i]=true;
				picked[pi] =  ary[i];
				dfs(picked,pi+1,i+1,visited);
			//	visited[i]=false;
			//}
		}
	}

}
