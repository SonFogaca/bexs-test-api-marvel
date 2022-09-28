package br.com.marvel.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MavelStep {

    @Autowired
    MarvelService mavelService;

    private final Map<String, String> dados = new HashMap<>();

    @Given( "que tenho as credenciais de usuario da marvel" )
    public void queTenhoAsCredenciaisDeUsuarioDaMarvel() throws NoSuchAlgorithmException {
        gerarMD5();
    }

    @When("executo a chamada get no endpoint stories com limite de {string} title")
    public void executoChamadaGetNiEndpointStories(String limit){
        dados.put( "limit", limit );
        mavelService.getStories(dados);
    }

    @Then("valido o statusCode {int}")
    public void validoStatusCode(Integer statusCode){
        mavelService.getResponse().then().statusCode( statusCode );
    }

    @And("valido o retorno de {int} registros")
    public void validoRetornoDe5Registros(int registros){
        List<String> message = mavelService.getResponse()
                .then()
                .extract()
                .jsonPath().get("data.results.title");

        assertEquals(registros, message.size() );

    }

    @When("executo a chamada get no endpoint characters com id {string}")
    public void executoChamadaGetNoEndpointCharactersComId(String id){
        dados.put("id", id);
        mavelService.getCharacterId( dados );
    }

    @And("valido o nome do personagem {string}")
    public void validNomePersonagem(String personagem){
        List<String> name = mavelService.getResponse()
                .then()
                .extract()
                .jsonPath().get("data.results.name");

        assertTrue( "PERSONAGEM RETORNADO: ", name.stream().allMatch( personagem :: equals ) );
    }

    @And("valido mensagem de retorno {string}")
    public void validmensagemDeRetorno(String msg){
        String retorno = mavelService.getResponse()
                .then()
                .extract()
                .jsonPath().get("status");

        assertEquals(msg, retorno );

    }

    private void gerarMD5() throws NoSuchAlgorithmException {

        String puclicKey = System.getenv("publicKey");
        String privateKey = System.getenv("privateKey");
        String ts = String.valueOf(System.currentTimeMillis()  ) ;

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(ts.concat( privateKey ).concat( puclicKey ).getBytes( StandardCharsets.UTF_8 ));
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest);

        dados.put( "ts", ts );
        dados.put( "apikey", puclicKey );
        dados.put( "hash", myHash.toLowerCase() );
    }
}
