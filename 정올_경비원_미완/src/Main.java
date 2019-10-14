import java.io.*;
import java.util.*;
public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<pair> ary;
	static pair start;
	public static void main(String[] args) throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		int I = Integer.parseInt(st.nextToken());
		int J = Integer.parseInt(st.nextToken());
		ary= new ArrayList<>();
		int cnt = Integer.parseInt(br.readLine());
		
		int a,b;
		
		for(int i=0;i<cnt;i++) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			if(a==1) a=0;
			else if(a==2) a=I;
			else if(a==3) {
				a=b;
				b=0;
			}else if(a==4) {
				a=b;
				b=J;
			}
			ary.add(new pair(a,b));
		}
		
		st=new StringTokenizer(br.readLine());
		a=Integer.parseInt(st.nextToken());
		b=Integer.parseInt(st.nextToken());
		if(a==1) a=0;
		else if(a==2) a=I;
		else if(a==3) {
			a=b;
			b=0;
		}else if(a==4) {
			a=b;
			b=J;
		}
		start= new pair(a,b);
		
		
		
		
		
		
	}
	
	

	static class pair{
		int i;
		int j;

		public pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
		@Override
		public String toString() {
			return "pair [i=" + i + ", j=" + j + "]";
		}
		
	}

}
