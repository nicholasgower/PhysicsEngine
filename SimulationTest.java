 

import java.util.ArrayList;
/**
 * Write a description of class SimulationTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SimulationTest
{
    public static void main(String[] args){
    PhysicsObject mass= new PhysicsObject(new Vector(),4); //Point mass at origin with mass of 4 kg
    PhysicsObject earth=new FlatPlanet(); //
    earth.addChild(mass);
    ArrayList<PhysicsObject> children=new ArrayList<>();
    children.add(earth);
    Universe world = new Universe(children);
    System.out.println(mass.getPosition());
    System.out.println(mass.getMass());
    }
}
