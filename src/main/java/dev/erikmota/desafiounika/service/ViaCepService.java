package dev.erikmota.desafiounika.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.erikmota.desafiounika.models.Endereco;
import dev.erikmota.desafiounika.models.EnderecoViaCep;
import dev.erikmota.desafiounika.service.exceptions.ValidacaoException;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ViaCepService {
    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
    public Endereco buscarCep (String cep) {
        String endereco = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            EnderecoViaCep enderecoViaCep = mapper.readValue(json, mapper.getTypeFactory().constructType(EnderecoViaCep.class));
            return enderecoViaCep.toEndereco();
        } catch (Exception e){
            throw new ValidacaoException(" Ocorreu um erro ao encontrar o CEP!");
        }
    }
}
