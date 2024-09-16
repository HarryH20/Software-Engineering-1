package edu.baylor.ecs.csi3471.HasslerHarrison.module3;

public class Matrix {
    private int[][] matrix;
    private int height;
    private int width;
    public Matrix(int width, int height){
        this.width = width;
        this.height = height;
        this.matrix = new int[height][width];
        for(int i = 0; i < height; i++ ){
            for(int j = 0; j < width; j++){
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }

    }

    public Matrix(int[][] matrix){
        this.width = matrix.length;
        this.height = matrix[0].length;
        this.matrix = matrix;
    }

    public Matrix add(Matrix otherMatrix) throws IllegalArgumentException{
        if(height != otherMatrix.height || width != otherMatrix.width ){
            throw new IllegalArgumentException("Matrix cannot be added");
        }

        int[][] result = new int[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                result[i][j] = matrix[i][j] + otherMatrix.matrix[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix subtract(Matrix otherMatrix) throws IllegalArgumentException{
        if(height != otherMatrix.height || width != otherMatrix.width ){
            throw new IllegalArgumentException("Matrix cannot be subtracted");
        }

        int[][] result = new int[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                result[i][j] = matrix[i][j] - otherMatrix.matrix[i][j];
            }
        }
        return new Matrix(result);
    }


    public Matrix multiply(Matrix otherMatrix) throws IllegalArgumentException{
        if(height != otherMatrix.width){
            throw new IllegalArgumentException("Matrix cannot be multiplied");
        }

        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < otherMatrix.width; j++) {
                for (int k = 0; k < width; k++) {
                    result[i][j] += matrix[i][k] * otherMatrix.matrix[k][j];
                }
            }
        }
        return new Matrix(result);


    }

    public void printMatrix(){
        for(int[] row: matrix){
            for(int value: row){
                System.out.print( value + " ");

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Matrix matrix1 = new Matrix(3, 3);
        Matrix matrix2 = new Matrix(3, 3);
        System.out.println("Matrix Addition:");
        matrix1.printMatrix();
        System.out.println("+");
        matrix2.printMatrix();
        System.out.println("=");
        matrix1.add(matrix2).printMatrix();



        Matrix matrix3 = new Matrix(3, 3);
        Matrix matrix4 = new Matrix(3, 3);
        System.out.println("Matrix Subtraction:");
        matrix3.printMatrix();
        System.out.println("-");
        matrix4.printMatrix();
        System.out.println("=");
        matrix3.subtract(matrix4).printMatrix();


        Matrix matrix5 = new Matrix(2, 2);
        Matrix matrix6 = new Matrix(2, 2);
        System.out.println("Matrix Multiplication:");
        matrix5.printMatrix();
        System.out.println("*");
        matrix6.printMatrix();
        System.out.println("=");
        matrix5.multiply(matrix6).printMatrix();

    }
}



