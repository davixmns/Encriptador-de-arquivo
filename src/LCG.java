/*
Implementação do algoritmo PRNG: "Lehmer random number generator"
 Referências:
 + "https://en.wikipedia.org/wiki/Linear_congruential_generator"
 + "https://en.wikipedia.org/wiki/Lehmer_random_number_generator"
 + "https://www.geeksforgeeks.org/pseudo-random-number-generator-prng/"
*/

public class LCG { //Gerador Linear Congruente
    private final long a = 16_807;        // Multiplicador  0 <  a < m
    private final long c = 0;             // Incremento     0 <= c < m
    private final long m = 2_147_483_647; // Modulo 0 <  m

    public int randomInt(int minValue, int limit) {
        final int LOOP_CYCLES = 1_000_000;

        long xn = System.currentTimeMillis(); //timestamp

        for (int i = 0; i < LOOP_CYCLES; i++) {
            xn = ((a * xn) + c) % m;
        }
        return (minValue + (((int) xn) % limit));
    }
}

