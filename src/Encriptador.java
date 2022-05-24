import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Encriptador {

    private final BufferedReader leitorDeCriptografado;
    private final Scanner leitorDeAleatorios;
    private final PrintWriter escritorDeDescriptografado;
    private final PrintWriter escritorDeCriptografato;
    private final PrintWriter escritorDeAleatorios;

    public Encriptador() throws IOException {
        this.leitorDeCriptografado = new BufferedReader(new FileReader("arquivos/saida/criptografado.txt"));
        this.escritorDeCriptografato = new PrintWriter(new FileWriter("arquivos/saida/criptografado.txt"));
        this.leitorDeAleatorios = new Scanner(new FileReader("arquivos/lcg/aleatorios.txt"));
        this.escritorDeAleatorios = new PrintWriter(new FileWriter("arquivos/lcg/aleatorios.txt"));
        this.escritorDeDescriptografado = new PrintWriter(new FileWriter("arquivos/saida/descriptografado.txt"));
    }

    public void criptografar(Scanner arquivoEntrada) {
        System.out.println("Iniciando proceso de criptografia...");

        LCG lcg = new LCG();
        ArrayList<Integer> aleatoriosUsados = new ArrayList<>();

        int valorMin = 0;
        int valorMax;

        while (arquivoEntrada.hasNextLine()) {
            String linha = arquivoEntrada.nextLine();
            valorMax = linha.length() + 150;

            for (int i = 0; i < linha.length(); i++) {
                int aleatorio = lcg.randomInt(valorMin, valorMax);

                while (aleatoriosUsados.contains(aleatorio)){
                    aleatorio = lcg.randomInt(valorMin, valorMax);
                }

                aleatoriosUsados.add(aleatorio);
                this.escritorDeAleatorios.printf(aleatorio + " ");
                this.escritorDeCriptografato.print((char) (linha.charAt(i) + aleatorio));
            }
            System.out.print(".");
            this.escritorDeCriptografato.println();
            this.escritorDeAleatorios.println();
            aleatoriosUsados = new ArrayList<>();
        }

        this.escritorDeCriptografato.close();
        this.escritorDeAleatorios.close();
        System.out.println("Tarefa concluida!\n");
    }

    public void descriptografar() throws IOException {
        System.out.println("\nIniciando processo de descriptografia...");

        String linha = this.leitorDeCriptografado.readLine();

        while (leitorDeAleatorios.hasNextLine() && linha != null) {
            String[] vetorDeAleatorios = this.leitorDeAleatorios.nextLine().split(" ");

            for (int i = 0; i < linha.length(); i++) {
                char letra = (char)(linha.charAt(i) - Integer.parseInt(vetorDeAleatorios[i]));
                this.escritorDeDescriptografado.print(letra);
            }

            this.escritorDeDescriptografado.println();
            linha = this.leitorDeCriptografado.readLine();
        }

        this.escritorDeDescriptografado.close();
        this.leitorDeCriptografado.close();
        this.leitorDeAleatorios.close();
        System.out.println("Tarefa concluida!");
    }
}


