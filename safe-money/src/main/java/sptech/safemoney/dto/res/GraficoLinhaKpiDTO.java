package sptech.safemoney.dto.res;

public class GraficoLinhaKpiDTO {
    private double menorValor;
    private double maiorValor;
    private double variacaoPercentual;

    public GraficoLinhaKpiDTO(double menorValor, double maiorValor, double variacaoPercentual) {
        this.menorValor = menorValor;
        this.maiorValor = maiorValor;
        this.variacaoPercentual = variacaoPercentual;
    }

    public double getMenorValor() {
        return menorValor;
    }

    public void setMenorValor(double menorValor) {
        this.menorValor = menorValor;
    }

    public double getMaiorValor() {
        return maiorValor;
    }

    public void setMaiorValor(double maiorValor) {
        this.maiorValor = maiorValor;
    }

    public double getVariacaoPercentual() {
        return variacaoPercentual;
    }

    public void setVariacaoPercentual(double variacaoPercentual) {
        this.variacaoPercentual = variacaoPercentual;
    }
}
