import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int M;
	static int T;
	static int info[][]; //x,d,k //x의배수인 원판을 d방향으로 k칸으로 회전시킨다.
	static int ary[][];
	
	public static void main(String[] args) throws IOException{
		input();
		/*for(int t=0;t<T;t++) {
			rotate(info[t]);
			erase();
		}*/
		rotate(0,0,2);
		print();
		
	}

	private static void rotate(int is[]) {
		int x=is[0];
		int d=is[1];
		int k=is[2];
		
		//번호가 x의 배수인 원판을 d방향으로 k칸 회전시킨다.
		for(int i=x;i<=ary.length;i=i+x) {
			//rotate(ary[i-1],d,k);
		}
		
		
		
		
	}

	private static void rotate(int aryIdx, int d, int k) {
		//1차원 배열을 d방향으로 k칸 회전시킨다.
		int is[]=ary[aryIdx];
		int tempAry[]=new int [is.length];
		int MM=is.length-1;
		if(d==0) { //시계방향 오른쪽으로
			for(int i=0;i<4;i++) {
				tempAry[(i+k)%MM]=is[i];
			}
		}else if(d==1) {
			//왼쪽으로
			for(int i=0;i<4;i++) {
				tempAry[(i-k)%MM] = is[i];
			}
		}
		ary[aryIdx]=tempAry;
	}

	private static void input() throws IOException{
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		T = Integer.parseInt(stk.nextToken());
		
		ary = new int[N][M];
		
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				ary[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		info = new int[T][3];
		
		int x,d,k;
		for(int i=0;i<T;i++) {
			stk = new StringTokenizer(br.readLine());
			info[i][0]=Integer.parseInt(stk.nextToken());
			info[i][1]=Integer.parseInt(stk.nextToken());
			info[i][2] = Integer.parseInt(stk.nextToken());
		}
	}
	
	private static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(ary[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}