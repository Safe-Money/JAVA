package sptech.safemoney.utils;

import sptech.safemoney.dominio.Transacao;

import java.time.LocalDate;

public class OrdenacaoPesquisa {
    public static void ordernacaoBubbleSort(ListaObj<Transacao> lista) {
        int n = lista.getTamanho();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (lista.getElemento(j).getData().isBefore(lista.getElemento(j + 1).getData())) {
                    // Troca os elementos se estiverem na ordem errada
                    Transacao temp = lista.getElemento(j);
                    lista.alterar(lista.getElemento(j + 1), j);
                    lista.alterar(temp, j + 1);
                }
            }
        }
    }

    public static int pesquisaBinaria(ListaObj<Transacao> listaTransacoes, LocalDate data) {
        int indInf = 0;
        int indSup = listaTransacoes.getTamanho() - 1;

        while (indInf <= indSup) {
            int meio = (indInf + indSup) / 2;

            if (listaTransacoes.getElemento(meio).getData().isEqual(data)) {
                return meio; // Elemento encontrado, retorna o índice.
            } else if (data.isAfter(listaTransacoes.getElemento(meio).getData())) {
                indSup = meio - 1;
            } else {
                indInf = meio + 1;
            }
        }

        return -1;
    }
}
