import java.util.ArrayList;
import java.util.List;

public class MaxWeightSequence {
    
    /**
     * Άσκηση 9
     *   Έστω Α = (α1, α2, α3, …, αn) μια ακολουθία n θετικών ακεραίων αριθμών. Το σύνολο των δεικτών � ⊆
     *   {1,2, … , �} ονομάζεται μη συνεχόμενο εάν για κάθε ζεύγος δεικτών �,� ∈ �, |� − �| > 1, δηλαδή το σύνολο
     *   Δ δεν περιέχει συνεχόμενους δείκτες. Το βάρος Β(Δ) ενός συνόλου δεικτών � ⊆ {1,2, … , �} είναι το
     *   άθροισμα των αντίστοιχων στοιχείων αi, δηλαδή �(�) = ∑!∈% �!. Το ζητούμενο είναι ο αποδοτικός
     *   υπολογισμός ενός μη συνεχόμενου συνόλου μεγίστου βάρους για μια δεδομένη ακολουθία Α.
     *   (α) Έστω ο ακόλουθος άπληστος αλγόριθμος:
     *   (i) Α = (α1, α2, α3, …, αn), Δ = {} και Υ = {1, 2, …, n}.
     *   (ii) Πρόσθεσε στο σύνολο Δ τον δείκτη � ∈ � ο οποίος έχει τη μεγαλύτερη τιμή αi στο Α
     *   (iii)Αφαίρεσε τους δείκτες i-1, i και i+1 αν υπάρχουν από το σύνολο Υ.
     *   (iv) Επανέλαβε τα βήματα (ii) και (iii) ώσπου το σύνολο Υ να γίνει κενό.
     *   (v) Επέστρεψε το Δ
     *   Δώστε ένα απλό αντιπαράδειγμα για το οποίο ο πιο πάνω άπληστος αλγόριθμος αποτυγχάνει να βρει
     *   τη βέλτιστη λύση.
     *   (β) Δεδομένης μιας ακολουθίας Α n θετικών ακεραίων αριθμών κατασκευάστε αποδοτικό αλγόριθμο
     *   Δυναμικού Προγραμματισμού ο οποίος να επιστρέφει ένα μη συνεχόμενο σύνολο μεγίστου βάρους για την
     *   ακολουθία Α. Ο αλγόριθμος πρέπει να έχει χρονική πολυπλοκότητα τάξεως Ο(n). 
     */

    public static Object[] solution(int[] A){
        int n = A.length;

        int[] dp = new int[n + 1]; // dp[i]: max weight we can get by using up until i-th element of sequence
        boolean[] select = new boolean[n + 1];
        dp[0] = 0;
        dp[1] = A[0];
        select[1] = true;


        for(int i = 2; i <= n; i++){
            // we wither get the previous (and not i-th) or the previous-previous AND the i-th
            if(dp[i - 1] >= dp[i - 2] + A[i - 1]){
                dp[i] = dp[i - 1];
                select[i] = false;
            } else{
                dp[i] = dp[i - 2] + A[i - 1];
                select[i] = true;
            }
        }

        List<Integer> D = new ArrayList<>();
        int i = n;
        while(i >= 1){
            if(select[i]){
                D.add(i);
                i-=2;
            } else{
                i--;
            }
        }

        return new Object[]{dp[n], D};
    }
    
    public static void main(String[] args) {
        int[] A = {10, 50, 45, 30, 28};
        Object[] solution = solution(A);

        int maxWeight = (int)solution[0];
        List<Integer> sequence = (List<Integer>) solution[1];

        System.out.println("Max weight: " + maxWeight);
        System.out.println("Sequence: ");
        for(Integer index : sequence){
            System.out.print(index + " ");
        }
        System.out.println();
    }


}
