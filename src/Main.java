import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Software encriptador de texto");

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Selecione apenas arquivos .txt",
                "txt"
        );
        chooser.setFileFilter(filter);

        int retorno = chooser.showOpenDialog(null);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            String caminhoEntrada = chooser.getSelectedFile().getAbsolutePath();

            Scanner arquivoEntrada = new Scanner(new FileReader(caminhoEntrada));
            Scanner scanner = new Scanner(System.in);
            Encriptador encriptador = new Encriptador();

            encriptador.criptografar(arquivoEntrada);
            java.awt.Desktop.getDesktop().open(new File("arquivos/saida/criptografado.txt"));

            System.out.println("deseja descriptografar? (1-sim) (2-nao)");
            System.out.print("Digite: ");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                encriptador.descriptografar();
                java.awt.Desktop.getDesktop().open(new File("arquivos/saida/descriptografado.txt"));
            } else {
                System.exit(0);
            }

        } else {
            System.out.println("Arquivo invalido");
        }
    }
}
