import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Prueba {
    public static final int THREAD_NUM = 3;
    public static final int MULTIPLICATION_NUM = 10;

    public static void main(String[] args) {
        ThreadPoolExecutor l_Executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_NUM);
        int l_Num1 = 0;
        int l_Num2 = 0;
        int l_Counter = 0;
        Multiplication l_Task = new Multiplication(0, 0);
        @SuppressWarnings("unchecked")
        List<Future<Integer>> l_ResultList = null;
        List<Multiplication> l_TaskList = new ArrayList<>();

        for (l_Counter = 1; l_Counter < MULTIPLICATION_NUM; l_Counter++) {
            l_Num1 = (int) (Math.random() * 10) + 1;
            l_Num2 = (int) (Math.random() * 10) + 1;
            l_TaskList.add(new Multiplication(l_Num1, l_Num2));
        }

        try {
            l_ResultList = l_Executor.invokeAll(l_TaskList);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        try {
            for (Future<Integer> l_Item : l_Executor.invokeAll(l_TaskList)) {
                System.out.println(l_Item.get());
            }
        } catch (Exception e) {
            System.out.println("a");
        }

        l_Executor.close();
    }
}

class Multiplication implements Callable<Integer> {
    int a_Num1;
    int a_Num2;

    public Multiplication (int p_Num1, int p_Num2) {
        a_Num1 = p_Num1;
        a_Num2 = p_Num2;
    }

    @Override
    public Integer call() {
        //System.out.println("Mutliplicacion de " + a_Num1 + " y " + a_Num2 + ": " + a_Num1 * a_Num2);
        return a_Num1 * a_Num2;
    }
}