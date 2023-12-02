package sptech.safemoney.utils;

import sptech.safemoney.dominio.Transacao;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GerenciadorArquivoTxt {
    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // Abre o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        // Grava o registro e fecha o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(ListaObj<Transacao> lista, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00TRANSAÇÕES POR USUÁRIO"; //Verificar documento de layout
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Grava os registros de dados (ou registros de corpo)
        for (int i = 0; i < lista.getTamanho(); i++) {
            Transacao t = lista.getElemento(i);
            String corpo = "02";
            if (t.getConta() != null) {
                corpo += String.format("%05d", t.getId()); //Completar de acordo com documento
                corpo += String.format("%-30s", t.getNome());
                corpo += String.format("%10s", t.getData());
                corpo += String.format("%13.2f", t.getValor());
                corpo += String.format("%-25s", t.getTipo().getNome());
                corpo += String.format("%-20s", t.getCategoria().getNome());
                corpo += String.format("%-20s", t.getConta().getNome());
                corpo += String.format("%15d", t.getConta().getTipo());
                corpo += String.format("%13.2f", t.getSaldoAnterior());
                corpo += String.format("%-25s", t.getConta().getFkUsuario().getNome());

                //Gravando corpo no arquivo:
                gravaRegistro(corpo, nomeArq);
                // Incrementa o contador de registros de dados gravados
                contaRegDados++;
            }
        }

        for (int i = 0; i < lista.getTamanho(); i++) {
            Transacao t = lista.getElemento(i);
            String corpo = "03";
            if (t.getFatura() != null) {
                corpo += String.format("%05d", t.getId()); //Completar de acordo com documento
                corpo += String.format("%-30s", t.getNome());
                corpo += String.format("%10s", t.getData());
                corpo += String.format("%13.2f", t.getValor());
                corpo += String.format("%-25s", t.getTipo().getNome());
                corpo += String.format("%-20s", t.getCategoria().getNome());
                corpo += String.format("%-20s", t.getFatura().getFkCartao().getNome());
                corpo += String.format("%-15s", t.getFatura().getFkCartao().getBandeira());
                corpo += String.format("%13.2f", t.getSaldoAnterior());
                corpo += String.format("%-25s", t.getFatura().getFkCartao().getConta().getFkUsuario().getNome());


                //Gravando corpo no arquivo:
                gravaRegistro(corpo, nomeArq);
                // Incrementa o contador de registros de dados gravados
                contaRegDados++;
            }
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%05d", contaRegDados);

        gravaRegistro(trailer, nomeArq);
    }


    public static void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;

        String registro, tipoRegistro;

        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;


        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        // Leitura do arquivo
        try {
            registro = entrada.readLine();

            while (registro != null) {
                // obtem os 2 primeiros caracteres do registro lido
                // 1o argumento do substring é o indice do que se quer obter, iniciando de zero
                // 2o argumento do substring é o indice final do que se deseja, MAIS UM
                // 012345
                // 00NOTA

                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("----------É um registro de header-------------");
                    //Exibir informações do header
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 24));
                    System.out.println("Data e hora: " + registro.substring(24, 43));
                    System.out.println("Versão do layout: " + registro.substring(43, 45));


                } else if (tipoRegistro.equals("01")) {
                    System.out.println("----------É um registro de trailer---------------");
                    //Exibir quantidade de registros
                    System.out.println("Quantidade de registros: " + registro.substring(2, 7));

                } else if (tipoRegistro.equals("02")) {
                    System.out.println("---------É um registro de corpo--------------");

                    //Guardar dados do corpo em variáveis
                    int id = Integer.valueOf(registro.substring(2, 7).trim());
                    String descTransacao = registro.substring(7, 37).trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    // Converte a string para LocalDate
                    LocalDate data = LocalDate.parse(registro.substring(37, 47).trim(), formatter);

                    double valor = Double.valueOf(registro.substring(47, 60).trim().replace(",", "."));
                    String tipoTrans = registro.substring(60, 85).trim();
                    String categoria = registro.substring(85, 105).trim();
                    String banco = registro.substring(105, 125).trim();
                    int tipoConta = Integer.valueOf(registro.substring(125, 140).trim());
                    double saldoConta = Double.valueOf(registro.substring(140, 153).trim().replace(",", "."));
                    String titularConta = registro.substring(153, 176).trim();

                    // Depois de guarda a variável exiba:

                    System.out.println("Id: " + id);
                    System.out.println("Nome: " + descTransacao);
                    System.out.println("Data: " + data);
                    System.out.println("Valor: " + valor);
                    System.out.println("Tipo transferencia: " + tipoTrans);
                    System.out.println("Categoria: " + categoria);
                    System.out.println("Banco: " + banco);
                    System.out.println("Tipo conta: " + tipoConta);
                    System.out.println("Saldo conta: " + saldoConta);
                    System.out.println("Titular conta: " + titularConta);

                    // Incrementa o contador de reg de dados lidos
                    contaRegDadosLidos++;



                } else if (tipoRegistro.equals("03")) {
                    System.out.println("---------É um registro de corpo--------------");

                    //Guardar dados do corpo em variáveis
                    int id = Integer.valueOf(registro.substring(2, 7).trim());
                    String descTransacao = registro.substring(7, 37).trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    // Converte a string para LocalDate
                    LocalDate data = LocalDate.parse(registro.substring(37, 47).trim(), formatter);

                    double valor = Double.valueOf(registro.substring(47, 60).trim().replace(",", "."));
                    String tipoTrans = registro.substring(60, 85).trim();
                    String categoria = registro.substring(85, 105).trim();
                    String nomeCartao = registro.substring(105, 125).trim();
                    String bandeira = registro.substring(125, 140).trim();
                    double saldoConta = Double.valueOf(registro.substring(140, 153).trim().replace(",", "."));
                    String titularConta = registro.substring(153, 176).trim();

                    // Depois de guarda a variavel exiba:

                    System.out.println("Id: " + id);
                    System.out.println("Desc: " + descTransacao);
                    System.out.println("Data: " + data);
                    System.out.println("Valor: " + valor);
                    System.out.println("Tipo transferencia: " + tipoTrans);
                    System.out.println("Categoria: " + categoria);
                    System.out.println("Nome cartao: " + nomeCartao);
                    System.out.println("Bandeira: " + bandeira);
                    System.out.println("Saldo conta: " + saldoConta);
                    System.out.println("Titular conta: " + titularConta);

                    // Incrementa o contador de reg de dados lidos
                    contaRegDadosLidos++;

                    // Se estivesse conectado a um banco de dados
                    // como não estamos conectados a um BD, vamos adicionar na lista

                } else {
                    System.out.println("Registro inválido");
                }

                // Le o proximo registro
                registro = entrada.readLine();

            }
            // fim do while
            // Fecha o arquivo
            entrada.close();

        } // fim do try

        catch (IOException erro) {

            System.out.println("Erro ao ler o arquivo");

            erro.printStackTrace();

        }



        // Aqui tb seria possível salvar a lista no BD
        // repository.saveAll(lista);

    }
}
