// Adam Clemons 7/15/17
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.lang.Math;

public class PrimitiveOperatorDemo{

    private static final Logger logger = Logger.getLogger(PrimitiveOperatorDemo.class.getName());
    public static void main(String[] args){
        logger.setLevel(Level.ALL);
        //Good opportunity to talk Static vs Non-Static 
        IncrimentDecrimentAssignment();


    }

    private static void IncrimentDecrimentAssignment(){
        int i = 0;
        logger.info("Beginning IncrementDecrimentAsignment() Operator Demo");
        logger.info("i = " + (i));
        i++;
        logger.info("i++;  i = " + (i));
        i += 3;
        logger.info("i += 3; i = " + (i));
        i -= 1;
        logger.info("i -= 1; i = " + (i));
        i *= 3;
        logger.info("i *= 3; i = " + (i));
        i /= 2;
        logger.info("i /= 2; i = " + (i));
        i %= 3;
        logger.info("i %= 3; i = " + (i));
    }

    public static void TernaryOperatorDemo(){
        int randNum = 4 + (int)(Math.random() * 7);
        logger.info("i is 12, unless the random number is 6, and then i is 6");
        logger.info(" i = (randNum == 6) ? 6 : 12;");
        int i = (randNum == 6) ? 6 : 12;
        logger.info("randNum = " + (i));
        logger.info("i = " + (i));
    }
}