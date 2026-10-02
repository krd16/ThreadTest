import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Prueba {
    public static final int THREAD_NUM = 3;
    public static final int MULTIPLICATION_NUM = 10;

    public class Multiplication implements Callable<Integer> {
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

    public static void main(String[] args) {
        ThreadPoolExecutor l_Executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_NUM);
        int l_Num1 = 0;
        int l_Num2 = 0;
        int l_Counter = 0;
        Prueba l_Application = new Prueba();
        Multiplication l_Task = l_Application.new Multiplication(0, 0);
        @SuppressWarnings("unchecked")
        Future<Integer>[] l_ResultList = (Future<Integer>[]) new Future[MULTIPLICATION_NUM];

        for (l_Counter = 1; l_Counter < MULTIPLICATION_NUM; l_Counter++) {
            l_Num1 = (int) (Math.random() * 10) + 1;
            l_Num2 = (int) (Math.random() * 10) + 1;
            l_Task = l_Application.new Multiplication(l_Num1, l_Num2);
            l_ResultList[l_Counter - 1] = l_Executor.submit(l_Task);
        }

        for (Future<Integer> l_Item : l_ResultList) {
            System.out.println(l_Item);
        }

        l_Executor.close();
    }
}
