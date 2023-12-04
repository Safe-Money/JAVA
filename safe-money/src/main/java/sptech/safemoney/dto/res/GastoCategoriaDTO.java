package sptech.safemoney.dto.res;

public class GastoCategoriaDTO {
    private Integer idPlanejamento;
    private int idCategoria;
    private String nomeCategoria;
    private double totalGasto;
    private double valorPlanejado;

    public GastoCategoriaDTO(Integer idPlanejamento, int idCategoria, String nomeCategoria, double totalGasto, double valorPlanejado) {
        this.idPlanejamento = idPlanejamento;
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.totalGasto = totalGasto;
        this.valorPlanejado = valorPlanejado;
    }

    public Integer getIdPlanejamento() {
        return idPlanejamento;
    }

    public void setIdPlanejamento(Integer idPlanejamento) {
        this.idPlanejamento = idPlanejamento;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
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
