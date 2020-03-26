package BigBom;

import java.util.ArrayList;
import java.util.Arrays;

public class Convert {
	public static void main(String args[]) {
		ArrayList<String> listNumber = new ArrayList<>();
		listNumber.add("123");
		listNumber.add("345");
		listNumber.add("437");

//		String[] str = { "123", "345", "437", "894" };
		int size = listNumber.size();
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = Integer.parseInt(listNumber.get(i));
			Arrays.toString(arr);
		}
		for (int i : arr) {
			System.out.println("Phan tu so " + i);
		}

//		System.out.println(Arrays.toString(arr));
	}
}
