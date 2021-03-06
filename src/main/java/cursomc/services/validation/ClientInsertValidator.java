package cursomc.services.validation;

import cursomc.resources.dto.ClienteNewDTO;
import cursomc.resources.handler.FieldMessageError;
import cursomc.services.ClienteService;
import cursomc.services.validation.utils.DocumentCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cursomc.domain.enums.TipoCliente.PESSOAFISICA;
import static cursomc.domain.enums.TipoCliente.PESSOAJURIDICA;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {

    @Autowired
    private ClienteService clienteService;

    @Override
    public void initialize(ClientInsert clientInsert) {

    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
        List<FieldMessageError> fieldMessageErrors = new ArrayList<>();

        if (PESSOAFISICA.equals(clienteNewDTO.getTipo()) && !DocumentCodeUtil.isValidCpf(clienteNewDTO.getCpfOuCnpj())) {
            fieldMessageErrors.add(new FieldMessageError("cpfOuCnpj", "CPF inválido"));
        }

        if (PESSOAJURIDICA.equals(clienteNewDTO.getTipo()) && !DocumentCodeUtil.isValidCnpj(clienteNewDTO.getCpfOuCnpj())) {
            fieldMessageErrors.add(new FieldMessageError("cpfOuCnpj", "CNPJ inválido"));
        }

        final var exitingClientWithEmail = this.clienteService.findByEmail(clienteNewDTO.getEmail());
        if (Objects.nonNull(exitingClientWithEmail)) {
            fieldMessageErrors.add(new FieldMessageError("email", "Email já existente"));
        }

        for (FieldMessageError fieldMessageError : fieldMessageErrors) {
           context.disableDefaultConstraintViolation();
           context.buildConstraintViolationWithTemplate(fieldMessageError.getMessage())
                   .addPropertyNode(fieldMessageError.getField())
                   .addConstraintViolation();
        }

        return fieldMessageErrors.isEmpty();
    }
}
