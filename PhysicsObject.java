 

import java.util.ArrayList;
/**
 * Write a description of class PhysicsObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PhysicsObject
{
    PhysicsObject parent; //Most vectors in this object are relative to its parent.
    ArrayList<PhysicsObject> children=new ArrayList<PhysicsObject>(); //These objects have this object as a parent.
    
    boolean airResistance=false;
    boolean constrained=false; //If true, object can not move independently of parent. If false, object can move independently
    // All values are in SI units
    Vector position=new Vector();
    Vector velocity=new Vector();
    ArrayList<Force> forces=new ArrayList<Force>(); //All forces acting on object at this moment.
    double mass=0; //Mass of this object only. Use getMass() when doing calculations.
    double time; //Internal clock
    Vector standardGravity=new Vector(0,0,0);
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
       
        return absPos;
    }   
    public Vector getAbsVelocity(){
    Vector absVel=new Vector(this.getVelocity());
    PhysicsObject currentParent=this.getParent();
    while (! (currentParent instanceof Universe)){
        absVel.addVector(currentParent.getVelocity());
        currentParent=currentParent.parent;
    }
    return absVel;
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
        this.time=parent.getTime();
    }
    public ArrayList<PhysicsObject> getChildren(){
        return children;
    }
    public void addChild(PhysicsObject child){
        child.addParent(this);
        this.children.add(child);
    }
    
    public boolean isConstrained(){
        return constrained;
    }
    public boolean isAirResistance(){
        if (airResistance){
            return true;
        }else{
            return parent.isAirResistance();
        }
        
    }
    public void setAirResistance(boolean other){
        this.airResistance=other;
    }
    public boolean isInsideOf(Vector point){
     //Used to check if a point intersects with an object's hitbox. Most objects will override this method.
     return this.position.equals(point);
    }
    public double getMass(){
        double childrenMass=0;
        for (int i=0;i<children.size();i++){
            //if (children[i].isConstrained()){
            childrenMass+=children.get(i).getMass();    
            //}
        }
        return this.mass+childrenMass;
    }
    public Force getAirResistance(){
        return new Force();
    }
    public double getMomentofInertia(){
        return 0;
    }
    public void clearForces(){
        forces.clear();
    }
    public Vector getNetForce(){
        Vector sum=new Vector();
        for(int i=0;i<this.forces.size();i++){
            sum.add(this.forces.get(i).getValue());
        }
        return sum;
    }
    public Vector getAcceleration(){
        return getNetForce().getQuotient(getMass()); //a=Sum(F)/m
    }
    
    public Force getGravity(){ //Gravity from parent to child
        //return new Force(new Vector(0,0,-9.80665));
        
        return new Force();
    }
    public Force getStandardGravity(PhysicsObject other){
        return new Force();
    }
    public Force getGravity(PhysicsObject other){ //Gravity from this object to other
        if (other instanceof FlatPlanet){
            return other.getStandardGravity(this);
        }
        double G= 6.674*Math.pow(10,-11); //Gravitational constant
        Vector distance=this.getAbsPosition().getDifference(other.getAbsPosition());
        Vector F=(distance.getUnitVector()).getProduct((G*this.getMass()*other.getMass())/(Math.pow(distance.getMagnitude(),2))); 
        return new Force(F,this.getPosition());
    }
    public void addGravity(){
        if (parent instanceof FlatPlanet){
            addForce(parent.getStandardGravity(this));
        }else{
            addForce(getGravity(parent),parent);
        }
    }
    public void addForce(Force force){
        forces.add(force);
    }
    public void giveForce(Force force){ //Apply force to only this object
        forces.add(force);
    }
    public void addForce(Force force,PhysicsObject other){ //Apply force to this object and opposite force to other object(Newton's Third Law)
        this.giveForce(force);
        other.giveForce(force.getNegative());
    }
    public void giveForce(Force force,PhysicsObject other){ //Apply force to this object and opposite force to other object(Newton's Third Law)
        this.giveForce(force);
        other.giveForce(force.getNegative());
    }
    //public void defineChanges(){ 
        
    //}
    public void update(){
        
        applyChanges();
        for (int i=0;i<children.size();i++){
            children.get(i).update();
        }
    }
    public void applyChanges(){
        if (! (this instanceof Universe) && parent.getTime()-this.getTime()!=0){
        position.addVector(getVelocity().getProduct(parent.getTime()-this.getTime()));
        velocity.addVector(getAcceleration().getProduct(parent.getTime()-this.getTime()));
        this.time=parent.getTime();
        clearForces();
    }
        
    }
    
    

    
}
