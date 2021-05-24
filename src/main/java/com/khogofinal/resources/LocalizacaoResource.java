package com.khogofinal.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.khogofinal.domain.Location;
import com.khogofinal.domain.Veiculo;
import com.khogofinal.dto.LocalizacaoDTO;
import com.khogofinal.services.LocalizacaoService;
import com.khogofinal.services.VeiculoService;

@RestController
@RequestMapping(value="/localizacoes")
public class LocalizacaoResource {
	
	@Autowired 
	private LocalizacaoService localizacaoService;
	
	@Autowired
	private VeiculoService service;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	//@Autowired
	//private SendMessage sendMessage;
	

	@RequestMapping(method=RequestMethod.POST)// verbos GET, POST, DELETE
	@ResponseStatus(HttpStatus.CREATED)
	public void sendLocalization(@RequestBody Location obj) {
		
		//sendMessage.sendToTelegram();
		String aparelho = obj.getAparelho();					// Recebo o código do aparelho 
		//String username = obj.getUsername();
		Veiculo veiculoObj = service.findDevice(aparelho);          //faço a busca no banco pelo nome do aparelho
		System.out.println("CLIENTE OBJID: "+veiculoObj.getId());
		System.out.println("NOME DO APARELHO: "+aparelho);
		LocalizacaoDTO cliDto = new LocalizacaoDTO();			//Instancio um novo LocalizacaoDTO somente com as informações relevantes
		
		cliDto.setTelefone(veiculoObj.getCliente().getTelefone1());
		cliDto.setVeiculo(veiculoObj.getVeiculo());
		cliDto.setCor(veiculoObj.getCor());
		cliDto.setPlaca(veiculoObj.getPlaca());
		cliDto.setUsername(obj.getUsername());
		
		cliDto.setLatitude(obj.getLatitude());
		cliDto.setLongitude(obj.getLongitude());
		
		
		//Envia a mensagem ao telegram
		//sendMessage.sendToTelegram("Acesse o Site: https://khogo1.herokuapp.com/localizar/localizacao , coloque o login: Felipe");
		
		 template.convertAndSendToUser(obj.getUsername(),"/queue/greetings",cliDto); //Envio os dados por socket ao usuario especifico
		
		System.out.println(cliDto.getCor() + " " + cliDto.getPlaca() + " " + veiculoObj.getCliente().getTelefone1() + " " + 
						   cliDto.getVeiculo() + " " + cliDto.getLatitude() + " " + cliDto.getLongitude() + " " + obj.getUsername());
		obj = localizacaoService.insert(obj); //Salva estes dados no banco		
	}

}
