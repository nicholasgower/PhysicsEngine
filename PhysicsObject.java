
/**
 * Write a description of class PhysicsObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PhysicsObject
{
    PhysicsObject parent; //Most vectors in this object are relative to its parent.
    PhysicsObject[] children=new PhysicsObject[64]; //These objects have this object as a parent.
    
    boolean constrained=false; //If true, object can not move independently of parent. If false, object can move independently
    // All values are in SI units
    Vector position=new Vector();
    Vector velocity=new Vector();
    Force[] forces=new Force[64]; //All forces acting on object at this moment.
    double mass=0; //Mass of this object only. Use getMass() when doing calculations.
    double time; //Internal clock
    
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
    public Vector getAbsPosition(){
    Vector absPos=new Vector(this.getPosition());
    PhysicsObject currentParent=this.getParent();
    while (! (currentParent instanceof Universe)){
        absPos.addVector(currentParent.getPosition());
        currentParent=currentParent.parent;
    }
    return absPos;
    }
    public Vector getVelocity(){
        if (constrained){
            return new Vector();
        }else{
            return this.velocity;
        }
    }
    public double getTime(){
        return this.time;
    }
    public void setTime(double time){
        this.time=time;
    }
    public void addTime(double time){
        this.time+=time;
    }
    public PhysicsObject getParent(){
        return parent;    
    }
    public void addParent(PhysicsObject parent){
        this.parent=parent;
    }
    public PhysicsObject[] getChildren(){
        return children;
    }
    public void addChild(PhysicsObject child){
        child.addParent(this);
        
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
            //if (children[i].isConstrained()){
            childrenMass+=children[i].getMass();    
            //}
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
    
    public Force getGravity(){ //Gravity from parent to child
        return new Force();
        
        
    }
    public Force getGravity(PhysicsObject other){ //Gravity between two objects
        //double G= 6.674*Math.pow(10,-11); //Gravitational constant
        //PhysicsObject selfParent=this.parent;
        //Vector selfAb
        //PhysicsObject otherParent=this.parent;
        //while( 
        return getGravity();
    }
    
    public void giveForce(Force force){ //Apply force to only this object
        int i=0;
        while (this.forces[i]!=null){
            i++;
        }
        forces[i]=force;
    }
    
    public void giveForce(Force force,PhysicsObject other){ //Apply force to this object and opposite force to other object(Newton's Third Law)
        this.giveForce(force);
        other.giveForce(force);
    }
    
    public void applyForces(){
        
        
        getVelocity().addVector(getAcceleration().getQuotient(parent.getTime()-this.getTime()));
        this.time=parent.getTime();
        clearForces();
        for (int i=0;i<children.length;i++){
            children[i].applyForces();
        }
    }
    
    

    
}
