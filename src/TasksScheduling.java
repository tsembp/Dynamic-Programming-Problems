import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TasksScheduling {

    /**
     * Άσκηση 10
     *   Έστω Α = (α1, α2, α3, …, αn) μια ακολουθία εργασιών. Κάθε εργασία i έχει κάποιο χρόνο έναρξης, έστω si,
     *   κάποιο χρόνο ολοκλήρωσης, έστω fi και κάποιο κέρδος, έστω ki, που θα μας αποφέρει σε περίπτωση που
     *   αποφασίσουμε να την διεκπεραιώσουμε. Υπολογίστε το συμβατό (δεν είναι δυνατή η επιλογή εργασιών
     *   που έχουν αλληλοεπικάλυψη στους χρόνους στους οποίους θα εργαστούμε για τη διεκπεραίωση τους)
     *   υποσύνολο των εργασιών της Α το οποίο μεγιστοποιεί το κέρδος μας.
     */

    static class Task{
        
        int id;
        int start;
        int finish;
        int cost;

        public Task(int id, int start, int finish, int cost){
            this.id = id;
            this.start = start;
            this.finish = finish;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Task " + id + " (start=" + start + ", finish=" + finish + ", cost=" + cost + ")";
        }
    }

    public static Object[] solution(Task[] A){
        int n = A.length;
        Arrays.sort(A, Comparator.comparingInt(t -> t.finish));

        int[] P = new int[n]; // P[i]: index j of last job that finished before i starts
        for (int i = 0; i < n; i++) {
            P[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (A[j].finish <= A[i].start) {
                    P[i] = j;
                    break;
                }
            }
        }

        int[] dp = new int[n]; // dp[i]: maximum profit achieveable up to i jobs
        dp[0] = A[0].cost;

        boolean[] selected = new boolean[n];
        selected[0] = true;


        for(int i = 1; i < n; i++){
            int costWith = A[i].cost + (P[i] != -1 ? dp[P[i]] : 0);
            int costWithout = dp[i - 1];

            if(costWith > costWithout){
                dp[i] = costWith;
                selected[i] = true;
            } else{
                dp[i] = costWithout;
                selected[i] = false;
            }
        }

        List<Task> sequence = new ArrayList<>();
        int i = n - 1;
        while(i >= 0){
            if(selected[i]){
                sequence.add(A[i]);
                i = P[i];
            } else{
                i--;
            }
        }
        Collections.reverse(sequence);

        return new Object[]{dp[n - 1], sequence};
    }


    public static void main(String[] args) {
        Task[] A = {
            new Task(1, 1, 4, 2),
            new Task(2, 3, 5, 3),
            new Task(3, 0, 6, 6),
            new Task(4, 4, 7, 1),
            new Task(5, 3, 8, 2),
            new Task(6, 5, 9, 4),
            new Task(7, 6, 10, 3),
            new Task(8, 8, 11, 2)
        };

        Object[] result = solution(A);
        int profit = (int)result[0];
        List<Task> sequence = (List<Task>)result[1];

        System.out.println("Maximum Profit: " + profit);
        System.out.println("Chosen tasks in the optimal solution:");
        for (Task t : sequence) {
            System.out.println(t);
        }
        
    }
    
}
