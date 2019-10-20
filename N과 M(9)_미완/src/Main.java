import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
	static Scanner sc=new Scanner(System.in);
	static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] ary;
	static HashSet<int[]> hash=new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		int N= sc.nextInt();
		int M = sc.nextInt();

		ary=new int[N];
		for(int i=0;i<N;i++) {
			ary[i]=sc.nextInt();
		}
		
		Arrays.sort(ary);
		dfs(new int[M],0,new boolean[N],answer);
		
		for(int[] temp : hash) {
			System.out.println(Arrays.toString(temp));
		}
		bw.flush();
	}

	private static void dfs(int[] picked, int pi, boolean[] visited,) throws IOException {
		if(pi == M) {
			int []temp = new int[picked.length];
			for(int i=0;i<picked.length;i++) temp[i]=picked[i];
			hash.add(temp);
			
			return;
		}

		for(int i=0;i<ary.length;i++) {
			if(!visited[i]) {
				visited[i]=true;
				picked[pi] = (int) ary[i];
				dfs(picked,pi+1,visited,M);
				visited[i]=false;
			}
		}
	}

}
