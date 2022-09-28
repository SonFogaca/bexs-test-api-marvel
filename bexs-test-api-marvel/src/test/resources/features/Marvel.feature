@all
Feature: API MARVEL INTERATIVA

 Background:
  Given que tenho as credenciais de usuario da marvel

 Scenario: Validar requisicao get stories com paginacao
  When executo a chamada get no endpoint stories com limite de "5" title
  Then valido o statusCode 200
  And valido o retorno de 5 registros

 Scenario Outline: Validar requisicao get pelo codigo de identificacao characterID
  When executo a chamada get no endpoint characters com id "<ID>"
  Then valido o statusCode 200
  And valido o nome do personagem "<PERSONAGEM>"

  Examples:
   |ID     |PERSONAGEM     |
   |1011198|Agents of Atlas|
   |1011297|Agent Brand    |
   |1011456|Balder         |

 Scenario: Validar requisicao get pelo codigo de identificacao characterID não existente
  When executo a chamada get no endpoint characters com id "1019999"
  Then valido o statusCode 404
  And valido mensagem de retorno "We couldn't find that character"
