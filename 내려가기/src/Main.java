import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static int board[];
	static int answerMin[];
	static int answerMax[];
	static int N;

	public static void main(String[] args) throws IOException {
		//br=new BufferedReader(new InputStreamReader(System.in));
		br=new BufferedReader(new FileReader("src/input.txt"));
		board= new int[3];
		answerMin =new int [3];
		answerMax= new int [3];
		N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i=0;i<3;i++) {
			answerMax[i] = Integer.parseInt(stk.nextToken());
			answerMin[i] = answerMax[i];
		}

		//with input
		for(int i=1;i<N;i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				board[j] = Integer.parseInt(stk.nextToken());
			}
			for(int j=0;j<3;j++) {
				answerMax[j]+=calc(j);
				answerMin[j]+=calc2(j);
			}
			System.out.println(Arrays.toString(answerMax));
			System.out.println(Arrays.toString(answerMin));
		}
		int max =0,min = Integer.MAX_VALUE;
		for(int k=0;k<3;k++) {
			max = max > answerMax[k]?max : answerMax[k];
			min = min < answerMin[k]? min: answerMin[k];
		}

		System.out.println(max+" "+min);


	}

	private static int calc2(int sj) {
		int j = sj-1;
		int min = Integer.MAX_VALUE;
		for(int k=0;k<3;k++) {
			if(0<=j && j<3) {
				min = min < board[j]?min: board[j];
			}
			j++;
		}
		return min;
	}

	private static int calc(int sj) {
		int j=sj-1;
		int max = 0;
		for(int k=0;k<3;k++) {
			if(0<=j && j<3) {
				max = max > board[j]?max : board[j];
			}
			j++;
		}
		return max;
	}
}
