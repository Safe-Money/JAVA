package sptech.safemoney.dto.res;

public class ResumoKpiDTO {
    private double saldoTotal;
    private double despesas;
    private double gastoCartao;

    public ResumoKpiDTO(double saldoTotal, double despesas, double gastoCartao) {
        this.saldoTotal = saldoTotal;
        this.despesas = despesas;
        this.gastoCartao = gastoCartao;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public double getDespesas() {
        return despesas;
    }

    public void setDespesas(double despesas) {
        this.despesas = despesas;
    }

    public double getGastoCartao() {
        return gastoCartao;
    }

    public void setGastoCartao(double gastoCartao) {
        this.gastoCartao = gastoCartao;
    }
}
