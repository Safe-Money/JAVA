package sptech.safemoney.dto.res;

public class ComprovantePixDTO {
    private Long id;
    private String status;
    private String qrcodeBase;
    private String copiaCola;

    public ComprovantePixDTO(Long id, String status, String qrcodeBase, String copiaCola) {
        this.id = id;
        this.status = status;
        this.qrcodeBase = qrcodeBase;
        this.copiaCola = copiaCola;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQrcodeBase() {
        return qrcodeBase;
    }

    public void setQrcodeBase(String qrcodeBase) {
        this.qrcodeBase = qrcodeBase;
    }

    public String getCopiaCola() {
        return copiaCola;
    }

    public void setCopiaCola(String copiaCola) {
        this.copiaCola = copiaCola;
    }
}
