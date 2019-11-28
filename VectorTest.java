
/**
 * Write a description of class VectorTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class VectorTest
{
    // instance variables - replace the example below with your own
    public static void main(String[] args){
        Vector a= new Vector(2,1,2);
        Vector b= new Vector(2,2,2);
        
        System.out.printf("a: %s\n",a);
        System.out.printf("b: %s\n",b);
        System.out.printf("|a|: %f\n",a.getMagnitude());
        System.out.printf("|b|: %f\n",b.getMagnitude());
        
        System.out.printf("a dot b: %f\n",a.dotProduct(b));
        System.out.printf("a cross b: %s\n",a.crossProduct(b));
    }
}
