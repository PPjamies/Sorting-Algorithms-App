package part2;

//Dominic Luu
//Pazuzu Jindrich
//5/8/2019
//CS 401
//HW 2

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class GUI {
    private JFrame frame = new JFrame();
    private JPanel panelMain = new JPanel();
    private JPanel panelCenter = new JPanel();

    private JLabel dataSizeLabel = new JLabel("Data Size (less than 500000");
    private JTextField dataSizeField = new JTextField(20);

    private JLabel titleLabel = new JLabel("Merging");

    private JPanel buttonPanel = new JPanel();
    private JButton generateUnsortedButton = new JButton("Unsorted");
    private JButton generateSortedButton = new JButton("Sorted");


    // Elementary Sorting Algorithms
    private JButton sortBttn = new JButton("SORT");
    private String[] sortingAlgo = {"Selection Sort", "Insertion Sort",
            "Shell Sort", "Bubble Sort", "Merge Sort", "Quick Sort", "Heap Sort"};
    private JComboBox options = new JComboBox(sortingAlgo);
    
    private String fileName;
    private int fileSize;		
    private String fileType;
    
    private int selection;		//sorting algo selection
    private boolean type_selected = false;
    private boolean size_selected = false;


    public GUI() {
//Setting up GUI components    	
        fileName = "";
        
        panelMain.setLayout(new BorderLayout());
        panelCenter.setLayout(new GridLayout(22, 1));
        panelCenter.add(dataSizeLabel);
        panelCenter.add(dataSizeField);
        panelCenter.add(new JLabel("Generate dataset"));

        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(generateUnsortedButton);
        buttonPanel.add(generateSortedButton);

        panelCenter.add(buttonPanel);
        panelCenter.add(new JLabel());
        panelCenter.add(options);
        panelCenter.add(sortBttn);  

//Sorted button
        generateSortedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(dataSizeField.getText().equals("")) {
            		String message = "Please input data size";
        		    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
        		    return;
            	}else {
	        		String input = dataSizeField.getText();
	        		fileSize = Integer.parseInt(input);
	        		fileType = "sorted";
	        		type_selected = true;
	        		processInput(input, "sorted");
            	}
            }
        });

//Unsorted button     
        generateUnsortedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(dataSizeField.getText().equals("")) {
            		String message = "Please input data size";
        		    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
        		    return;
            	}else {
	                String input = dataSizeField.getText();
	                fileSize = Integer.parseInt(input);
	                fileType = "unsorted";
	                type_selected = true;
	                processInput(input, "unsorted");
            	}
            }
        });

    	
//Sort button
        sortBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(type_selected == true) {
	        		try {
	        			File file = new File(fileName);
	        			SortingManager manager = new SortingManager(file,selection,fileSize,fileType);
	        			type_selected = false;
					} catch (FileNotFoundException err) {
						err.printStackTrace();
					}  
            	}else {
            		String message = "Please select sorted or unsorted";
            		    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
            		        JOptionPane.ERROR_MESSAGE);
            	}
            }
        });


//Add remainder components to GUI
        panelMain.add(titleLabel, BorderLayout.NORTH);
        panelMain.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelMain);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Generates data files
    // Input type = "sorted" or "unsorted"
    private void processInput(String input, String inputType) {
        boolean inputValid = true;

        int inputSize = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                inputValid = false;
            }
        }

        if (inputValid) {
            inputSize = Integer.parseInt(input);
            if (inputSize > 500000) {
                inputValid = false;
            }
        }

        //Generates the appropriate data files
        if (inputValid) {
            System.out.println("input" + inputSize);
            fileName = inputType + inputSize + ".txt";

            StringBuilder output = new StringBuilder();
            if (inputType.equals("unsorted")) {
                for (int i = 0; i < inputSize; i++) {
                    int randomInt = (int) (Math.random() * 500000);
                    output.append(randomInt + " ");
                }
            } else {
                for (int i = 0; i < inputSize; i++) {
                    output.append((i + 1) + " ");
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
        } else {
            dataSizeField.setText("Invalid input");
        }
    }
}
