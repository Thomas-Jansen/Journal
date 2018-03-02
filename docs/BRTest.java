import java.io.*;

import java.util.Locale;

public class BRTest {
    private int calls = 0;
    private int successfulCalls = 0;
    private int totalReturned = 0;
    int[] excepCounts = new int[5];
    
    public void callIt() {
        calls += 1;
        try {
            int random = BadRandom.randVal();
            if (random >= 0) {
                successfulCalls += 1;
                totalReturned += random;
            }
        }
        catch (ArithmeticException ae) {
            System.out.println(ae);
            excepCounts[0] += 1;
        }
        catch (NullPointerException npe) {
            System.out.println(npe);
            excepCounts[1] += 1;
        }
        catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println(aioobe);
            excepCounts[2] += 1;
        }
        catch (ClassCastException cce) {
            System.out.println(cce);
            excepCounts[3] += 1;
        }
        catch (NegativeArraySizeException nase) {
            System.out.println(nase);
            excepCounts[4] += 1;
        }
        
    }
    
    public void resetCounts() {
        calls = 0;
        successfulCalls = 0;
        totalReturned = 0;
        for (int i = 0; i < excepCounts.length; i++) {
            excepCounts[i] = 0;
        }
    }
    
    public void nRandInts(int n) {
        for (int i = 0; i < n; i++) {
            int x = successfulCalls;
            callIt();
            if (x == successfulCalls) {
                i--;
            }
        }
        
    }
    
    public void writeData() {
        System.out.println("-------------------------------------------------------");
        System.out.println("Number of calls: " + calls);
        System.out.println("Successful calls: " + successfulCalls);
        System.out.println("Total returned: " + totalReturned);
        System.out.println("Percentage Arithmetic Exceptions " + (float) (excepCounts[0]*100)/calls);
        System.out.println("Percentage Null Pointer Exceptions: " + (float) (excepCounts[1]*100)/calls);
        System.out.println("Percentage Array Index Exceptions: " + (float) (excepCounts[2]*100)/calls);
        System.out.println("Percentage Class Cast Exceptions: " + (float) (excepCounts[3]*100)/calls);
        System.out.println("Percentage Negative Array Exceptions: " + (float) (excepCounts[4]*100)/calls);
        System.out.println("Percentage of successful calls: " + (float) (successfulCalls*100)/calls);
        System.out.println("-------------------------------------------------------");
        
    }
    
    public static void main(String[] args) {
        BRTest me = new BRTest();
        me.resetCounts();
        me.nRandInts(30);
        me.writeData();
    }
}