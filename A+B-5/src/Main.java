import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String []args) throws IOException{
        StringTokenizer st=null;
        int a,b;
        
        while(true){
            st=new StringTokenizer(br.readLine());
            a=Integer.parseInt(st.nextToken());
            b=Integer.parseInt(st.nextToken());
            if(a==0 && b==0) break;
            bw.write(Integer.toString(a+b)+"\n");
        }
        bw.flush();
        bw.close();
    }
}