import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixCalculator {
   private static Scanner in;
   
   
   public MatrixCalculator() {
    in = new Scanner(System.in); 
   }
  
 
  public static void main(String[] args) {
    MatrixCalculator start = new MatrixCalculator();
    //ok so this creates 2 matrices that you can now operate on
    Matrix[] values = enterMatrices();
    Matrix A = values[0];
    Matrix B = values[1];
    command(A,B);
    
    
  }
  
  
  public static Matrix[] enterMatrices() {
    //initialize scanner

    
    System.out.println("Enter the first matrix as follows: [0 0 0 0; 0 0 0 0] without the brackets");
    String m1 = in.nextLine(); //take the whole line as input
 
    String[] rows = m1.split("; ");
    if (rows.length>1) {
      for (int i=0;i<rows.length-1;i++) {
        if (rows[i].length() != rows[i+1].length()) {
         System.out.println("size of each row of the matrix must be the same!");
         m1 = in.nextLine();
         rows = m1.split("; ");
        }
      }
      
    }
    //so a whole long string is entered, then you split it into two rows: [0 0 0 0] [0 0 0 0]
  
    //bc it's divided by spaces, so the number of numbers is always length/2+1
    
    double[][] r1 = new double[rows.length][(rows[0].length()/2+1)];
    
    for (int i=0;i<rows.length;i++) {
      //now you split each entry of the matrix into the individual characters: 0, 0, 0, 0
      String[] currow = rows[i].split(" ");
      for (int j=0;j<currow.length;j++) {
       r1[i][j] = Integer.parseInt(currow[j]);
      }
    }
    System.out.println(Arrays.deepToString(r1));
    
 
     System.out.println("Enter the second matrix as follows: [0 0 0 0; 0 0 0 0] w/o brackets");
     System.out.println("Enter 0 if there is no matrix B you need");
      String m2 = in.nextLine();
      

  
    rows = m2.split("; ");
    //so a whole long string is entered, then you split it into two rows: [0 0 0 0] [0 0 0 0]
  
    double[][] r2 = new double[rows.length][(rows[0].length()/2+1)];
    
    for (int i=0;i<rows.length;i++) {
      //now you split each entry of the matrix into the individual characters: 0, 0, 0, 0
      String[] currow = rows[i].split(" ");
      for (int j=0;j<currow.length;j++) {
       r2[i][j] = Integer.parseInt(currow[j]);
      }
    }
    System.out.println(Arrays.deepToString(r2));
     
     Matrix A = new Matrix(r1);
     Matrix B = new Matrix(r2);
     Matrix[] m = {A,B};
     return m;
    
     
  }
  
  public static void command(Matrix A, Matrix B) {
    //different cases of commands, very stringent
    System.out.println("Possible calculations:");
    
   String command = in.nextLine();
   System.out.println(command);
   switch(command) {
     
     case "A+B":
       if (A.getRow() != B.getRow() || A.getCol() != B.getCol()) {
      break; 
     }
       add(A, B);
       break;
       
     case "A-B":
       if (A.getRow() != B.getRow() || A.getCol() != B.getCol()) {
      break; 
     }
       subtract(A, B);
       break;
       
     case "A.B":
       if (A.getCol() != B.getRow()) {
      break; 
     }
       Matrix C3 = dotProduct(A, B);
       System.out.println(C3);
       System.out.println("Hello");
       break;
       
       case "B.A":
       if (B.getCol() != A.getRow()) {
      break; 
     }
       Matrix C4 = dotProduct(B, A);
       System.out.println(C4);
       break;
       
       
     case "AxB":
       if (A.getRow() != B.getRow()) {
      break; 
     }
       crossProduct(A,B);
       break;
       
     case "sA":
     case "sB":
       System.out.println("Enter the scalar");
       int s = in.nextInt();
       scalarMult(A,s);
       break;
       
     case "row reduce A":
       rowReduce(A);
       break;
     
   }
  }
  
  
  
  
  public static Matrix add(Matrix A, Matrix B) {

    double[][] a = A.getMatrix();
    double[][] b = B.getMatrix();
    double[][] c = new double[a.length][a[0].length];
    
    for (int i=0;i<a.length;i++) {
      for (int j=0;j<a[0].length;j++) {
        c[i][j] = a[i][j] + b[i][j];
      }
    }
    Matrix C = new Matrix(c);
    System.out.println(C);
    return C;
  }
  
  public static Matrix subtract(Matrix A, Matrix B) {
    double[][] a = A.getMatrix();
    double[][] b = B.getMatrix();
    double[][] c = new double[a.length][a[0].length];
    
    for (int i=0;i<a.length;i++) {
      for (int j=0;j<a[0].length;j++) {
        c[i][j] = a[i][j] - b[i][j];
      }
    }
    
    Matrix C = new Matrix(c);
    System.out.println(C);
    return C;
    
  }
  
  public static Matrix dotProduct(Matrix A, Matrix B) {
    double[][] a = A.getMatrix();
    double[][] b = B.getMatrix();
    double[][] c = new double[a.length][b[0].length];
    
    for (int i=0;i<a[0].length;i++) {
      
      for (int j=0;i<b.length;j++) {
       c[i][j] = a[i][j]*b[j][i];
        
      }
      
    }
    Matrix C = new Matrix(c);
    System.out.println(C);
    return C;
    
  }
  
  public static void crossProduct(Matrix A, Matrix B) {
    
  }
  
  public static void scalarMult(Matrix A, double s) {
    
    
  }
  
  public static void rowReduce(Matrix A) {
    /**
     [2 3 4]
     [5 6 7]
     **/
    //you have an double[][] with [[2,3,4],[5,6,7]]
    double[][] C = A.getMatrix();
    
    int rows = A.getRow();
    int cols = A.getCol();
    
    //THE MATRIX INVERSION ALGORITHM
    int count = 0;
    for (int j = 0;j<cols-1;j++) {
      C[count][j] = C[count][j]/C[count][count];
      //[1 3/2 2]
      //[5 6 7]
    
    for (int i=0;i<rows;i++) {
      if (i==count) {
       continue; 
      }
    for (int k=0;k<cols;k++) {
     double x = C[i][j]/C[count][j]; 
     C[i][k] = C[i][k] - (x*C[0][k]);
     //[1 3/2 2]
     //[0 -3/2 -3]
    }
    }
    
    count++;
    
    }
    System.out.println(Arrays.deepToString(C));
        
  }
  
  
                           
  
  
  
  
}