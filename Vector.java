
/**
 * Write a description of class Vector here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vector
{
    // instance variables - replace the example below with your own
    private double x=0;
    private double y=0;
    private double z=0;
    /**
     * Constructor for objects of class Vector
     */
    public Vector() {}
    public Vector(double[] values){
        this.x=values[0];
        this.y=values[1];
        this.z=values[2];
    }
    public Vector(Vector in){
        double[] values=in.toArray();
        this.x=values[0];
        this.y=values[1];
        this.z=values[2];
    }
    public Vector(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Vector(double x,double y){
        this.x=x;
        this.y=y;
    }
    public Vector(double x){
        this.x=x;
    }
    
    //Getters, setters and adders
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getZ(){
        return this.z;
    }
    public void setX(double in){
        this.x=in;
    }
    public void setY(double in){
        this.y=in;
    }
    public void setZ(double in){
        this.z=in;
    }
    public void addX(double in){
        this.x+=in;
    }
    public void addY(double in){
        this.y+=in;
    }
    public void addZ(double in){
        this.z+=in;
    }
    public void multiplyX(double in){
        this.x*=in;
    }
    public void multiplyY(double in){
        this.y*=in;
    }
    public void multiplyZ(double in){
        this.z*=in;
    }
    
    
    public double[] toArray(){
    double[] output={x,y,z};
    return output;
    }
    public String toString(){
        return String.format("[%f,%f,%f]",x,y,z);
    }
    //Indirect getters
    public Vector getNegative(){
        return new Vector(-this.x,-this.y,-this.z);
    }
    public Vector getAbs(){
        double[] output=this.toArray();
        for(int i=0;i<output.length;i++){
            output[i]= Math.abs(output[i]);
        }
        return new Vector(output);
    }
    public Vector getSum(Vector other){
        
        return new Vector(this.getX()+other.getX(),this.getY()+other.getY(),this.getZ()+other.getZ());
    }
    public Vector getProduct(double other){
        
        return new Vector(this.getX()*other,this.getY()*other,this.getZ()*other);
    }
    public Vector getQuotient(double other){
        
        return getProduct(1/other);
    }
    public Vector getDifference(Vector other){
        
        return getSum(other.getNegative());
    }
    public double distanceTo(Vector other){
        return this.getSum(other.getNegative()).getMagnitude();
        
    }
    public double getMagnitude(){
        double[] array=this.toArray();
        double sum=0;
        for(int i=0;i<array.length;i++){
            sum+=Math.pow(array[i],2);
            
        }
        return Math.sqrt(sum);
    }
    public Vector getUnitVector(){
        return getQuotient(getMagnitude());
    }
    public double dotProduct(Vector other){
       double[] _this=this.toArray();
       double[] _other=other.toArray();
       double sum=0;
       for(int i=0;i<_this.length;i++){
            sum+=_this[i]*_other[i];
            
       }
        return sum;
        
    }
    public Vector crossProduct(Vector other){
       double[] _this=this.toArray();
       double[] _other=other.toArray();
       return new Vector(_this[1]*_other[2]-_this[2]*_other[1],_this[2]*_other[0]-_this[0]*_other[2],_this[0]*_other[1]-_this[1]*_other[0]);
    }
    //Vector adders
    public void addVector(Vector other){
        this.addX(other.getX());
        this.addY(other.getY());
        this.addZ(other.getZ());
    }
    public void add(Vector other){
        this.addVector(other);
    }
    public void addVector(double x,double y,double z){
        this.addX(x);
        this.addY(y);
        this.addZ(z);
    }
    public void subtractVector(Vector other){
        this.addVector(other.getNegative());
    }
    public void multiplyScalar(double other){
        this.multiplyX(other);
        this.multiplyY(other);
        this.multiplyZ(other);
    }
    public void divideScalar(double other){
        this.multiplyScalar(1/other);
    }
    public boolean equals(Vector other){
        return (this.getX()==other.getX() && this.getY()==other.getY() && this.getZ()==other.getZ());
    }
    public void set(double x,double y,double z){
    setX(x);
    setY(y);
    setZ(z);
    }
    
    
    
    
}
