package sptech.safemoney.controle;


import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dto.req.PixDTO;
import sptech.safemoney.dto.res.ComprovantePixDTO;
import sptech.safemoney.servico.PagamentoService;

@Tag(name = "Pagamento Controller", description = "Pagamento via PIX")
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService service;

    @PostMapping("/pix/{id}")
    public ResponseEntity<ComprovantePixDTO> criarPagamento(@RequestBody PixDTO pixDTO, @PathVariable int id) throws MPException, MPApiException {

        ComprovantePixDTO comprovantePixDTO = service.criarPix(pixDTO, id);

        return ResponseEntity.status(200).body(comprovantePixDTO);
    }

    @PostMapping("/alterar-plano/{id}")
    public ResponseEntity<Void> alterarPlano(@PathVariable int id) {
        boolean planoAlterado = service.alterarPlano(id);

        return planoAlterado
                ? ResponseEntity.status(200).build()
                : ResponseEntity.status(404).build();
    }


}
