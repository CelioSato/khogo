package com.khogofinal.resources;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessage {

	public void sendToTelegram(String token, String idChat, String endereco) {

		String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        //Add Telegram token 
        String apiToken = token;   ////GrandSiena_bot
      
        //Add chatId
        String chatId = idChat;  //Grupo_Teste
        String text = "Endereço: " + endereco ;
        
        //String text = "Mensagem Enviada ao Khogo_bot!";
        urlString = String.format(urlString, apiToken, chatId, text);
        
        

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
