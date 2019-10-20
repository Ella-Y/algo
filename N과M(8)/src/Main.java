import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static Scanner sc=new Scanner(System.in);
	static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
	static int ary[];
	public static void main(String[] args) throws IOException {
		int N= sc.nextInt();
		int M = sc.nextInt();
		
		ary=new int[N];
		for(int i=0;i<N;i++) {
			ary[i]=sc.nextInt();
		}
		
		Arrays.sort(ary);
		
		dfs(new int[M],0,0,M);
		bw.flush();
	}
	
	private static void dfs(int[] picked, int pi, int k , final int M) throws IOException {
		if(pi == M) {
			for(int i=0;i<picked.length;i++) {
				bw.write(picked[i]+" ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=k;i<ary.length;i++) {
				picked[pi] = ary[i];
				dfs(picked,pi+1,i,M);
				
		}
	}

}
