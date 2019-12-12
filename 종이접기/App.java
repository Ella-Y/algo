import java.util.Arrays;
/**프로그래머스 종이접기
 * 종이를 절반씩 접은 후, 가장 왼쪽의 굴곡 모양부터 순서대로 배열에 담아 return하기
 * 종이를 접었다 편 후 생긴 굴곡이 ∨ 모양이면 0, ∧ 모양이면 1로 
 */
public class App {
    public static void main(String[] args) throws Exception {
        int n = 20; //주어진 값

        int ary[]=new int [(1<<n)-1];
        divide(ary, 0, ary.length, 0);
        System.out.println(Arrays.toString(ary));
    }
    public static void divide(int []ary, int start,int end,int val){
        int mid = (start+end)/2;
        ary[mid] = val;
        if(mid-start>1)
            divide(ary,start,mid,0);
        if(end-mid>1)
            divide(ary,mid,end,1);
    }
}
