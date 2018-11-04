package sort;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc=new Scanner(System.in);
		System.out.println("Enter total elements to be sorted:");
		int n=sc.nextInt();
		System.out.println("Enter elements separated by space:");
		int a[]=new int[n];
		for (int i = 0; i < n; i++) {
			a[i]=sc.nextInt();
		}
		mergeSort(a,1,n);
		System.out.println(Arrays.toString(a));
	}

	private static void mergeSort(int[] a, int start, int end) {
		// TODO Auto-generated method stub
		if(end>start){
			mergeSort(a, start, (start+end)/2);
			mergeSort(a, (start+end)/2+1, end);
			sortArray(a, start-1, (start+end)/2, end-1);
		}
	}

	private static void sortArray(int[] a, int start, int mid, int end) {
		// TODO Auto-generated method stub
		for(int i=start;i<end;i++){
			for(int j=mid;j<=end;j++){
				if(a[j]<a[i]){
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
			if(i+1==mid)
				mid+=1;
		}
	}
}
// 38 27 43 3 9 82 10