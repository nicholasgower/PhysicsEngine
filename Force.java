 


/**
 * Write a description of class Force here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Force
{
   
    
    Vector value=new Vector();
    Vector origin=new Vector(); //Where force is applied-- Important to rotation, but not to linear movement
    
    /**
     * Constructor for objects of class Force
     */
    public Force(Vector value,Vector origin)
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
        return new Force(this.value.getNegative(),this.getOrigin());
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
