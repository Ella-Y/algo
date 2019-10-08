import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static int pa[];
	static int dis[];
	public static void main(String[] args) throws IOException {
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem(test);
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void problem(int test) throws IOException{
		int N=Integer.parseInt(br.readLine());
		pa=new int[N+1];
		dis=new int [N+1];
		for(int i=0;i<N+1;i++) pa[i]=i;
		
		while(true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String cmd=st.nextToken();
			/*System.out.println(cmd+":");*/
			if(cmd.equals("O")) break;
			else if(cmd.equals("E")) {
				bw.append(findSet(Integer.parseInt(st.nextToken())).dis+"\n");
				
			}else if(cmd.equals("I")) {
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				pair p1= findSet(a);
				pair p2=findSet(b);
				if(p1.parent !=p2.parent) {
					pa[p1.parent]=p2.parent;
					dis[p1.parent] = p2.dis + Math.abs(a-b)%1000;
				}
			}
			//System.out.println(Arrays.toString(pa));
			//System.out.println(Arrays.toString(dis));
		}
		
	}

	private static pair findSet(int i) {
		if(pa[i] == i) return new pair(i,dis[i]);
		pair cur = findSet(pa[i]);
		pa[i] = cur.parent;
		dis[i] +=cur.dis;
		return new pair(pa[i],dis[i]);
	}
	
	static class pair{
		int parent;
		int dis;
		public pair(int parent, int dis) {
			super();
			this.parent = parent;
			this.dis = dis;
		}
		@Override
		public String toString() {
			return "pair [parent=" + parent + ", dis=" + dis + "]";
		}
		
		
	}

}
