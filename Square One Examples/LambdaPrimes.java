import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class LambdaPrimes {
    private static Logger log;

    public static void main(String[] args) {
        log = Logger.getLogger(LambdaPrimes.class.getName());
        //ConsoleHandler handle = new ConsoleHandler();
        //handle.setFormatter(new SimpleFormatter());
        //log.addHandler(handle);
        //Main Method
        log.setLevel(Level.ALL);
        //handle.setLevel(Level.ALL);
        log.config("Arg0: " + args[0]);
        log.config("Arg1: " + args[1]);

        int lowerBound = Integer.parseInt(args[0]);
        int upperBound = Integer.parseInt(args[1]);

        //For measuring run time
        long startTime = System.currentTimeMillis();
        parallelPrimeTester(lowerBound, upperBound);
        long endTime = System.currentTimeMillis();
        long parallelRunTime = endTime - startTime;
        
        startTime = System.currentTimeMillis();
        forLoopPrimeTester(lowerBound, upperBound);
        endTime = System.currentTimeMillis();
        long forLoopRunTime = endTime - startTime;

        log.info("Run time for parallelPrimeTester is  " + (parallelRunTime) + " Milliseconds");

        log.info("Run time for forLoopPrimeTester is  " + (forLoopRunTime) + " Milliseconds");
    }

    private static void parallelPrimeTester(int lowerBound, int upperBound) {
        IntStream.range(lowerBound, upperBound).parallel().forEach(i -> {
            if (isPrime(i)) {
                log.fine("Value: " + i + " is Prime");
            }
        });
    }

    private static boolean isPrime(int toTest) {
        //Lambda's look really hard to read at first.
        // IntPredicate javaDocs - https://docs.oracle.com/javase/8/docs/api/java/util/function/class-use/IntPredicate.html
        // This is the test is divisible is true if toTest % index = 0, where index is an inputStream.
        IntPredicate isDivisible = index -> toTest % index == 0;
        //Performing the test and returning the results in one line... because I like short code. 
        // Test runs from 2 to toTest-1, 1 and toTest are always factors... cause Integers.
        return toTest > 1 && IntStream.range(2, toTest - 1).noneMatch(isDivisible); //Adding parallel shaved another 1s

    }

    private static void forLoopPrimeTester(int lowerBound, int upperBound) {
        for (int i = lowerBound; i <= upperBound; i++) {
            if (isPrimeSlow(i)) {
                log.fine("Value: "+ i + " is Prime");
            }
        }
    }

    private static boolean isPrimeSlow(int toTest) {
        // With speedup break, this still takes an average of 500ms longer than isPrime(). 
        boolean prime = true; //This is designed to mirror the IntPredicate from the lamda example
        for (int i = 2; i < toTest; i++) {
            if ((toTest % i) == 0) {
                prime = false;
            }
            if (prime == false) {
                break; //To Speed things up, by roughly 250%
            }
        }
        return prime;
    }
}