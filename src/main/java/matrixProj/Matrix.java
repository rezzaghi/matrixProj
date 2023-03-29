package matrixProj;

import java.io.FileReader;
import java.time.Duration;
import java.util.Scanner;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Matrix {

    public static void main(String[] args) {

	//14L e 10C
        //String fileName = "C:\\Users\\Spopoi\\eclipse-workspace\\matrixProj\\src\\main\\java\\matrix.csv";
        //String fileNameB = "C:\\Users\\Spopoi\\eclipse-workspace\\matrixProj\\src\\main\\java\\matrixB.csv";

        //3L e 2C
        String fileName = "C:\\Users\\Spopoi\\eclipse-workspace\\matrixProj\\src\\main\\java\\matrix1.csv";
        String fileNameB = "C:\\Users\\Spopoi\\eclipse-workspace\\matrixProj\\src\\main\\java\\matrix1B.csv";

        //4L e 5C
        //String fileName = "C:\\Users\\Spopoi\\eclipse-workspace\\matrixProj\\src\\main\\java\\matrix2.csv";
        //String fileNameB = "C:\\Users\\Spopoi\\eclipse-workspace\\matrixProj\\src\\main\\java\\matrix2B.csv";

        Scanner scanner = new Scanner(System.in);
        System.out.print("quantidade de linhas: ");
        int rowsA = scanner.nextInt();
        System.out.print("quantidade de colunas: ");
        int colsA = scanner.nextInt();
        scanner.close();
        
        int rowsB = colsA;
        int colsB = rowsA;

        float[][] matrixA = new float[rowsA][colsA];
        float[][] matrixB = new float[rowsB][colsB];
        float[][] matrixC = new float[rowsA][colsB];
        float[][] matrixD = new float[rowsA][colsB];

        matrixA = getMatrixInput(fileName,rowsA,colsA);
        matrixB = getMatrixInput(fileNameB,rowsB,colsB);
        
        /*
        for (int i = 0; i< matrixA.length; i++) {
            for(int j = 0; j< matrixA[0].length; j++) {
                System.out.print(matrixA[i][j] + ";");
            }
            System.out.println();
        }

            System.out.println();
            System.out.println();
        
        for (int i = 0; i< matrixB.length; i++) {
            for(int j = 0; j< matrixB[0].length; j++) {
                System.out.print(matrixB[i][j] + ";");
            }
            System.out.println();
        }
        */

        multiplyMatrices(matrixA, matrixB, matrixC);
	MultiThread.multiply(matrixA, matrixB, matrixD);


    }


    public static float[][] getMatrixInput(String fileName, int rowSize, int colSize){

        float[][] matrix = new float[rowSize][colSize];

        try {
            FileReader filereader = new FileReader(fileName);
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                                      .withCSVParser(parser)
                                      .build();
      
            java.util.List<String[]> allData = csvReader.readAll();
            
            for (int i = 0; i< matrix.length; i++) {
        	for(int j =0;j <matrix[0].length; j++) {
                    String[] cell = allData.get(i);
                    String crl = cell[j];
        	    matrix[i][j] = Float.parseFloat(crl.replaceAll(",","."));
        	}
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return matrix;
	
    }

    
    public static void multiplyMatrices(float[][] matrix1, float[][] matrix2, float[][] result) {
        int m1Rows = matrix1.length;
        int m1Cols = matrix1[0].length;
        int m2Rows = matrix2.length;
        int m2Cols = matrix2[0].length;
        
        if (m1Cols != m2Rows) {
            throw new IllegalArgumentException("Numero de colunas da primeira matrix precisa ser igual ao numero de linhas da segunda");
        }
        
        
 	long startTime = System.nanoTime();
        for (int i = 0; i < m1Rows; i++) {
            for (int j = 0; j < m2Cols; j++) {
                int sum = 0;
                for (int k = 0; k < m1Cols; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                result[i][j] = sum;
            }
        }
        long endTime = System.nanoTime();
	long totalTime = endTime - startTime;
	Duration d = Duration.ofNanos(totalTime);
	System.out.println("single: " + d);
        
    }
}