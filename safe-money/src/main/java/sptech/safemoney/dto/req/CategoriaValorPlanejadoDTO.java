package sptech.safemoney.dto.req;

public class CategoriaValorPlanejadoDTO {
    private Integer idCategoria;
    private String nomeCategoria;
    private Integer idPlanejamento;
    private double valorPlanejado;

    public CategoriaValorPlanejadoDTO(Integer idCategoria, String nomeCategoria, Integer idPlanejamento, double valorPlanejado) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.idPlanejamento = idPlanejamento;
        this.valorPlanejado = valorPlanejado;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Integer getIdPlanejamento() {
        return idPlanejamento;
    }

    public void setIdPlanejamento(Integer idPlanejamento) {
        this.idPlanejamento = idPlanejamento;
    }

    public double getValorPlanejado() {
        return valorPlanejado;
    }

    public void setValorPlanejado(double valorPlanejado) {
        this.valorPlanejado = valorPlanejado;
    }
}
