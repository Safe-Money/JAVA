package sptech.safemoney.dto.res;

public class GastoCategoriaDTO {
    private String categoria;
    private double totalGasto;

    public GastoCategoriaDTO(String categoria, double totalGasto) {
        this.categoria = categoria;
        this.totalGasto = totalGasto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }
}
