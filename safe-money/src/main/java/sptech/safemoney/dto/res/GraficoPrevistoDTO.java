package sptech.safemoney.dto.res;

public class GraficoPrevistoDTO {
    private double receita;
    private double despesa;
    private double saldo;

    public GraficoPrevistoDTO(double receita, double despesa, double saldo) {
        this.receita = receita;
        this.despesa = despesa;
        this.saldo = saldo;
    }

    public double getReceita() {
        return receita;
    }

    public void setReceita(double receita) {
        this.receita = receita;
    }

    public double getDespesa() {
        return despesa;
    }

    public void setDespesa(double despesa) {
        this.despesa = despesa;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
