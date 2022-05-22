import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

public class Encriptador {
    public void criptografar(Scanner arquivoEntrada) throws IOException {
        System.out.println("Iniciando proceso de criptografia...");
        PrintWriter printWriter1 = new PrintWriter(new FileWriter("arquivos/saida/criptografado.txt"));
        PrintWriter printWriter2 = new PrintWriter(new FileWriter("arquivos/lcg/aleatorios.txt"));
        LCG lcg = new LCG();
        ArrayList<Integer> aleatoriosUsados = new ArrayList<>();

        int valorMin = 0;
        int valorMax;
        while (arquivoEntrada.hasNextLine()) {
            String linha = arquivoEntrada.nextLine();
            valorMax = linha.length() + 100;

            for (int i = 0; i < linha.length(); i++) {
                int aleatorio;
                while (true) {
                    aleatorio = lcg.randomInt(valorMin, valorMax);

                    if (!aleatoriosUsados.contains(aleatorio)) {
                        aleatoriosUsados.add(aleatorio);
                        break;
                    }
                }
                printWriter2.printf(aleatorio + " ");
                char caractere = (char) (linha.charAt(i) + aleatorio);
                printWriter1.print(caractere);
            }
            printWriter1.println();
            printWriter2.println();
            aleatoriosUsados = new ArrayList<>();
        }

        printWriter1.close();
        printWriter2.close();
        System.out.println("Tarefa concluida!\n");
    }

    public void descriptografar() throws IOException {
        System.out.println("\nIniciando processo de descriptografia...");
        BufferedReader arqCriptografado = new BufferedReader(new FileReader("arquivos/saida/criptografado.txt"));
        Scanner arqAleatorios = new Scanner(new FileReader("arquivos/lcg/aleatorios.txt"));
        PrintWriter arqDescriptografado = new PrintWriter(new FileWriter("arquivos/saida/descriptografado.txt"));

        String linha = arqCriptografado.readLine();
        while (arqAleatorios.hasNextLine() && linha != null) {
            String[] vetorAleatorios = arqAleatorios.nextLine().split(" ");

            for (int i = 0; i < linha.length(); i++) {
                char letra = (char)(linha.charAt(i) - Integer.parseInt(vetorAleatorios[i]));
                arqDescriptografado.print(letra);
            }

            arqDescriptografado.println();
            linha = arqCriptografado.readLine();
        }
        arqDescriptografado.close();
        arqCriptografado.close();
        arqAleatorios.close();
        System.out.println("Tarefa concluida!");
    }
}


