
//Adam Clemons 07/17/17
import java.util.Date;

 public interface DemoInterface {
    public String HelloWorld();

    public Date Today();
}

class DemoInterfaceImpl implements DemoInterface{
    
    public String HelloWorld(){
        return "Hello World";
    }

    public Date Today(){
        Date now = new Date();
        return now;
    }
    
}


class DemoInterfaceRunner{
    public static void main(String args[]){
        DemoInterfaceImpl demo = new DemoInterfaceImpl();
        System.out.println("HelloWorld() called");
        System.out.println(demo.HelloWorld());
        System.out.println("Today() called");
        System.out.println(demo.Today());

    }
}