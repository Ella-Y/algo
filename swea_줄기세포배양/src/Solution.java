import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Solution
 */
public class Solution {
    static BufferedReader br;
    static int N, M, K; // 세로,가로,배양시간
    static int mmap[][][]; // 위치 [0]:생명력/[1]배양시간
    final static int di[] = { -1, 0, 1, 0 };
    final static int dj[] = { 0, -1, 0, 1 };
    static ArrayList<Pair> active;
    static ArrayList<Pair> unactive;

    public static void main(String[] args) throws IOException {
        //br = new BufferedReader(new FileReader("src/input.txt"));
    	br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for(int test=1;test<=T;test++) {
        	input();
            problem(test);
        }
    }

    private static void input() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        mmap=new int [N+K+K][N+K+K][2];
        active=new ArrayList<Pair>();
        unactive=new ArrayList<Pair>();
        
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                mmap[i+K][j+K][0]=Integer.parseInt(stk.nextToken());
                if(mmap[i+K][j+K][0]!=0) {
                	mmap[i+K][j+K][1]=1;
                    unactive.add(new Pair(i+K, j+K, mmap[i+K][j+K][0]));
                }
            }
        }

    }

    private static void problem(int test) {
    	//print();
        //System.out.println("active:"+active);
        //System.out.println("unactive:"+unactive);
        ArrayList<Pair> delList=new ArrayList<>();
        
        for (int time = 1; time <= K; time++) {
        	delList.clear();
            for (Pair p : active) {
            	breeding(p, time); // 번식
                if (p.time == time) { // 죽어야할 시간이면
                    delList.add(p);
                }
            }
            active.removeAll(delList);
            
            delList.clear();
            for (Pair p : unactive) { // 비활성 상태의 세포들을 활성상태로 바꾼다.
                if (p.time == time) { // 활성상태로 바뀌어야함 -> 죽는 시간으로 시간을 바꿈
                    active.add(new Pair(p.i, p.j, time + mmap[p.i][p.j][0]));
                    //unactive.remove(p);
                    delList.add(p);
                }
            }
            unactive.removeAll(delList);
            
            //System.out.println("-----"+time+"시간 후--------");
            //print();
            //System.out.println("active:"+active);
            //System.out.println("unactive:"+unactive);
            
        }
        
        System.out.println("#"+test+" "+(active.size()+unactive.size()));
        
    }
    private static void print(){
        for(int i=0;i<mmap.length;i++){
            for(int j=0;j<mmap[i].length;j++){
                System.out.print(mmap[i][j][0]+" ");
            }
            System.out.println();
        }
    }
    private static void breeding(final Pair p, int startTime) {

        int ti, tj, x;

        for (int z = 0; z < 4; z++) {
            ti = p.i + di[z];
            tj = p.j + dj[z];
            if (0 <= ti && ti < mmap.length && 0 <= tj && tj < mmap[0].length) {
                x = mmap[p.i][p.j][0]; //현재 위치의 생명력
                
                // 값이 이미 있는 경우
                if(mmap[ti][tj][0]>=1) {
                	if(mmap[ti][tj][1]==startTime && x>mmap[ti][tj][0]) {
                		// 배양시각과 startTime과 같을때, 생명력수치가 높으면 덮어씌운다.
                        mmap[ti][tj][0] = x; // 생명력수치
                        mmap[ti][tj][1] = startTime; // 배양시각
                        unactive.add(new Pair(ti, tj, startTime + x)); //활성시각
                	}
                }else{
                	mmap[ti][tj][0] = x;
                    mmap[ti][tj][1] = startTime; // 배양시각
                    unactive.add(new Pair(ti, tj, startTime + x)); //활성시각
                }

            }
        }

    }

    static class Pair {
        int i;
        int j;
        int time;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Pair [i=" + i + ", j=" + j + ", time=" + time + "]";
        }

        public Pair(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }

    }
}