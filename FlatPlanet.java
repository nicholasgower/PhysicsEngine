
/**
 * Write a description of class FlatPlanet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FlatPlanet extends PhysicsObject
{
    // instance variables - replace the example below with your own
    Vector standardGravity=new Vector(0,0,-9.80665);

    /**
     * Constructor for objects of class FlatPlanet
     */
    public FlatPlanet()
    {
        super();
    }
    public Force getStandardGravity(PhysicsObject other){
        Vector g=standardGravity;
        
        return new Force(g.getProduct(other.getMass()),this.getPosition());
        
    }
    public boolean isInsideOf(Vector point){
     //The flat planet's hitbox extends infinitely below the z=0 plane.
     return (point.getZ()<0);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
}
