import org.mariuszgromada.math.mxparser.*;
import java.util.*;
/**
 * Write a description of class ExpressionTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ExpressionTest
{
    public static void main(String[] args){
        /*String allArgs="";
        for (int i=0;i<args.length;i++){
            allArgs+=args[i];
        } */
        Scanner input= new Scanner(System.in);
        while (true){
        System.out.println("Enter an expression:  ");
        Expression x=new Expression(input.nextLine());
        System.out.println(x.calculate());
    }
    }
}
