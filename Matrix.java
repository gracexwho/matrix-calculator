import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Matrix {
  
  private double[][] rows;
  private int row;
  private int col;
  private boolean isSquare;
  
  
  public Matrix(double[][] r) {
    rows = new double[r.length][r[0].length];
    
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
   String output = Arrays.deepToString(this.rows);
   return output;
  }
  
}