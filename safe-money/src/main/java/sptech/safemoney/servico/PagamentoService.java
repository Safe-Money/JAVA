package sptech.safemoney.servico;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.PagamentoEntity;
import sptech.safemoney.dominio.UsuarioEntity;
import sptech.safemoney.dto.req.PixDTO;
import sptech.safemoney.dto.res.ComprovantePixDTO;
import sptech.safemoney.repositorio.PagamentoRepository;
import sptech.safemoney.repositorio.UsuarioRepository;
import com.mercadopago.MercadoPagoConfig;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PagamentoService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PagamentoRepository pagamentoRepository;

    public ComprovantePixDTO criarPix(PixDTO pixDTO, int idUsuario) throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken("APP_USR-8758627595030379-042017-b99d5ebe5dd8f9db3e0c88d7a9aaaf43-741447599");

        String uniqueValue = UUID.randomUUID().toString();

        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", uniqueValue);

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

        PaymentClient client = new PaymentClient();

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime nowAjuste = now.plusHours(1);
        OffsetDateTime expirationTime = nowAjuste.plusMinutes(3);

        String email = pixDTO.getEmail();
        String firstName = pixDTO.getFirstName();
        String cpf = pixDTO.getCpf();

        PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal("4.99"))
                        .description("Premium - SafeMoney")
                        .paymentMethodId("pix")
                        .dateOfExpiration(expirationTime)
                        .payer(
                                PaymentPayerRequest.builder()
                                        .email(email)
                                        .firstName(firstName)
                                        .identification(
                                                IdentificationRequest.builder().type("CPF").number(cpf).build())
                                        .build())
                        .build();

        Payment response = client.create(paymentCreateRequest, requestOptions);


        UsuarioEntity user = new UsuarioEntity();
        user.setId(idUsuario);
        PagamentoEntity pagamentoEntity = new PagamentoEntity(response.getId(), user);
        pagamentoRepository.save(pagamentoEntity);

        ComprovantePixDTO comprovantePixDTO = new ComprovantePixDTO(response.getId(), response.getStatus(),
                response.getPointOfInteraction().getTransactionData().getQrCodeBase64(), response.getPointOfInteraction().getTransactionData().getQrCode());

        return comprovantePixDTO;
    }

    public boolean ConfirmarPagamento(int idUsuario) {
        usuarioRepository.alterarPlano(idUsuario);
        return false;
    }
}
