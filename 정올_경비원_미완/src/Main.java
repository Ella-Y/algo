import java.io.*;
import java.util.*;
public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<point> ary;
	static point s;
	static int H,W;
	public static void main(String[] args) throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(br.readLine());
		int a,b;
		ary =new ArrayList<>();
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			ary.add(getP(a,b));
		}
		
		st=new StringTokenizer(br.readLine());
		a=Integer.parseInt(st.nextToken());
		b=Integer.parseInt(st.nextToken());
		s=getP(a,b);
		
		int ans=0;
		int temp = 0;
		for(point p: ary) {
			temp =Math.min(s.x+p.x+s.y+p.y, (W-s.x)+s.y+(W-p.x)+p.y);
			if(s.x == p.x || s.y == p.y) {
				if(s.x == p.x) temp = Math.min(Math.abs(s.y - p.y), temp);
				else temp = Math.min(Math.abs(s.x - p.x), temp);
			}
			ans += temp;
			System.out.println((s.x+p.x+s.y+p.y)+" "+((W-s.x)+s.y+(W-p.x)+p.y));
			
		}
		System.out.println(ans);
	}
	
	

	private static point getP(int dir, int n) {
		if(dir==1) return new point(n,H);
		else if(dir==2) return new point(n,0);
		else if(dir==3) return new point(0,H-n);
		else return new point(W,H-n);
	}



	static class point{
		int x;
		int y;
		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + "]";
		}
		
		
		
	}

}
