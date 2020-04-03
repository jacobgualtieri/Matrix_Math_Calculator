public class Vector{
    /**
     * private fields a, b, and c: The x, y, and z components of a three dimensional vector.
     */
    private double[] componentsList;

    public Vector(double[] numList){
        this.componentsList = numList;
    }

    public Vector(Vector originalVector){
        this.componentsList = originalVector.componentsList;
    }

    /**
     * Get the list of opponents held by this vector object
     * @return the component list
     */
    public double[] getComponentsList() {
        return componentsList;
    }

    /**
     * @return the list of components
     */
    public double getComponent(int componentIndex) {
        return componentsList[componentIndex];
    }

    /**
     * Add the components of another vector, otherVector, to the components of this vector
     * @param otherVector The second vector that will be added to this vector
     */
    public void addVector(Vector otherVector){
        for(int i = 0; i < componentsList.length; i++){
            this.componentsList[i] = this.componentsList[i] + otherVector.componentsList[i];
        }
    }

    /**
     * @param o The vector with which the dot product will be performed.
     * @return The scalar result of the dot product between the vector being referenced and vector o.
     */
    public double dotProduct(Vector o){
        double result = 0;
        if(o.componentsList.length == this.componentsList.length){
            for(int i = 0; i < componentsList.length; i++){
                result += componentsList[i]*o.componentsList[i];
            }
        }
        return result;
    }

    /**
     * @param o The vector with which the cross product will be performed.
     * @return The vector result of the cross product between the vector being referenced and vector o.
     */
    public Vector crossProduct(Vector o){
        double[] temporaryArray = new double[3];
        if (this.componentsList.length == 3 && o.componentsList.length == 3){
            temporaryArray[0] = (this.getComponent(1)*o.getComponent(2)) - (this.getComponent(2)*o.getComponent(1));
            temporaryArray[1] = -1*((this.getComponent(0)*o.getComponent(2)) - (this.getComponent(2)*o.getComponent(0)));
            temporaryArray[2] = (this.getComponent(0)*o.getComponent(1)) - (this.getComponent(1)*o.getComponent(0));
        }
        return new Vector(temporaryArray);
    }

    /**
     * @return The scalar magnitude of the vector being referenced.
     */
    public double getMagnitude(){
        double radicand = 0;
        for (double v : componentsList) {
            radicand += Math.pow(v, 2);
        }
        return Math.sqrt(radicand);
    }

    /**
     * Multiply each component of this vector by the scaleFactor
     * @param scaleFactor The scalar value by which the vector being referenced will be multiplied.
     */
    public void scaleBy(double scaleFactor){
        for(int i = 0; i < componentsList.length; i++){
            this.componentsList[i] = scaleFactor*this.componentsList[i];
        }
    }

    public static Vector scalarProduct(Vector v, double scaleFactor){
        double[] newComponentList = new double[v.getComponentsList().length];
        for(int i = 0; i < v.componentsList.length; i++){
            newComponentList[i] = scaleFactor*v.componentsList[i];
        }
        return new Vector(newComponentList);
    }

    /**
     * @param o The vector onto which this vector is projecting itself.
     * @return The vector projection of the vector being referenced onto Vector o.
     */
    public Vector projection(Vector o){
        double a_dot_b = this.dotProduct(o);
        Vector numerator = scalarProduct(o, a_dot_b);
        double magnitude_squared = Math.pow(o.getMagnitude(),2);
        double denominator = 1.0/magnitude_squared;
        return scalarProduct(numerator, denominator);
    }

    public double comp(Vector o){
        return this.projection(o).getMagnitude();
    }

    /**
     * Determine the sine of the the angle between this vector and the vector v
     * @param v the second vector associated with the angle theta
     * @return the sine of theta
     */
    public double sineTheta(Vector v){
        double numerator = this.crossProduct(v).getMagnitude();
        double denominator = this.getMagnitude()*v.getMagnitude();
        return numerator/denominator;
    }

    /**
     * Determine the cosine of the the angle between this vector and the vector v
     * @param v the second vector associated with the angle theta
     * @return the cosine of theta
     */
    public double cosineTheta(Vector v){
        double numerator = this.dotProduct(v);
        double denominator = this.getMagnitude()*v.getMagnitude();
        return numerator/denominator;
    }

    public boolean equals(Vector o){
        return this.componentsList == o.componentsList;
    }

    @Override
    public String toString() {
        StringBuilder resultStr = new StringBuilder("<");
        for(int i = 0; i < componentsList.length; i++){
            if (i < componentsList.length-1){
                resultStr.append(this.componentsList[i]).append(", ");
            }
            else {
                resultStr.append(this.componentsList[i]).append(">");
            }
        }
        return resultStr.toString();
    }
}