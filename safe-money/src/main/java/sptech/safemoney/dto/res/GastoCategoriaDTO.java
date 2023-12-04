package sptech.safemoney.dto.res;

public class GastoCategoriaDTO {
    private Integer idPlanejamento;
    private int categoria;
    private double totalGasto;
    private double valorPlanejado;

    public GastoCategoriaDTO(int categoria, double totalGasto) {
        this.categoria = categoria;
        this.totalGasto = totalGasto;
    }

    public Integer getIdPlanejamento() {
        return idPlanejamento;
    }

    public void setIdPlanejamento(Integer idPlanejamento) {
        this.idPlanejamento = idPlanejamento;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public double getValorPlanejado() {
        return valorPlanejado;
    }

    public void setValorPlanejado(double valorPlanejado) {
        this.valorPlanejado = valorPlanejado;
    }
}
