package cursomc.services;

import cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmation(Pedido pedido);

    void sendEmail(SimpleMailMessage message);
}
