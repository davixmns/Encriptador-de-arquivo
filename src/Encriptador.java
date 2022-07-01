import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Encriptador {
    private final BufferedReader leitorDeCriptografado;
    private final BufferedReader leitorDeAleatorios;
    private final PrintWriter escritorDeDescriptografado;
    private final PrintWriter escritorDeCriptografato;
    private final PrintWriter escritorDeAleatorios;

    public Encriptador() throws IOException {
        this.leitorDeCriptografado = new BufferedReader(new FileReader("arquivos/saida/criptografado.txt"));
        this.escritorDeCriptografato = new PrintWriter(new FileWriter("arquivos/saida/criptografado.txt"));
        this.leitorDeAleatorios = new BufferedReader(new FileReader("arquivos/saida/aleatorios.txt"));
        this.escritorDeAleatorios = new PrintWriter(new FileWriter("arquivos/saida/aleatorios.txt"));
        this.escritorDeDescriptografado = new PrintWriter(new FileWriter("arquivos/saida/descriptografado.txt"));
    }

    public void criptografar(Scanner arquivoDeEntrada, String caminhoDoArquivoDeEntrada) throws FileNotFoundException {
        System.out.println("Iniciando proceso de criptografia...");
        Random random = new Random();

        int valorMax = 255; //UTF-8
        int nLinhas = Carregamento.contarLinhas(caminhoDoArquivoDeEntrada);
        int linhaAtual = 0;

        while (arquivoDeEntrada.hasNextLine()) {
            StringBuilder linhaCriptografada = new StringBuilder();
            StringBuilder linhaAleatorios = new StringBuilder();

            String linha = arquivoDeEntrada.nextLine();
            for (int i = 0; i < linha.length(); i++) {
                int aleatorio = random.nextInt(valorMax);
                linhaAleatorios.append(aleatorio).append(" ");
                linhaCriptografada.append((char) (linha.charAt(i) + aleatorio));
            }
            this.escritorDeCriptografato.println(linhaCriptografada);
            this.escritorDeAleatorios.println(linhaAleatorios);
            System.out.println(Carregamento.porcentagem(linhaAtual, nLinhas) + "%");
            linhaAtual++;
        }

        this.escritorDeCriptografato.close();
        this.escritorDeAleatorios.close();
        System.out.println("Tarefa concluida!\n");
    }

    public void descriptografar() throws IOException {
        System.out.println("\nIniciando processo de descriptografia...");

        while(this.leitorDeCriptografado.ready()){
            StringBuilder linhaDescriptografada = new StringBuilder();
            String[] vetorDeAleatorios = this.leitorDeAleatorios.readLine().split(" ");
            String linhaCriptografada = this.leitorDeCriptografado.readLine();
            for (int i = 0; i < linhaCriptografada.length(); i++) {
                linhaDescriptografada.append((char)((int) linhaCriptografada.charAt(i) - Integer.parseInt(vetorDeAleatorios[i])));
            }
            this.escritorDeDescriptografado.println(linhaDescriptografada);
        }

        this.leitorDeCriptografado.close();
        this.leitorDeAleatorios.close();
        this.escritorDeDescriptografado.close();
        System.out.println("Tarefa concluida!\n");
    }
}


