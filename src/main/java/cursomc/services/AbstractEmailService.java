package cursomc.services;

import cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public abstract class  AbstractEmailService implements EmailService {

    @Override
    public void sendOrderConfirmation(Pedido pedido) {
        SimpleMailMessage message = prepareMessage(pedido);
        sendEmail(message);
    }

    protected SimpleMailMessage prepareMessage(Pedido pedido) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lio@gmail.com");
        message.setTo(pedido.getCliente().getEmail());
        message.setText(pedido.toString());
        return message;
    }

}
