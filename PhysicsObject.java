
/**
 * Write a description of class PhysicsObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PhysicsObject
{
    PhysicsObject parent; //Most vectors in this object are relative to its parent.
    PhysicsObject[] children; //These objects have this object as a parent.
    
    boolean constrained=false; //If true, object can not move independently of parent. If false, object can move independently
    // All values are in SI units
    Vector position=new Vector();
    Vector velocity=new Vector();
    Force[] forces=new Force[64]; //All forces acting on object at this moment.
    double mass=0; //Mass of this object only. Use getMass() when doing calculations.
    double time=0; //Internal clock
    
    //3d rotation is complicated. It will come later.
    //Vector angle=new Vector();
    //double roll=0;
    //Vector angularVelocity=new Vector();
    //Vector[] torques=new Vector[64];
    public PhysicsObject() {};
    public PhysicsObject(Vector position,double mass){
        position.addVector(position);
        this.mass=mass;
    }
    public Vector getPosition(){
        return this.position;
    }
    public Vector getVelocity(){
        return this.velocity;
    }
    public boolean isConstrained(){
        return constrained;
    }
    public boolean isInsideOf(Vector point){
     //Used to check if a point intersects with an object's hitbox. Most objects will override this method.
     return this.position.equals(point);
    }
    public double getMass(){
        double childrenMass=0;
        for (int i=0;i<children.length;i++){
            if (children[i].isConstrained()){
            childrenMass+=children[i].getMass();    
            }
        }
        return this.mass+childrenMass;
    }
    public void clearForces(){
        forces=new Force[64];
    }
    public Vector getNetForce(){
        Vector sum=new Vector();
        for(int i=0;i<this.forces.length;i++){
            sum.add(this.forces[i].getValue());
        }
        return sum;
    }
    public Vector getAcceleration(){
        return getNetForce().getQuotient(getMass()); //a=Sum(F)/m
    }
    public void giveForce(Force force){ //Apply force to only this object
        int i=0;
        while (this.forces[i]!=null){
            i++;
        }
        forces[i]=force;
    }
    public void giveForce(PhysicsObject other,Force force){ //Apply force to this object and opposite force to other object(Newton's Third Law)
        this.giveForce(force);
        other.giveForce(force);
    }
    
    public void applyForces(){
        
        clearForces();
    }
    
    

    
}
