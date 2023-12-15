package dev.erikmota.desafiounikamain.service.validacoes;

import dev.erikmota.desafiounikamain.models.Endereco;
import dev.erikmota.desafiounikamain.repository.EnderecoRepository;
import dev.erikmota.desafiounikamain.service.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ValidacaoEnderecoPrincipal implements IValidacaoEndereco{

    @Autowired
    private EnderecoRepository repository;

    @Override
    public void validar(Endereco e) {

        List<Endereco> enderecos = e.getMonitorador().getEnderecos();
        System.out.println(enderecos);
/*
        System.out.println(existePrincipal);
        if (existePrincipal)
            throw new ValidacaoException("Este monitorador já possui um endereço principal");*/
    }
}
