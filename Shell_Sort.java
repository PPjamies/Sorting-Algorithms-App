package part2;

public class Shell_Sort {
	public static Comparable[] Shell_Sort(Comparable[] a) {
		int h = 1;
		int n = a.length;
		while(h<n/3){
			h = 3*h + 1;
		}
		for(;h>0;h/=3) {
			for(int i=h; i<n; i++) {
				for(int j=i; j>=h && less(a[j],a[j-h]); j-=h) {
					swap(a,j,j-1);
				}
			}
		}
		return a;
	}
	
	private static boolean less(Comparable a, Comparable b) {
		if(a.compareTo(b) < 0) {
			return true;
		}
		return false;
	}

	private static void swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
