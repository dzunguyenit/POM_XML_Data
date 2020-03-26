package BigBom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SumDraft {
	public static void main(String[] args) {
		int page = 49;
		int tong = (page % 50 != 0) ? (page / 50 + 1) : (page / 50);
		System.out.println(tong);

//		String text1 = "₫1,642,543/₫1,000,000 daily";
//		String text2 = "₫3,391/₫50,000 daily";
//		String text3 = "₫0/₫50,000 daily";
//
//		int regexText1 = getTextRegex("₫(.*?)/₫(.*?) daily", text1, 1);
//		int regexText2 = getTextRegex("₫(.*?)/₫(.*?) daily", text2, 1);
//		int regexText3 = getTextRegex("₫(.*?)/₫(.*?) daily", text3, 1);
//
//		System.out.println("regexText1 = " + regexText1);
//		System.out.println("regexText1 = " + regexText2);
//		System.out.println("regexText1 = " + regexText3);
//
//		int finalSum = regexText1 + regexText2 + regexText3;
//
//		System.out.println("Tong la " + finalSum);
//
//		String number = "1,642,543";
//		String numbernew = number.replaceAll(",", "");
//		System.out.println("============Debug=== " + numbernew);
	}

	public static int getTextRegex(String regex, String text, int group) {
		String textRegex = "";
		Pattern patternRegex = Pattern.compile(regex);
		Matcher m = patternRegex.matcher(text);

		if (m.find()) {
			textRegex = m.group(group);
		}
		return Integer.parseInt(textRegex.replaceAll(",", ""));
	}
}
