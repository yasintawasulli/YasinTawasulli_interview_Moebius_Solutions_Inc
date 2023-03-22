package moebiusSolutionsInc_TestCode;

/*
The following program produces inconsistent results. It should always output
this:
```
counter is 20000
 */
public class Q5_WrongAnswer {
    private int counter = 0;
    public static void main(String[] args) {
        new Q5_WrongAnswer().run();
    }
    private void run() {
        try {
            Thread t1 = new Thread(this::incrementToOnHundred);
            Thread t2 = new Thread(this::incrementToOnHundred);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("counter is " + counter);
        } catch (InterruptedException e) {
            System.err.println("fatal error, unexpected interrupt exception");
            System.exit(2);
        }
    }

    private void incrementToOnHundred() {
        for (int i = 0; i < 10_000; i++) {
            doSomeFakeWork();
        }
    }

    // since multiple thread executes this method and it is not thread safe, program produces inconsistent results.
    // we can make it thread safe with synchronized keyword
    private synchronized void doSomeFakeWork() {
        counter++;
    }
}