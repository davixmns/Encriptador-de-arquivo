import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Carregamento {
    public static int contarLinhas(String caminhoArquivo) throws FileNotFoundException {
        Scanner arquivoEntrada = new Scanner(new FileReader(caminhoArquivo));
        int nLinhas = 0;
        while (arquivoEntrada.hasNextLine()){
            arquivoEntrada.nextLine();
            nLinhas++;
        }
        return nLinhas;
    }

    public static int porcentagem(int linhaAtual, int nLinhas) {
        return (linhaAtual * 100) / nLinhas;
    }
}
