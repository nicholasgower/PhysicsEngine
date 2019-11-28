
/**
 * Write a description of class Force here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Force
{
    // instance variables - replace the example below with your own
    PhysicsObject source; //From who this force came from
    PhysicsObject appliedTo; //To whom this force applies to
    
    Vector value=new Vector();
    Vector origin=new Vector(); //Where force is applied-- Important to rotation, but not to linear movement

    /**
     * Constructor for objects of class Force
     */
    public Force(Vector value,PhysicsObject source,PhysicsObject appliedTo)
    {
        // initialise instance variables
        this.value=value;
        this.origin=origin;
    }
    public Force()
    {
        
        //Non-existent force
    }
    
    public Force getNegative(){
        return new Force(this.value.getNegative(),this.source,this.appliedTo);
    }
    public Vector getValue(){
        return this.value;
    }
    public Vector getOrigin(){
        return this.origin;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
}
