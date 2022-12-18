/*•	There are generally four ways to make an object eligible for garbage collection.
1.	Nullifying the reference variable
2.	Re-assigning the reference variable
3.	An object created inside the method
4.	Island of Isolation
Ways for requesting JVM to run Garbage Collector
•	Once we make an object eligible for garbage collection, it may not destroy immediately by the garbage collector. Whenever JVM runs the Garbage Collector program, then only the object will be destroyed. But when JVM runs Garbage Collector, we can not expect.
•	We can also request JVM to run Garbage Collector. There are two ways to do it : 
1.	Using System.gc() method: System class contain static method gc() for requesting JVM to run Garbage Collector.
2.	Using Runtime.getRuntime().gc() method: Runtime class allows the application to interface with the JVM in which the application is running. Hence by using its gc() method, we can request JVM to run Garbage Collector.
3.	There is no guarantee that any of the above two methods will run Garbage Collector.
4.	The call System.gc() is effectively equivalent to the call : Runtime.getRuntime().gc()



garbage collector(gc) will see 2 objects free. Now to decrement nextId,gc(garbage collector) will call method to finalize() only when we programmers have overridden it in our class. And as mentioned previously, we have to request gc(garbage collector), and for this, we have to write the following 3 steps before closing brace of sub-block.  
1.	Set references to null(i.e X = Y = null;)
2.	Call, System.gc();
3.	Call, System.runFinalization();
Now the correct code for counting the number of employees(excluding interns)  
 */

// Correct code to count number
// of employees excluding interns.

class Employee_emp {

    private int ID;
    private String name;
    private int age;
    private static int nextId = 1;

    // it is made static because it
    // is keep common among all and
    // shared by all objects
    public Employee_emp(String name, int age) {
        this.name = name;
        this.age = age;
        this.ID = nextId++;
    }

    public void show() {
        System.out.println("Id=" + ID + "\nName=" + name
                + "\nAge=" + age);
    }

    public void showNextId() {
        System.out.println("Next employee id will be="
                + nextId);
    }

    protected void finalize() {
        --nextId;
        // In this case,
        // gc will call finalize()
        // for 2 times for 2 objects.
    }
}

public class Employee {
    public static void main(String[] args) {
        Employee_emp E = new Employee_emp("GFG1", 56);
        Employee_emp F = new Employee_emp("GFG2", 45);
        Employee_emp G = new Employee_emp("GFG3", 25);
        E.show();
        F.show();
        G.show();
        E.showNextId();
        F.showNextId();
        G.showNextId();

        {
            // It is sub block to keep
            // all those interns.
            Employee_emp X = new Employee_emp("GFG4", 23);
            Employee_emp Y = new Employee_emp("GFG5", 21);
            X.show();
            Y.show();
            X.showNextId();
            Y.showNextId();
            X = Y = null;
            System.gc();
            System.runFinalization();
        }
        E.showNextId();
    }
}
