import java.util.*;
import java.io.*;

public class Main {
	static Scanner sc=new Scanner(System.in);
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] ary;
	static LinkedHashSet<String> lhs = new LinkedHashSet<>();
	
	public static void main(String[] args) throws IOException {
		int N=sc.nextInt();
		int M=sc.nextInt();
		
		ary=new int[N];
		for(int i=0;i<N;i++) {
			ary[i]=sc.nextInt();
		}
		
		Arrays.sort(ary);
		dfs(new int[M],0,0);
		
		for(String str: lhs) {
			bw.append(str);
			bw.append('\n');
		}
		bw.flush();
		
	}
	
	private static void dfs(int[] picked, int pi,int k) {
		if(pi == picked.length) {
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<picked.length;i++) {
				sb.append(picked[i]+" ");
			}
			lhs.add(sb.toString().trim());
			return;
		}
		
		for(int i=k;i<ary.length;i++) {
			picked[pi] = ary[i];
			dfs(picked,pi+1,i);
		}
		
	}

}
