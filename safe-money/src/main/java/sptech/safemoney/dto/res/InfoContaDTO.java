package sptech.safemoney.dto.res;

public class InfoContaDTO {
    private double despesa;
    private double receita;

    public InfoContaDTO(double despesa, double receita) {
        this.despesa = despesa;
        this.receita = receita;
    }

    public double getDespesa() {
        return despesa;
    }

    public void setDespesa(double despesa) {
        this.despesa = despesa;
    }

    public double getReceita() {
        return receita;
    }

    public void setReceita(double receita) {
        this.receita = receita;
    }
}
