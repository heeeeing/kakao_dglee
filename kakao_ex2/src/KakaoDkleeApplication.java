

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class KakaoDkleeApplication {

	public static void main(String[] args) {
		//3[a]z
		//3[a4[c]]
		Ex01_jordy jordy = new Ex01_jordy();
		Scanner sc = new Scanner(System.in);
		String str = "";
		System.out.print("입력 : ");
		str = sc.nextLine();
		
		System.out.println(jordy.decodeStr(str));
		
		
	}
	
}
