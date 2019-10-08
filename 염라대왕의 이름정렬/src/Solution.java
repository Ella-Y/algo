import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Solution {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem(test);
		}
		bw.flush();
		bw.close();
	}
	private static void problem(int test) throws IOException {
		int N=Integer.parseInt(br.readLine());
		HashSet<String> names=new HashSet<>();
		//String [] names=new String[N];
		for(int i=0;i<N;i++) {
			//names[i]=br.readLine();
			names.add(br.readLine());
		}
		
		String[] ob=new String[names.size()];
		names.toArray(ob);
		
		Arrays.sort(ob,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				else if(o1.length()>o2.length()) return 11;
				else return -1;
				
			}
			
		});
		
		bw.write("#"+test+"\n");
		for(String n: ob) {
			bw.write(n+"\n");
		}
		
		
		
		
	}
	
}
