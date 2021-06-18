

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex01_jordy {
	public String decodeStr(String inputStr){
		//입력받는 데이터의 [갯수만큼 배열을 생성한다.
		String[] inputArr = new String [countChar(inputStr, '[')+1];
		Pattern pattern = Pattern.compile("(\\[(.*)\\])");
		
		int i = 1;
		//inputArr 0번째에 원형을 넣는다.
		inputArr[0] = inputStr;
		while(true) {
			//정규식을 이용해 inputArr[1]부터 []안의 연산식을 넣는다
			Matcher matcher = pattern.matcher(inputStr);
			if(matcher.find()){// 정규식과 매칭되는 값이 있으면
			    inputStr = matcher.group(2).trim();
			    inputArr[i] = inputStr; 
			    inputArr[0].replace(inputStr, "");
			}else {
				break;
			}
			i++;
		}

		//제일 []안쪽은 필요없음으로 제거
		for(int a = inputArr.length-1; a > 0; a--) {
			String temp = "";
			String number = "";
			String first = inputArr[a-1].substring(0, inputArr[a-1].indexOf('['));
			String middle = inputArr[a-1].substring(inputArr[a-1].indexOf('[')+1,inputArr[a-1].indexOf(']'));
			String last = inputArr[a-1].substring(inputArr[a-1].indexOf(']')+1);
			
			for(int b = 0; b < first.length(); b ++) {
				if( !CheckNumber(first.charAt(b)) ) {
					temp += first.charAt(b);
				}else {
					number += first.charAt(b);
				}
			}
			
			for(int c = 0; c < Integer.parseInt(number); c ++) {
				temp += middle;
			}
			
			temp += last;
			
			for(int d = 0; d < inputArr.length-1; d ++) {
				inputArr[d] = inputArr[d].replace(inputArr[a-1], temp);
			}
		}
		return inputArr[0];
	}
	
	//숫자인지 체크
	public boolean CheckNumber(char ch){
		if( ch < 48 || ch > 58)
		{
			return false;
		}
		return true;
	}
	
	//문자열의 특정 문자 갯수 출력
	public int countChar(String str, char ch) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ch) {
			count++;
			}
		}
		return count;
	}
}
