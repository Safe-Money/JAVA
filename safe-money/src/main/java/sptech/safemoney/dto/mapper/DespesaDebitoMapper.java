package sptech.safemoney.dto.mapper;

import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.req.DespesaDTO;

public class DespesaDebitoMapper extends Mapper<Transacao, DespesaDTO> {
    @Override
    protected DespesaDTO converterEntidadeParaDto(Transacao entidade) {
        return null;
    }

    @Override
    protected Transacao converterDtoParaEntidade(DespesaDTO dto) {
        Transacao t = new Transacao();
        t.setNome(dto.getNome());
        t.setData(dto.getData());
        t.setCategoria(dto.getCategoria());
        t.setTipo(dto.getTipo());
        t.setConta(dto.getConta());
        t.setValor(dto.getValor());
        t.setSaldoAnterior(dto.getSaldoAnterior());
        t.setParcelas(0);
        t.setParcelaAtual(0);

        t.setFatura(null);

        return t;
    }
}
