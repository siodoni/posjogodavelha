package JogoDaVelha;

import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class JogoDaVelha {

    private int matriz[][] = new int[3][3];
    private String resultado;
    private boolean fimJogo;
    private boolean empate;
    private boolean jogadaValida;

    public JogoDaVelha() {
        montaTabuleiro("SIM");
    }

    public void jogar() {

        mensagemInicio();

        Scanner entrada = new Scanner(System.in);

        int posColuna = 0, posLinha = 0;
        int jogadorAtual = 1;
        int jogadorAnterior = 1;

        try {

            while (!isFimJogo()) {

                System.out.println("Informe a coluna:");
                posColuna = entrada.nextInt();

                if ((posColuna > 3) || (posColuna < 1)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Posição da coluna " + posColuna + " invalida.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    setJogadaValida(false);

                } else {
                    setJogadaValida(true);
                }

                if (isJogadaValida()) {
                    System.out.println("Informe a linha:");
                    posLinha = entrada.nextInt();

                    if ((posLinha > 3) || (posLinha < 1)) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Posição da linha " + posLinha + " invalida.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        setJogadaValida(false);

                    } else {
                        setJogadaValida(true);
                    }
                }

                if (isJogadaValida()) {
                    jogadorAnterior = jogadorAtual;

                    if (matriz[posLinha - 1][posColuna - 1] == 0) {
                        matriz[posLinha - 1][posColuna - 1] = jogadorAtual;

                        if (jogadorAtual == 1) {
                            jogadorAtual = jogadorAtual + 1;
                        } else {
                            jogadorAtual = jogadorAtual - 1;
                        }

                        montaTabuleiro("");
                        verificaGanhador(jogadorAnterior);

                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Posição já ocupada. Escolha outra posição.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);

                        jogadorAtual = jogadorAnterior;
                    }
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Informação invalida");
        }
    }

    private void mensagemInicio() {
        JOptionPane.showMessageDialog(
                null,
                "Jogo da velha v0. \n" +
                "Para jogar passe as coordenadas. \n" +
                "Exemplo: \n" +
                "Coluna 1 - Linha 3",
                "Instruções",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void montaTabuleiro(String limpaTabuleiro) {

        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz.length; coluna++) {

                if (limpaTabuleiro.equals("SIM")) {
                    // limpando o tabuleiro
                    matriz[linha][coluna] = 0;
                } else {
                    // imprimindo o tabuleiro
                    if (matriz[linha][coluna] == 0) {
                        setResultado(" ");
                    }

                    if (matriz[linha][coluna] == 1) {
                        setResultado("X");
                    }

                    if (matriz[linha][coluna] == 2) {
                        setResultado("O");
                    }

                    if (coluna <= 1) {
                        System.out.print(" " + getResultado() + " |");
                    } else {
                        System.out.print(" " + getResultado());
                    }
                }
            }

            if (!limpaTabuleiro.equals("SIM")) {
                System.out.println("\t");
                if (linha <= 1) {
                    System.out.println("---|---|---");
                }
            }
        }
        System.out.println("\t");
    }

    private void verificaGanhador(int jogador) {

        int contador = 0;

        // verificando as linhas e as colunas
        for (int i = 0; i < 3; i++) {
            if (matriz[i][0] != 0) {
                if ((matriz[i][0] == matriz[i][1])
                &&  (matriz[i][2] == matriz[i][1])) {
                    setFimJogo(true);
                }
            }
            if (matriz[0][i] != 0) {
                if ((matriz[0][i] == matriz[1][i])
                &&  (matriz[2][i] == matriz[1][i])) {
                    setFimJogo(true);
                }
            }
        }

        // verificando as diagonais
        if (matriz[1][1] != 0) {
            if ((matriz[0][0] == matriz[1][1])
            &&  (matriz[2][2] == matriz[1][1])) {
                setFimJogo(true);

            } else if ((matriz[0][2] == matriz[1][1])
                   &&  (matriz[2][0] == matriz[1][1])) {
                setFimJogo(true);
            }
        }

        if (isFimJogo()) {
            System.out.println("\nFim de jogo!");
            System.out.println("O vencedor é o jogador " + jogador + "\n");
        }

        // verifica se o jogo terminou empatado
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != 0) {
                    contador++;
                }
            }
            if (contador == 9) {
                setEmpate(true);
                setFimJogo(true);
            }
        }

        if ((isFimJogo()) && (isEmpate())) {
            System.out.println("\nFim de jogo!");
            System.out.println("Jogo empatado.\n");
        }
    }

    private String getResultado() {
        return resultado;
    }

    private void setResultado(String resultado) {
        this.resultado = resultado;
    }

    private boolean isJogadaValida() {
        return jogadaValida;
    }

    private void setJogadaValida(boolean jogadaValida) {
        this.jogadaValida = jogadaValida;
    }

    private boolean isEmpate() {
        return empate;
    }

    private void setEmpate(boolean empate) {
        this.empate = empate;
    }

    private boolean isFimJogo() {
        return fimJogo;
    }

    private void setFimJogo(boolean fimJogo) {
        this.fimJogo = fimJogo;
    }
}