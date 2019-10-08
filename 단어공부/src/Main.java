import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		
		char str[]=br.readLine().toUpperCase().toCharArray();
		int number[]=new int['Z' - 'A'+1];
		
		int index=0;
		int max=0;
		boolean flag=false;
		for(int i=0,size=str.length;i<size;i++) {
			++number[str[i]-'A'];
			if(number[str[i]-'A']>max) {
				index=str[i]-'A';
				max=number[str[i]-'A'];
				flag=false;
			}else if(number[str[i]-'A']==max) {
				flag=true;
			}
		}

		System.out.println(flag?'?':(char)(index+'A'));
		

	}
	
	
}
