package ≈∏¿œ;

import java.util.Scanner;


public class Main {
	
	static int[][] mmap;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int I = sc.nextInt();
		int J = sc.nextInt();
		mmap = new int[N][N];

		dfs(0,0,N,N,I,J);

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				System.out.print(mmap[i][j]+" ");
			}
			System.out.println();
		}
		sc.close();
		
	}

	static void dfs(int si, int sj, int ei, int ej, int ni, int nj){
		if(ei-si==1)
			return;

		int i = (si+ei)/2;
		int j = (sj+ej)/2;

		int temp = check(si,sj,ei,ej,ni,nj);

		if(temp==1) { //1π¯
			mmap[i][j] = mmap[i-1][j] = mmap[i][j-1] = temp;
			dfs(si, j, i, ej, i-1, j); 
			dfs(i, j, ei, ej, i, j);
			dfs(si, sj, i, j, ni, nj); 
			dfs(i, sj, ei, j, i, j-1); 
		}
		else if(temp==2) {
			mmap[i][j] = mmap[i][j-1] = mmap[i-1][j-1] = temp;
			dfs(si, sj, i, j, i-1, j-1); 
			dfs(i, sj, ei, j, i, j-1); 
			dfs(i, j, ei, ej, i, j); 
			dfs(si, j, i, ej, ni, nj); 
		}else if(temp==3) {
			mmap[i][j]= mmap[i-1][j-1] = mmap[i-1][j] = temp;
			dfs(si, sj, i, j, i-1, j-1); 
			dfs(i, sj, ei, j, ni, nj); 
			dfs(i, j, ei, ej, i, j); 
			dfs(si, j, i, ej, i-1, j); 
		}
		else if(temp==4) {
			 mmap[i-1][j] = mmap[i-1][j-1] = mmap[i][j-1] = temp;
			dfs(si, j, i, ej, i-1, j); 
			dfs(i, j, ei, ej, ni, nj); 
			dfs(i, sj, ei, j, i, j-1); 
			dfs(si, sj, i, j, i-1, j-1);
		}
		
	}

	static int check(int si, int sj, int ei, int ej, int ni, int nj){
		int i = (si+ei)/2;
		int j = (sj+ej)/2;
		
		if(sj<=nj && nj<j && si<=ni && ni<i)
			return 1;
		else if(j<=nj && nj<ej && si<=ni && ni<i)
			return 2;
		else if(i<=ni && ni<ei && sj<=nj && nj<j)
			return 3;
		
		return 4;

	}

}

