import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int M;
	private static int T; //T번 회전
	private static int info[][]; //배수,시계/반시계, 회전칸
	private static double sum;
	private static double avgN;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer stk= new StringTokenizer(br.readLine());

		N=Integer.parseInt(stk.nextToken());
		M=Integer.parseInt(stk.nextToken());
		T=Integer.parseInt(stk.nextToken());

		int ary[][]=new int[N][M];
		sum=0;
		avgN=N*M;
		
		for(int i=0;i<N;i++) {
			stk=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				ary[i][j]=Integer.parseInt(stk.nextToken());
				sum+=ary[i][j];
			}
		}
		
		info=new int[T][3];
		
		for(int i=0;i<T;i++) {
			stk=new StringTokenizer(br.readLine());
			info[i][0]=Integer.parseInt(stk.nextToken());
			info[i][1]=Integer.parseInt(stk.nextToken());
			info[i][2]=Integer.parseInt(stk.nextToken());
		}
		
		int del=0; double avg;
		for(int i=0;i<T;i++) {
			rotate(ary,info[i]);
			print(ary);
			del=deleteNum(ary);
			
			if(del==0) {
				if(avgN==0) continue;
				avg=sum/avgN;
				System.out.println(avg+" "+sum+" "+avgN);
				for(int r=0;r<N;r++) {
					for(int c=0;c<M;c++) {
						if(ary[r][c]==0) continue;
						
						if(ary[r][c]>avg) {
							ary[r][c]--;
							sum--;
						}
						else if(ary[r][c]<avg) {
							ary[r][c]++; sum++;
						}
					}
				}
			}
			print(ary);
		}
		
		System.out.println((long)sum);

	}

	private static int deleteNum(int[][] ary) {
		
		HashSet<Pair> delSet=new HashSet<>();
		
		for(int j=0;j<M;j++) {
			for(int i=N-1;i>0;i--) {
				if(ary[i][j]==0) continue;
				if(ary[i-1][j]==ary[i][j]) {
					delSet.add(new Pair(i-1,j));
					delSet.add(new Pair(i,j));
				}	
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=M-1;j>0;j--) {
				if(ary[i][j]==0) continue;
				if(ary[i][j] == ary[i][j-1]) {
					delSet.add(new Pair(i,j));
					delSet.add(new Pair(i,j-1));
				}
			}
			//j가 0일때 M이랑 비교해야함
			if(ary[i][0]!=0 && ary[i][0] == ary[i][M-1]) {
				delSet.add(new Pair(i,0));
				delSet.add(new Pair(i,M-1));
			}
		}
		
		int del =delSet.size();
		avgN-=del;
		for(Pair p:delSet) {
			sum-=ary[p.i][p.j];
			ary[p.i][p.j]=0;
		}
		
		return del;
	}
	
	static class Pair{
		int i;
		int j;
		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + "]";
		}
		@Override
		public int hashCode() {
			return super.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Pair) {
				Pair o=(Pair)obj;
				if(o.i==i && o.j==j) return true;
			}
			return false;
		}
		
	}


	private static void rotate(int [][]ary,int []info) {
		int spin;
		int moveAry[];
		int temp;

		for(int i=info[0]-1;i<=N;i+=info[0]) { //
			spin=i; //우리는 0열부터 시작하기 때문에
			moveAry=new int [M];
			for(int j=0;j<M;j++) {
				if(info[1]==0)
					moveAry[(j+info[2])%M]=ary[spin][j];
				else {
					temp = (j-info[2])>=0 ? j-info[2] : M+j-info[2];

					moveAry[temp%M]=ary[spin][j];
				}
			}
			ary[spin] = moveAry;

		}

	}
	

	
	
	private static void print(int [][]ary) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(ary[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
