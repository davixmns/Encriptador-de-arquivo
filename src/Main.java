import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner arquivoEntrada = new Scanner(new FileReader("arquivos/entrada/arquivoEntrada.txt"));
        Scanner scanner = new Scanner(System.in);
        Encriptador encriptador = new Encriptador();

        System.out.println("Software encriptador de texto");
        encriptador.criptografar(arquivoEntrada);

        System.out.println("deseja descriptografar? (1-sim) (2-nao)");
        System.out.print("Digite: ");
        int escolha = scanner.nextInt();

        if(escolha == 1)
            encriptador.descriptografar();
        else
            System.exit(0);
    }
}
