

import java.util.ArrayList;
/**
 * Write a description of class Universe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Universe extends PhysicsObject
{
    public Universe(ArrayList<PhysicsObject> children){
    this.mass=0;
    this.children=children;
    this.parent=null; //This is the ultimate object; all objects are ultimately the parent to this object.
    
    }
    public Vector getPosition(){
        return new Vector();
    }
    public Vector getVelocity(){
        return new Vector();
    }
    public Vector getNetForce(){
        return new Vector();
    }
  
    public PhysicsObject getParent(){
    return null;    
    }
    public boolean isInsideOf(Vector point){
     return false;
    }
}
