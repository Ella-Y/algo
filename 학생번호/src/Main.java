import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException{
		int N= Integer.parseInt(br.readLine());
		String ary[]=new String[N];
		
		for(int i=0;i<N;i++) {
			ary[i]=br.readLine();
		}
		
		HashSet<String> set=new HashSet<>();
		
		for(int k=ary[0].length()-1;k>=0;k--) {
			set.clear();
			for(int i=0;i<N;i++) {
				set.add(ary[i].substring(k, ary[0].length()));
			}
			//System.out.println(set);
			if(set.size() == N) {
				System.out.println(ary[0].length()-k);
				break;
			}
		}
		
		
		
	}

}
