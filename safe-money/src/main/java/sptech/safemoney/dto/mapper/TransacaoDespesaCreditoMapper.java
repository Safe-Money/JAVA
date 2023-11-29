package sptech.safemoney.dto.mapper;

import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.req.DespesaCreditoDTO;

public class TransacaoDespesaCreditoMapper extends Mapper<Transacao, DespesaCreditoDTO> {
    @Override
    protected DespesaCreditoDTO converterEntidadeParaDto(Transacao entidade) {
        return null;
    }

    @Override
    protected Transacao converterDtoParaEntidade(DespesaCreditoDTO dto) {
        Transacao t = new Transacao();
        t.setNome(dto.getNome());
        t.setData(dto.getData());
        t.setCategoria(dto.getCategoria());
        t.setTipo(dto.getTipo());
        t.setParcelas(dto.getParcelas());
        t.setParcelaAtual(dto.getParcelaAtual());
        t.setSaldoAnterior(dto.getSaldoAnterior());
        t.setFatura(dto.getFatura());
        t.setValor(dto.getValor());
        return t;
    }
}
