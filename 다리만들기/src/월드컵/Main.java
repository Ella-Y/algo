package 월드컵;

import java.util.Scanner;

public class Main {
	static boolean[] check;
	static boolean flag;
	static int[][] team;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		for (int z = 0; z < 4; z++) {
			problem(scan);
		}
	}

	private static void problem(Scanner scan) {
		int win = 0;
		int draw = 0;
		int lose = 0;
		flag = false;

		check = new boolean[6];
		team = new int[6][3];
		
		for (int i = 0; i < 6; i++) {
			team[i][0] = scan.nextInt();
			win = win + team[i][0];
			team[i][1] = scan.nextInt();
			draw = draw + team[i][1];
			team[i][2] = scan.nextInt();
			lose = lose + team[i][2];
		}
		
		for(int i=0;i<6;i++) {
			if (team[i][0] + team[i][1] + team[i][2] != 5) { //전체 5가 아니면 실패
				System.out.print(0+" ");
				return;
			}
		}
		
		dfs(0,1);
		
		if(flag) {
			System.out.print(1+" ");
		}else {
			System.out.print(0+" ");
		}
		
	}

	static void dfs(int a, int b) {
		
		if(a==6) {
			flag=true;
			return;
		}
		
		if(b==6) {
			dfs(a+1,a+2);
			return;
		}
		
		team[a][0]--;team[b][2]--;
		
		if(team[a][0]>=0&&team[b][2]>=0) {
			dfs(a,b+1);
		}
		
		team[a][0]++;team[b][2]++;
		team[a][1]--;team[b][1]--;
		
		if(team[a][1]>=0&&team[b][1]>=0) {
			dfs(a,b+1);
		}
		
		team[a][1]++;team[b][1]++;
		team[a][2]--;team[b][0]--;
		
		if(team[a][2]>=0&&team[b][0]>=0) {
			dfs(a,b+1);
		}
		
		team[a][2]++;team[b][0]++;
		
	}
}
