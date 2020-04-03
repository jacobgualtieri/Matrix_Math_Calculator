import java.util.LinkedList;
import java.util.List;

class Matrix {
    private LinkedList<Vector> columnVectors;

    public Matrix(LinkedList<Vector> vectorList){
        this.columnVectors = vectorList;
    }

    /**
     * Retrieve the column vector at the specified index from the list of column vectors
     * @param columnIndex The index at which to look for the vector
     * @return A specific vector from the columnVectors List;
     */
    public Vector getColumnVector(int columnIndex){
        return columnVectors.get(columnIndex);
    }

    /**
     * Get the number of rows within this matrix
     * @return The length of the array of coordinates within a vector object within this matrix
     */
    public int getNumRows(){
        return columnVectors.get(0).getComponentsList().length;
    }

    /**
     * Retrieve the row vector at the specified index from the list of column vectors
     * @param rowIndex The index at which to look for the vector
     * @return A new vector created from the coordinates at rowIndex from each column vector;
     */
    public Vector getRowVector(int rowIndex){
        double[] numArray = new double[columnVectors.size()];
        for (int i = 0; i < columnVectors.size(); i++){
            numArray[i] = columnVectors.get(i).getComponent(rowIndex);
        }
        return new Vector(numArray);
    }

    /**
     * Multiply each value within the matrix by the scaleFactor
     * @param scaleFactor The number by which each number in the matrix would be multiplied
     */
    public void scaleBy(double scaleFactor){
        for (Vector columnVector : columnVectors) {
            columnVector.scaleBy(scaleFactor);
        }
    }

    /**
     * Determine if this matrix is square
     * @return boolean - number of rows == number of columns
     */
    public boolean isSquareMatrix(){
        return this.columnVectors.size() == this.getNumRows();
    }

    public void addMatrix(Matrix m){
        for (int i = 0; i < this.columnVectors.size(); i++){
            this.getColumnVector(i).addVector(m.getColumnVector(i));
        }
    }

    public double determinant(){
        double result;
        if(this.isSquareMatrix()){
            if(this.getNumRows() == 2){
                double a = columnVectors.get(0).getComponent(0)*columnVectors.get(1).getComponent(1);
                double b = columnVectors.get(0).getComponent(1)*columnVectors.get(1).getComponent(0);
                result = a - b;
            }
            else{
                result = 0; // Not yet implemented
            }
        }
        else{
            System.out.println("Matrix must be square");
            result = 0;
        }
        return result;
    }

    public static Matrix multiply(Matrix mainMatrix, Matrix otherMatrix){
        LinkedList<Double> pp = new LinkedList<>();
        int commonDimension = mainMatrix.columnVectors.size();
        if(commonDimension == otherMatrix.getNumRows()){
            LinkedList<Vector> protoMatrix = new LinkedList<>();
            for(int i = 0; i < otherMatrix.columnVectors.size(); i++){
                double[] intermediateArray = new double[mainMatrix.getNumRows()];
                for(int j = 0; j < mainMatrix.getNumRows(); j++){
                    intermediateArray[j] = otherMatrix.getColumnVector(i).dotProduct(mainMatrix.getRowVector(j));
                }
                protoMatrix.add(new Vector(intermediateArray));
            }
            return new Matrix(protoMatrix);
        }
        else{
            System.out.println("ERROR: Invalid matrix size");
            return null;
        }
    }

    @Override
    public String toString(){
        String result = "[  ";
        for (int rowNum = 0; rowNum < this.getNumRows(); rowNum++){
            for(Vector columnVector : columnVectors){
                result += columnVector.getComponent(rowNum) + "  ";
            }
            if(rowNum == this.getNumRows()-1){
                result += "]";
            }
            else{
                result += "]\n[  ";
            }
        }
        return result;
    }

    public static void main(String[] args){

        double[] testArray1 = {1,3,5};
        double[] testArray2 = {2,4,6};

        double[] testArray3 = {0,1};
        double[] testArray4 = {1,0};

        double[] testArray5 = {4,2,0};
        double[] testArray6 = {3,1,-1};

        Matrix matrix1 = new Matrix(new LinkedList<>(List.of(
                new Vector(testArray1),
                new Vector(testArray2))));

        Matrix matrix2 = new Matrix(new LinkedList<>(List.of(
                new Vector(testArray3),
                new Vector(testArray4))));

        Matrix matrix3 = new Matrix(new LinkedList<>(List.of(
                new Vector(testArray5),
                new Vector(testArray6))));

        System.out.println(matrix1.toString()+"\n");
        System.out.println(matrix2.toString());
        double det_matrix_2 = matrix2.determinant();
        System.out.println(det_matrix_2);
        System.out.println("\nMultiply Test:");
        Matrix multMatrix = multiply(matrix1, matrix2);
        System.out.println(multMatrix.toString());
        System.out.println();

        matrix1.addMatrix(matrix3);
        System.out.println(matrix1);
    }
}