
/**
 * Write a description of class Plane here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plane
{
    //https://en.wikipedia.org/wiki/Plane_(geometry)
    Vector position=new Vector();
    Vector vec1;
    Vector vec2;

    /**
     * Constructor for objects of class Plane
     */
    public Plane(Vector vec1,Vector vec2)
    {
        this.vec1=vec1;
        this.vec2=vec2;
    }
    public double getValue(double x, double y){
        
    }
    public Plane(){
        this.vec1=new Vector(1,0,0);
        this.vec2=new Vector(0,1,0);
    }
    

    
}
