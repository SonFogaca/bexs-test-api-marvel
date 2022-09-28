package br.com.marvel.api;

import br.com.marvel.enums.PathEnum;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.WriterOutputStream;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
@Component
public class MarvelService {

    @Getter
    @Setter
    private Response response;

    private StringWriter requestWrite;
    private PrintStream requestCapture;

    private StringWriter responseWrite;
    private PrintStream responseCapture;

    private void createNewLog(){
        requestWrite = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWrite, StandardCharsets.UTF_8), true);

        responseWrite = new StringWriter();
        responseCapture = new PrintStream(new WriterOutputStream(responseWrite, StandardCharsets.UTF_8), true);
    }


    public void getStories( Map<String, String> dados){
        createNewLog();

        response = given()
                .filter( new RequestLoggingFilter(requestCapture) )
                .filter(new ResponseLoggingFilter(responseCapture))
                .param("limit", dados.get("limit") )
                .param("ts", dados.get( "ts" ) )
                .param("apikey", dados.get( "apikey" ) )
                .param("hash", dados.get( "hash" ) )
                .contentType( "application/json" )
                .when()
                .get(PathEnum.PATH_STORIES.getPath());


        log.info( "REQUEST STORIES : {}", requestWrite );
        log.info( "RESPONSE STORIES : {}", responseWrite );
    }


    public void getCharacterId(Map<String, String> dados){
        createNewLog();
        this.response = given()
                .filter( new RequestLoggingFilter(requestCapture) )
                .filter(new ResponseLoggingFilter(responseCapture))
                .param("ts", dados.get( "ts" ) )
                .param("apikey", dados.get( "apikey" ) )
                .param("hash", dados.get( "hash" ) )
                .contentType( "application/json" )
                .when()
                .get( PathEnum.PATH_CHARACTER_ID.getPath(), dados.get( "id" ));

        log.info( "REQUEST CHARACTER_ID : {}", requestWrite );
        log.info( "RESPONSE CHARACTER_ID : {}", responseWrite );
    }
}
