package Arrays;

public class PivotElement {

	public static void main(String[] args) {
		int[] arr = { 1, 7, 3, 6, 5, 6 };
		int pivot = Findidx(arr);
		System.out.println("The idx at which the left sum and right su  are same is : " + pivot);
	}

	private static int Findidx(int[] arr) {
		int rightSum = 0;
		int leftSum = 0;
		for (int num : arr)
			rightSum += num;
		for (int i = 0; i < arr.length; i++) {
			if (leftSum == rightSum - arr[i])
				return i;
			leftSum += arr[i];
			rightSum -= arr[i];
		}
		return -1;
	}

}
