package sptech.safemoney.dto.mapper;

public abstract class Mapper <E, D> {
    protected abstract D converterEntidadeParaDto(E entidade);
    protected abstract E converterDtoParaEntidade(D dto);

    public D paraDTO(E entidade) {
        if (entidade == null) {
            return null;
        }
        return converterEntidadeParaDto(entidade);
    }

    public E paraEntidade(D dto) {
        if (dto == null) {
            return null;
        }
        return converterDtoParaEntidade(dto);
    }
}
