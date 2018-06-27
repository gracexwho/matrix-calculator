import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixCalculator {
   private static Scanner in;
   // scanner to take user input
   
   public MatrixCalculator() {
    in = new Scanner(System.in); 
   }
  
 
  public static void main(String[] args) {
    MatrixCalculator start = new MatrixCalculator();
    //this creates 2 matrices that you can now operate on
    Matrix[] values = enterMatrices();
    Matrix A = values[0];
    Matrix B = values[1];
    command(A,B);
    
  }
  
  
  public static Matrix[] enterMatrices() {
    // very rigid input system to enter matrices to be calculated
    System.out.println("Enter the first matrix as follows: [0 0 0 0; 0 0 0 0] without the brackets");
    String m1 = in.nextLine(); //take the whole line as input
    
    // splits input (the matrix) by rows
    String[] rows = m1.split("; ");
    if (rows.length>1) {
      for (int i=0;i<rows.length-1;i++) {
        String[] temp1 = rows[i].split(" ");
        String[] temp2 = rows[i+1].split(" ");
        //checks to make sure that all the rows are the same length: i.e. contain the same amount of elements
        if (temp1.length != temp2.length) {
         System.out.println("size of each row of the matrix must be the same!");
         m1 = in.nextLine();
         rows = m1.split("; ");
        }
      }
      
    }
  
    // so a whole long string is entered, then you split it into two rows: [0 0 0 0] [0 0 0 0]
    // bc it's divided by spaces, so the number of numbers is always length/2+1
    
    double[][] r1 = new double[rows.length][(rows[0].length()/2+1)];
    
    for (int i=0;i<rows.length;i++) {
      //now you split each entry of the matrix into the individual characters: 0, 0, 0, 0
      String[] currow = rows[i].split(" ");
      for (int j=0;j<currow.length;j++) {
       r1[i][j] = Integer.parseInt(currow[j]);
      }
    }
    Matrix A = new Matrix(r1);
    System.out.println(A);
    
    
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
     
    // uses the multidimensional arrays to create new Matrix objects by calling the constructor from the Matrix class
     
     Matrix B = new Matrix(r2);
     System.out.println(B);
     
     Matrix[] m = {A,B};
     return m;
    
     
  }
  
  // The actual calculator part, which takes commands from the user
  public static void command(Matrix A, Matrix B) {
    //different cases of commands, very stringent
    System.out.println("Possible calculations (enter the text without brackets):");
    System.out.println("[A+B] = adds the two matrices");
    System.out.println("[A-B] = subtracts matrix B from matrix A");
    System.out.println("[B-A] = subtracts matrix A from matrix B");
    System.out.println("[A*B] = computes dot product of A*B");
    System.out.println("[B*A] = computes dot product of B*A");
    System.out.println("[AxB] = computes cross product of AxB, must be 3D column vectors");
    System.out.println("[sA] = computes product of scalar times A");
    System.out.println("[row reduce A] = row reduces Matrix A");
    System.out.println("[row reduce B] = row reduces Matrix B");
    
    System.out.println("If no Matrix B is necessary, then enter 0 when prompted.");
    
    
   boolean validInput = false;
   while (validInput==false) {
     System.out.println("Please enter a valid input.");
     String command = in.nextLine();
     System.out.println(command);
   
   switch(command.toLowerCase()) {
     case "a+b":
       if (A.getRow() != B.getRow() || A.getCol() != B.getCol()) {
       System.out.println("Matrices A and B must be the same dimensions.");
      break; 
     }
       add(A, B);
       validInput = true;
       break;
       
     case "a-b":
       if (A.getRow() != B.getRow() || A.getCol() != B.getCol()) {
       System.out.println("Matrices A and B must be the same dimensions.");
      break; 
     }
       subtract(A,B);
       validInput = true;
       break;
       
     case "b-a":
       if (A.getRow() != B.getRow() || A.getCol() != B.getCol()) {
       System.out.println("Matrices A and B must be the same dimensions.");
      break; 
     }
       subtract(B,A);
       validInput = true;
       break;
       
     case "a*b":
       if (A.getCol() != B.getRow()) {
      break; 
     }
       multiply(A, B);
       validInput = true;
       break;
       
       case "b*a":
       if (B.getCol() != A.getRow()) {
      break; 
     }
       multiply(B, A);
       validInput = true;
       break;

       
       
     case "axb":
       if (A.getRow() != B.getRow() && A.getCol() != B.getCol() && A.getCol() != 1 ) {
       System.out.println("You can only do cross product on 3D column vectors.");
      break; 
     }
       crossProduct(A,B);
       validInput = true;
       break;
       
     case "sa":
       System.out.println("Enter the scalar");
       double s = in.nextInt();
       scalarMult(A,s);
       in.nextLine();
       validInput = true;
       break;
       
     case "row reduce a":
       rowReduce(A);
       validInput = true;
       break;
       
     case "row reduce b":
       rowReduce(B);
       validInput = true;
       break;
       
     case "end":
       System.exit(0);
       break;
       
     default: break;
   }
   // If the input is not valid, the program asks for another input
   }
   
   System.out.println("Would you like to try a different calculation? Type yes or no");
   String user = in.nextLine();
   
   if (user.equals("yes")) {
     Matrix[] values = enterMatrices();
     Matrix X = values[0];
     Matrix Y = values[1];
     command(X,Y);
   } else {
     System.out.println("Hope you're done your math homework!");
    System.exit(0);
   }
  }
  
  
  public static Matrix add(Matrix A, Matrix B) {
    double[][] a = A.getMatrix();
    double[][] b = B.getMatrix();
    double[][] c = new double[a.length][a[0].length];
    
    for (int i=0;i<a.length;i++) {
      for (int j=0;j<a[0].length;j++) {
        c[i][j] = a[i][j] + b[i][j];
        // loops through and adds each element
      }
    }
    Matrix C = new Matrix(c);
    System.out.println(C);
    return C;
    // returns a new matrix as output
  }
  
  
  public static Matrix subtract(Matrix A, Matrix B) {
    double[][] a = A.getMatrix();
    double[][] b = B.getMatrix();
    double[][] c = new double[a.length][a[0].length];
    
    //loops through and subtracts elements
    for (int i=0;i<a.length;i++) {
      for (int j=0;j<a[0].length;j++) {
        c[i][j] = a[i][j] - b[i][j];
      }
    }
    
    Matrix C = new Matrix(c);
    System.out.println(C);
    return C;
    
  }
  
 
  public static Matrix multiply(Matrix A, Matrix B) {
    double[][] a = A.getMatrix();
    double[][] b = B.getMatrix();
    double[][] c = new double[a.length][b[0].length];
    
    int countRow = 0;
    int countCol = 0;
    while (countRow<A.getRow()) {
      double sum = 0;
      
      double[] tempA = new double[a[0].length];
      for (int i=0;i<a[0].length;i++) {
        tempA[i] = a[countRow][i];
      }
      // puts the row/column of the respective matrices into temporary double[]
      
      double[] tempB = new double[b.length];
      for (int index=0;index<b.length;index++) {
        tempB[index] = b[index][countCol];
      }
      
      for(int i=0;i<tempA.length;i++) {
        // To multiply two matrices, take dot products of each row times each column
        sum = sum + tempA[i]*tempB[i]; 
        // this takes 
      }
      c[countRow][countCol] = sum;
      
      if(countCol==B.getCol()-1) {
        // If each row has been dot multiplied with each column, move on to the next row
      countRow++;
      countCol = 0;
      } else {
       countCol++; 
      }
      
      }
      
    /** for (int i=count;i<a.length;i++) {
      double sum = 0;
      for (int j=0;j<a[0].length;j++) {
        sum = sum + a[i][j]*b[j][i];
      }
      c[i][j] = sum;
    } **/
    
    
    Matrix C = new Matrix(c);
    System.out.println(C);
    return C;
    
  }
  
  public static Matrix crossProduct(Matrix A, Matrix B) {
    // only for [1;2;3] and [2,4,6]
    double[][] a = A.getMatrix();
    double[][] b = B.getMatrix();
    double[][] c = new double[a.length][a[0].length];
    //  a b
    //0 1 5
    //1 2 6
    //2 3 7
    
    c[0][0] = (a[1][0]*b[2][0]-a[2][0]*b[1][0]);
    c[1][0] = (-1)*(a[0][0]*b[2][0]-a[2][0]*b[0][0]);
    c[2][0] = (a[0][0]*b[1][0]-a[1][0]*b[0][0]);
    
    Matrix C = new Matrix(c);
    System.out.println(C);
    return C;
    
  }
  
  public static Matrix scalarMult(Matrix A, double s) {
    double[][] a = A.getMatrix();
    double[][] c = new double[a.length][a[0].length];
    
     for (int i=0;i<a.length;i++) {
      for (int j=0;j<a[0].length;j++) {
       c[i][j] = a[i][j]*s;
      }
    }
    Matrix C = new Matrix(c);
    System.out.println(C);
    return C;
  }
  
  // Row reduce still doesn't work
  
  public static void rowReduce(Matrix A) {
    /**
     [2 3 4]
     [5 6 7]
     **/
    // First take k = [0][0]
    // Multiply row 0 by 1/k
    // If row 1 starts with "z", subtract z*row0 from row1
    // Take k = [1][1]
    // Multiply row 1 by 1/k
    // If row 0 and all the other rows start with xyz, subtract xyz*row0 from rows
    
    //you have an double[][] with [[2,3,4],[5,6,7]]
    
    double[][] c = A.getMatrix();
    
    int rows = A.getRow();
    int cols = A.getCol();
    
    //THE MATRIX INVERSION ALGORITHM
     
    for (int count=0;count<rows;count++) {
    double k = c[count][count];
    c[count] = scalarM(c[count],(1/k));
    // divide the first row by that
    // so now this updates the first row by 1/k
    
    for (int i=0;i<rows;i++) {
      if (i==count) {
       continue; 
        //Skip row reducing the row if it's the k row: it's the one we wanna keep
      }
      // Doesn't actually row reduce row 0; must change to everything EXCEPT if i==count then don't change it that round
      for (int j=0;j<cols;j++) {
        double z = c[i][count];
        double[] temp = new double[c[i].length];
        temp = scalarM(c[count],z);  //takes the k row and multiplies it
        c[i] = subtractM(c[i],temp);
      }   
    }
    
    }
    Matrix C = new Matrix(c);
    
    System.out.println(C);
    

        
  }
  
  
  
  public static double[] scalarM(double[] a, double s) {
    double[] c = new double[a.length];
    for (int i=0;i<c.length;i++) {
     c[i] = a[i]*s; 
    }
    return c;
  }
   
  public static double[] subtractM(double[] a, double[] b) {
    double[] c = new double[a.length];
    for (int i=0;i<a.length;i++) {
      c[i] = a[i]-b[i];
    }
    return c;
  }
  
  
  
  
}