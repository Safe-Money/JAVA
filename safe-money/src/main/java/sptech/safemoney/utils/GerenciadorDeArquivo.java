package sptech.safemoney.utils;

import sptech.safemoney.dominio.Transacao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class GerenciadorDeArquivo {
    public static void gravaArquivoCsv(ListaObj<Transacao> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

// Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar o arquivo
        try {
            OrdenacaoPesquisa.ordernacaoBubbleSort(lista);

            for (int i = 0; i < lista.getTamanho(); i++) {

                //Recupere um elemento da lista e formate aqui:
                Transacao transacao = lista.getElemento(i);

                String tipoTransacao = "Despesa";
                if (transacao.getTipo().getId() == 1) {
                    tipoTransacao = "Receita";
                } else if (transacao.getTipo().getId() == 2) {
                    tipoTransacao = "Transfêrencia";
                }

                String tipoConta = "Conta-Corrente";
                if (transacao.getConta().getTipo() == 1) {
                    tipoTransacao = "Conta-Poupança";
                }

                saida.format("%d;%s;%s;%.2f;%s;%s;%s;%s;%.2f;%s\n", transacao.getId(), transacao.getNome(), transacao.getData(), transacao.getValor(), tipoTransacao,
                        transacao.getCategoria().getNome(), transacao.getConta().getBanco(),
                        tipoConta, transacao.getSaldoAnterior(), transacao.getConta().getFkUsuario().getNome());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo
        try {
            //Leia e formate a saída no console aqui:

            // Cabeçalho
            System.out.printf("%-7S %-30S %-10S %-18S %-17S %-9S %-15S %-15S %-20S %16S\n", "id", "descrição", "data", "valor da transação", "Tipo de transação",
                    "Categoria", "Banco", "Tipo de conta", "Saldo após transação", "Titular da conta");
            while (entrada.hasNext()) {
                //Corpo

                int id = entrada.nextInt();
                String nome = entrada.next();
                String data = entrada.next();
                double valorTransacao = entrada.nextDouble();
                String tipoTransacao = entrada.next();
                String categoria = entrada.next();
                String banco = entrada.next();
                String tipoConta = entrada.next();
                double saldoApos = entrada.nextDouble();
                String titular = entrada.next();


                System.out.printf("%07d %-30s %10s %18.2f %-17s %-9s %-15s %-15s %20.2f %16s\n", id, nome, data, valorTransacao, tipoTransacao, categoria,
                        banco, tipoConta, saldoApos, titular);

            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}
