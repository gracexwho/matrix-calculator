import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Matrix {
// creates a Matrix object that countains a double[][] array, which is oriented [rows][cols] respectively
  
  private double[][] rows;
  private int row;
  private int col;
  private boolean isSquare;
  
  
  public Matrix(double[][] r) {
    rows = new double[r.length][r[0].length];
    // populates the multiD array of the matrix
    for (int i=0;i<r.length;i++) {
      for (int j=0;j<r[0].length;j++) {
        rows[i][j] = r[i][j];
      }
    }
    this.row = rows.length;
    this.col = rows[0].length;
    if (this.row==this.col) {
     this.isSquare = true; 
    } else {
     this.isSquare = false; 
    }
    
  }
  
  
  public int getRow() {
   return this.row; 
    
  }
  public int getCol() {
   return this.col; 
  }
  
  public double[][] getMatrix() {
   return this.rows; 
  }
  
 public String toString() {
   // Instead of printing an array, it prints the matrix in a rectangular manner
   double[][] temp = this.getMatrix();
   int row = this.getRow();
   int col = this.getCol();
   for (int i=0;i<row;i++) {
     for (int j=0;j<col;j++) {
      System.out.print(" " + temp[i][j]); 
     }
     System.out.println();
   }
  String output = Arrays.deepToString(this.rows);
   return output;
   
   // 1 2 3
   // 4 5 6
   
  }
  
}