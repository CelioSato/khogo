PROJETO COM SPRING BOOT e ANGULAR

Projeto em andamento para localização de veiculos utilizando um ESP8266 e um módulo gps "NEO-6M", Spring boot (2.3.1) e Angular 10, enviando mensagens de alerta através do telegram e visualização utilizando a API do Google Maps. Ainda em fase de teste, funcional até o momento com melhorias a serem feitas. 

No Back-end está sendo criado uma API Rest usando Java com Spring Boot (2.3.1) e banco de dados MySQL, Websocket para comunicação com o cliente, JPA com Hibernate para mapeamento objeto-relacional, autenticação e autorização com tokens JWT, e implantação em nuvem utilizando a plataforma Heroku. No Back-end temos validação de dados, tratamento de exceções, segurança, CORS, e etc.

Está sendo desenvolvido o front end com a arquitetura MVC do Angular, Angular reactive forms, navegação, local storage, tratamento de erros. O diferencial do front-end esta no recebimento de mensagens de localização através de Websocket, este canal somente pode ser assinado depois do recebimento do nome do canal pelo telegram

FUNCIONAMENTO DO SISTEMA

Somente é possivel o cadastro no sistema por meio de uma chave liberada pelo administrador do sistema. Após a cadastro o usuário deve cadastrar seus dados pessoais, modelo, cor, placa de um veiculo e o número serial do aparelho a ser rastreado. Somente a página que lista a localização de um veiculo é liberada para acesso público, porém, esta página somente irá exibir qualquer localização quando um botão de pânico for pressionado, enviando ao telegram (Registrado pelo cliente) endereço da página e um login único que deve ser utilizado para acompanhar o rastreamento do veículo. O aparelho rastreador baseado em um Esp8266 com modulo GPS, faz uma conexão com a internet através de uma conexão WiFi (Celular), que ao ser pressionado o botão de pânico, envia o endereço a ser acessado e um login ao grupo de telegram cadastrado, em seguida inicia o envio da localização (latitude e longitude) do veículo ao Back-end, que grava em base de dados MYSQL, e envia aos assinantes deste canal (a assinatura desta canal é a mensagem recebida no telegram) 




