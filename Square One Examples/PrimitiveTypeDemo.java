//Adam Clemons 7/17/17
// Adam Clemons 7/15/17
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.lang.Math;

public class PrimitiveTypeDemo{
    private static String stringDemo;
    private static int intDemo;
    private static float floatDemo;
    private static boolean boolDemo;
    private static char charDemo;
    private static Long longDemo;
    private static Double doubleDemo;
    private static Byte byteDemo;
    private static Short shortDemo;

    private static final Logger logger = Logger.getLogger(PrimitiveTypeDemo.class.getName());


    public static void main(String[] args){
        logger.setLevel(Level.ALL);
        stringDemoRunner();
    }

    private static void stringDemoRunner(){
        stringDemo = "I can't let you do that Dave";
        logger.info("Original String Value: "+ stringDemo);
        boolDemo = stringDemo.contains("can't");
        logger.info("String.contains... Does this demo string have \"can't\" in it?");
        logger.info("" + boolDemo); // Java is helpful sometimes... converts bool to string in string concat operation

        logger.info("String to caps.  String.toUpperCase()"); // more than one way to do this. 
        String upper = stringDemo.toUpperCase();
        logger.info(upper);
        String lower = stringDemo.toLowerCase();
        logger.info("String to lower. String.toLowerCase()" );
        logger.info(lower);
    }

    

}