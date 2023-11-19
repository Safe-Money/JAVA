package sptech.safemoney;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sptech.safemoney.utils.GerenciadorDeArquivo;
@SpringBootApplication
public class SafeMoneyApplication {
	public static void main(String[] args) {
		SpringApplication.run(SafeMoneyApplication.class, args);
	}
}