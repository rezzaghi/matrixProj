package matrixProj;

public class WorkerThread implements Runnable{
    private final float[][] result;
    private float[][] matrix1;
    private float[][] matrix2;
    private final int row;

    public WorkerThread(float[][] result, float[][] matrix1, float[][] matrix2, int row) {
     this.result = result;
     this.matrix1 = matrix1;
     this.matrix2 = matrix2;
     this.row = row;
    }

    @Override
    public void run() {

     for (int i = 0; i < matrix2[0].length; i++) {
      result[row][i] = 0;
      for (int j = 0; j < matrix1[row].length; j++) {
       result[row][i] += matrix1[row][j] * matrix2[j][i];

      }

     }

    }

}
