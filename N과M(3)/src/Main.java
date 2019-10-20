import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
	static Scanner sc=new Scanner(System.in);
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static int ary[];
	public static void main(String[] args) throws IOException {
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		ary=new int[N];
		for(int i=0;i<N;i++) {
			ary[i]=i+1;
		}
		//System.out.println(Arrays.toString(ary));
		
		dfs(new int [M],0,0,M);
		bw.flush();
	}
	
	private static void dfs(int[] picked, int pi, int k, final int M) throws IOException {
		if(M == pi) {
			for(int i=0;i<pi;i++) {
				//System.out.print(picked[i]+" ");
				bw.append(picked[i]+" ");
			}
			bw.append("\n");
			//System.out.println();
			return;
		}
		
		for(int i=0;i<ary.length;i++) {
			picked[pi]=ary[i];
			dfs(picked,pi+1,i+1,M);
		}
		
		
	}

}
