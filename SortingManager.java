package part2;

//Dominic Luu
//Pazuzu Jindrich
//5/8/2019
//CS 401
//HW 2

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SortingManager {
	private int fileSize;
	private String fileType;
	private Comparable[] a;
	private Scanner scan;
	private long timeElapse;
	
	//Sorting algorithms return Comparable[] a
	private Selection_Sort selection_sort;
	private Insertion_Sort insertion_sort;
	private Shell_Sort shell_sort;
	private Bubble_Sort bubble_sort;
	private Merge_Sort merge_sort;
	private Quick_Sort quick_sort;
	private Heap_Sort heap_sort;
	
	private void initialize(File file, int option, int fileSize, String fileType) {
		this.fileSize = fileSize;
		this.fileType = fileType; 
		a = new Comparable[fileSize];
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public SortingManager(File file, int option, int fileSize, String fileType) throws FileNotFoundException {	
		initialize(file, option, fileSize, fileType);
		
		//fill comparable array with contents of current text file
		int index = 0;
		while(scan.hasNextInt() && index<=fileSize) {
			int token = scan.nextInt();
			a[index++] = token;
		}
		scan.close();
		
		
		//sort comparable arrays according to option selected	
		//override comparable array
		long startTime = System.currentTimeMillis();
		if(option == 0) { 
			startTime = System.currentTimeMillis();
			a = selection_sort.Selection_Sort(a);
		}else if(option == 1) { 
			startTime = System.currentTimeMillis();
			a = insertion_sort.Insertion_Sort(a);
		}else if(option == 2) {
			startTime = System.currentTimeMillis();
			a = shell_sort.Shell_Sort(a);
		}else if(option == 3) {
			startTime = System.currentTimeMillis();
			a = bubble_sort.Bubble_Sort(a);
		}else if(option == 4) {
			startTime = System.currentTimeMillis();
			a = merge_sort.Merge_Sort(a, 0, index);
		}else if(option == 5) {
			startTime = System.currentTimeMillis();
			a = quick_sort.quickSort(a, 0, index);
		}else if(option == 6) {
			startTime = System.currentTimeMillis();
			a = heap_sort.Heap_Sort(a);
		}
		long endTime = System.currentTimeMillis();
		long timeElapse = (endTime - startTime);
		reWriteTextFile();
	}
	
	//re-writes the current data file with sorted elements
	private void reWriteTextFile() {
        String fileName = this.fileType + fileSize + ".txt";
        
        StringBuilder output = new StringBuilder();
        if (fileType.equals("unsorted")) {
            for (int i = 0; i < a.length; i++) { 
                output.append(a[i] + " ");
            }
        } else {
            for (int i = 0; i < a.length; i++) {
                output.append(a[i] + " ");
            }
        }
        try {
            PrintWriter printWriter = new PrintWriter(fileName, "UTF-8");
            printWriter.println(output);
            printWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(output);
        System.out.println("Time elapsed: " + timeElapse + " milliseconds");
	}
}
