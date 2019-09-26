package part2;

public class Heap_Sort {
	
	public static Comparable[] Heap_Sort(Comparable[] a) {
		int size = a.length;
		for(int i = size/2-1; i>=0; i--) {
			makeHeap(a,size,i);
		}
		for(int i = size-1; i>=0; i--) {
			swap(a,0,i);
			makeHeap(a,i,0);
		}
		return a;
	}
	
	 private static void makeHeap(Comparable[] a, int size, int i) { 
	        int max = i;
	        int left = 2*i + 1;
	        int right = 2*i + 2;  
	  
	        if (left < size && less(a[max],a[left])) {
	            max = left; 
	        }
	        if (right < size && less(a[max],a[right])) {
	            max = right; 
	        }	  
	        if (max != i){ 
	        	swap(a, i, max);
	        
	            makeHeap(a, size, max); 
	        } 
	    }
	 
		private static boolean less(Comparable a, Comparable b) {
			if(a.compareTo(b) < 0) {
				return true;
			}
			return false;
		}

		private static void swap(Comparable[] a, int i, int max) {
			Comparable temp = a[i];
			a[i] = a[max];
			a[max] = temp;
		}
}
