public class ToyComponentSequence {

    // HW3 - Ex.5
    
    // - Given a sequence of components E = e1, e2, ..., en for constructing toy 1 (given to one staff)
    // - And a similar sequence P = p1, p2, ..., pm for constructing toy 2
    // - We set K (size n+m) the sequence that the components arrive in valid sequence. Chance of ei = pj also (same component for toys)
    // - Sometimes K has invalid sequence like: e1, e2, p2, p1
    // - Find algorithm that returns if K is valid

    public static boolean solution(String E[], String P[], String K[]){
        int n = P.length;
        int m = E.length;

        if(K.length != n + m) return false;

        // dp[i][j]: true if first (i+j) elements of K can be formed into valid sequence of first
        // i elements of P and first j elements of E
        boolean[][] dp = new boolean[n + 1][m + 1];
        
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= m; j++){
                dp[i][j] = false;
            }
        }
        dp[0][0] = true;

        // When P is empty: K has to match E
        for(int j = 1; j <= m; j++){
            dp[0][j] = dp[0][j - 1] && K[j - 1].equals(E[j - 1]);
        }

        // When E is empty: K has to match P
        for(int i = 1; i <= n; i++){
            dp[i][0] = dp[i - 1][0] && K[i - 1].equals(P[i - 1]);
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                // Element at K[i + j - 1] can be generated from:
                // - From P: if dp[i - 1][j]:true and P[i - 1] = K[i + j - 1]
                // - From E: if dp[i][j - 1]: true and E[j - 1] = K[i + j - 1]
                dp[i][j] = (dp[i - 1][j] && K[i + j - 1].equals(P[i - 1]))
                            || (dp[i][j - 1] && K[i + j - 1].equals(E[j - 1]));
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        // Παράδειγμα εισόδου:
        // Ακολουθίες κατασκευής των παιχνιδιών Π και Ε.
        String[] P = {"π1", "π2", "π3"};
        String[] E = {"ε1", "ε2", "ε3"};
        
        // Έγκυρη ακολουθία Κ:
        // Π.χ.: "π1", "ε1", "ε2", "π2", "ε3", "π3"
        String[] K_valid = {"π1", "ε1", "ε2", "π2", "ε3", "π3"};
        
        // Ένα παράδειγμα μη έγκυρης ακολουθίας Κ, όπου η σειρά στην Π ή την Ε δεν τηρείται.
        // Για παράδειγμα, αν η Π πρέπει να έχει την σειρά "π1", "π2", "π3" αλλά στο K
        // έχουμε "π1", "ε1", "π3", "π2", ... τότε η σειρά της Π διαταράσσεται.
        String[] K_invalid = {"π1", "ε1", "π3", "π2", "ε2", "ε3"};
        
        boolean valid = solution(E, P, K_valid);
        System.out.println("Is K_valid a valid sequence? " + valid);
        
        boolean invalid = solution(E, P, K_invalid);
        System.out.println("Is K_invalid a valid sequence? " + invalid);
    }

}
