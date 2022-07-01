import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Integer exibirMenu() {
        return JOptionPane.showOptionDialog(
                null,
                "Selecione um Arquivo TXT UTF-8",
                "Encriptador de arquivos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon("arquivos/imagens/encr.png"),
                new String[]{"Selecionar Arquivo", "Sair"},
                "Selecionar Arquivo"
        );
    }

    public static Integer exibirEncriptacaoConcluida(long tempo) {
        return JOptionPane.showOptionDialog(
                null,
                "Encriptação concluída!\nTempo de execução = " + tempo + "ms",
                "Encriptador de arquivos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Ver arquivo", "Descriptografar", "Menu", "Sair"},
                null
        );
    }

    public static Integer exibirDescriptacaoConcluida(long tempo) {
        return JOptionPane.showOptionDialog(
                null,
                "Descriptação concluída!\nTempo de execução = " + tempo + "ms",
                "Encriptador de arquivos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Ver arquivo", "Menu", "Sair"},
                null
        );
    }

    public static void abrirArquivoCriptografado() throws IOException {
        java.awt.Desktop.getDesktop().open(new File("arquivos/saida/criptografado.txt"));
    }

    public static void abrirArquivoDescriptografado() throws IOException {
        java.awt.Desktop.getDesktop().open(new File("arquivos/saida/descriptografado.txt"));
    }

    public static void main(String[] args) throws IOException {
        Encriptador encriptador = new Encriptador();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("apenas .txt", "txt"));

        int OPCAO_1 = 0;
        int OPCAO_2 = 1;
        int OPCAO_3 = 2;
        boolean flagDescriptografado = false;

        int escolhaDoMenu = exibirMenu();

        if (escolhaDoMenu == OPCAO_1) {
            int retorno = fileChooser.showOpenDialog(null);
            if (retorno == JFileChooser.APPROVE_OPTION) {
                String caminho = fileChooser.getSelectedFile().getAbsolutePath();
                Scanner arquivoEntrada = new Scanner(new FileReader(caminho));

                long inicioDaEncriptacao = System.currentTimeMillis();
                encriptador.criptografar(arquivoEntrada, caminho);
                long fimDaEncriptacao = System.currentTimeMillis();
                long tempoDaCriptografia = fimDaEncriptacao - inicioDaEncriptacao;

                int escolhaEncriptacaoConcluida = exibirEncriptacaoConcluida(tempoDaCriptografia);

                while(true){
                    if(escolhaEncriptacaoConcluida == OPCAO_1) {
                        abrirArquivoCriptografado();
                        escolhaEncriptacaoConcluida = exibirEncriptacaoConcluida(tempoDaCriptografia);

                    } else if(escolhaEncriptacaoConcluida == OPCAO_2){
                        flagDescriptografado = true;
                        break;

                    } else if(escolhaEncriptacaoConcluida == OPCAO_3){
                        main(args);
                        break;

                    } else {
                        System.exit(0);
                    }
                }

                if(flagDescriptografado){
                    long inicioDaDescriptacao = System.currentTimeMillis();
                    encriptador.descriptografar();
                    long fimDaDescriptacao = System.currentTimeMillis();
                    long tempoDaDescriptografia = fimDaDescriptacao - inicioDaDescriptacao;

                    int escolhaDescriptacaoConcluida = exibirDescriptacaoConcluida(tempoDaDescriptografia);

                    while (true){
                        if(escolhaDescriptacaoConcluida == OPCAO_1){
                            abrirArquivoDescriptografado();
                            escolhaDescriptacaoConcluida = exibirDescriptacaoConcluida(tempoDaDescriptografia);

                        } else if(escolhaDescriptacaoConcluida == OPCAO_2){
                            main(args);
                            break;

                        } else {
                            System.exit(0);
                        }
                    }
                }
            }

        } else {
            System.exit(0);
        }
    }
}
