//Terry Speicher and Jason Ivey

/**
 * Read file into an array and present different sized sub arrays of those points 
 * to each different sort routines and record timed results
 * @author Terry Speicher
 * @author Jason Ivey
 */
import java.util.*;
import java.util.Arrays;
import java.io.*;

public class SortAlgorithmsTester{
  
  private static Point[] data = new Point[100000];
  
  /**
   * Main body of Sort Tester.
   * @param args standard header String[]
   */
  public static void main(String args[]){
    
    /** Create int array representing the number of elements that will be taken from the 
      * beginning of the array of random items
      */
    int [] testCases = {2,2,3,4,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100,
      200,300,400,500,600,700,800,900,1000,2000,3000,4000,5000,6000,7000,
      8000,9000,10000,data.length};
    
    // Set the number of times the data will be tested and averaged.
    int iterations = 10;
    //results table is 9 by however many testCases there are
    long [][] resultsTable = new long[8][testCases.length];
    boolean testing = false; //Turn on/off verbose intermediate findings
    
    read(); //one time read of data[]
    
    SortAlgorithms sort = new SortAlgorithms();  //create class of sort methods
    
    //Main counter for determined number of test cases
    for (int counter = 1 ; counter <= iterations ; counter++){  
      
      //Visual output of loop # currently being processed
      System.out.println("Iteration#" + counter + " of " + iterations);
      
      //Print headings
      if (testing) System.out.print("Sort/#Items,");
      for (int i = 0 ; i < testCases.length ; i++)
        if (testing) System.out.print(testCases[i] + ",");
      if (testing) System.out.println();
      
      //Print each sort name and test results from each test of n elements
      if (testing) System.out.print("BubbleSort,");
      for (int i = 0 ; i < testCases.length ; i++){
        long startTime = System.nanoTime(); 
        sort.bubbleSort(Arrays.copyOfRange(data,0,testCases[i]));
        long estimatedTime = System.nanoTime() - startTime;
        if (testing) System.out.print(estimatedTime + ",");
        resultsTable[1][i] += estimatedTime;
      }
      if (testing) System.out.println();
      
      //Keep printing name of sort and results
      if (testing) System.out.print("ForcePush,");
      for (int i = 0 ; i < testCases.length ; i++){
        long startTime = System.nanoTime(); 
        sort.forcePush(Arrays.copyOfRange(data,0,testCases[i]));
        long estimatedTime = System.nanoTime() - startTime;
        if (testing) System.out.print(estimatedTime + ",");
        resultsTable[2][i] +=estimatedTime;
      }
      if (testing) System.out.println();    
      
      if (testing) System.out.print("MergeSort,");
      for (int i = 0 ; i < testCases.length ; i++){
        long startTime = System.nanoTime(); 
        sort.mergeSort(Arrays.copyOfRange(data,0,testCases[i]));
        long estimatedTime = System.nanoTime() - startTime;
        if (testing) System.out.print(estimatedTime + ",");
        resultsTable[3][i] +=estimatedTime;
      }
      if (testing) System.out.println();
      
      if (testing) System.out.print("QuickSort,");
      for (int i = 0 ; i < testCases.length ; i++){
        long startTime = System.nanoTime(); 
        sort.quickSort(Arrays.copyOfRange(data,0,testCases[i]));
        long estimatedTime = System.nanoTime() - startTime;
        if (testing) System.out.print(estimatedTime + ",");
        resultsTable[4][i] +=estimatedTime;
      }
      if (testing) System.out.println();
      
      if (testing) System.out.print("InsertionSort,");
      for (int i = 0 ; i < testCases.length ; i++){
        long startTime = System.nanoTime(); 
        sort.insertionSort(Arrays.copyOfRange(data,0,testCases[i]));
        long estimatedTime = System.nanoTime() - startTime;
        if (testing) System.out.print(estimatedTime + ",");
        resultsTable[5][i] +=estimatedTime;
      }
      if (testing) System.out.println();
      
      if (testing) System.out.print("SelectionSort,");
      for (int i = 0 ; i < testCases.length ; i++){
        long startTime = System.nanoTime(); 
        sort.selectionSort(Arrays.copyOfRange(data,0,testCases[i]));
        long estimatedTime = System.nanoTime() - startTime;
        if (testing) System.out.print(estimatedTime + ",");
        resultsTable[6][i] +=estimatedTime;
      }
      if (testing) System.out.println();
      
      if (testing) System.out.print("ShakerSort,");
      for (int i = 0 ; i < testCases.length ; i++){
        long startTime = System.nanoTime(); 
        sort.cocktailShakerSort(Arrays.copyOfRange(data,0,testCases[i]));
        long estimatedTime = System.nanoTime() - startTime;
        if (testing) System.out.print(estimatedTime + ",");
        resultsTable[7][i] +=estimatedTime;
      }
      if (testing) System.out.println();
      
    }
    
    //In verbose mode, print out time totals table 
    if (testing) {
      System.out.println("------------------------Totals over " + iterations + " iterations----------------------------");    System.out.print("Sort/#Items,");
      for (int i = 0 ; i < testCases.length ; i++)
        System.out.print(testCases[i] + ",");
      System.out.println();
      
      for (int a = 1; a <=7 ; a++){
        switch (a) {
          case 1: 
            System.out.print("Bubble,");
            break;
          case 2:
            System.out.print("ForcePush,");
            break;
          case 3:
            System.out.print("Merge,");
            break;
          case 4:
            System.out.print("Quick,");
            break;
          case 5: 
            System.out.print("Insertion,");
            break;
          case 6:
            System.out.print("Selection,");
            break;
          case 7:
            System.out.print("Shaker,");
            break;
        }  //end switch case
        
        for (int j = 0 ; j < resultsTable[0].length ; j++){
          System.out.print(resultsTable[a][j] + ",");
        }
        System.out.println();
        
        
      }
    }
    
    //Same loop as above, but print out the averages instead of the total time
    System.out.println("------------------------Averages over " + iterations + " iterations----------------------------");
    
    System.out.print("Sort/#Items,");
    for (int i = 0 ; i < testCases.length ; i++)
      System.out.print(testCases[i] + ",");
    System.out.println();
    
    for (int a = 1; a <=7 ; a++){
      switch (a) {
        case 1: 
          System.out.print("Bubble,");
          break;
        case 2:
          System.out.print("ForcePush,");
          break;
        case 3:
          System.out.print("Merge,");
          break;
        case 4:
          System.out.print("Quick,");
          break;
        case 5: 
          System.out.print("Insertion,");
          break;
        case 6:
          System.out.print("Selection,");
          break;
        case 7:
          System.out.print("Shaker,");
          break;
      }  //end switch case
      
      for (int j = 0 ; j < resultsTable[0].length ; j++){
        System.out.print(resultsTable[a][j]/ iterations+ ",");
      }
      System.out.println();
      
      
    }   
    
  }
  
  /**
   * Read in the file with the pairs of point (x,y) coordinates and place in the data[]
   * 
   */
  private static void read(){

    try {
      File file = new File("100000Points.txt");
      Scanner scan = new Scanner(file);
      
      int x = 0;
      String line;
      String token[];
      while(scan.hasNext()){
        line = scan.nextLine();
        token = line.split("\t");
        data[x] = new Point(Double.parseDouble(token[0]), Double.parseDouble(token[1]));
        x++;
      }
      
      //confirm number of elements read
      System.out.println("We have " + x + " points");
      scan.close();
    }
    catch (Exception e){
      System.out.println("Error in reading file");
    }
  }
  
  
}

