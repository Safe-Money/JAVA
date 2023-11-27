package sptech.safemoney.dto.res;

import java.time.LocalDate;

public class NotificacaoDTO {
    private String descricao;
    private LocalDate data;

    public NotificacaoDTO(String descricao, LocalDate data) {
        this.descricao = descricao;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
