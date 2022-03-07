package com.zitro.test.casino.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zitro.test.casino.exceptions.CasinoException;
import com.zitro.test.casino.service.CasinoService;
import com.zitro.test.casino.validators.CasinoValidator;
import com.zitro.test.game.service.dto.ConfigurationsDto;
import com.zitro.test.game.service.dto.GamesDto;
import com.zitro.test.game.service.dto.PlayersDto;
import com.zitro.test.game.service.dto.RoundDto;
import com.zitro.test.game.service.dto.RoundResponseDto;
import com.zitro.test.game.service.dto.UsersProvidersDto;

@RestController
@RequestMapping("/v1/api")
public class CasinoController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CasinoController.class);
	
	@Autowired
	private CasinoValidator casinoValidator;

	@Autowired
	private CasinoService casinoService;

	@GetMapping(value = "/games")
	public ResponseEntity<?> getGames(@RequestParam(value = "uuid", required = false) String uuid,
			@RequestParam(value = "name", required = false) String name) {
		try {
			if (uuid != null) {
				GamesDto resultGames = casinoService.selectGameType(UUID.fromString(uuid));
				return new ResponseEntity<GamesDto>(resultGames, HttpStatus.OK);
			}
			if (name != null) {
				GamesDto resultGames = casinoService.selectGameType(name);
				return new ResponseEntity<GamesDto>(resultGames, HttpStatus.OK);
			}
			List<GamesDto> resultGames = casinoService.selectGameType();
			return new ResponseEntity<List<GamesDto>>(resultGames, HttpStatus.OK);

		} catch (CasinoException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/config")
	public ResponseEntity<?> createConfigurations(@RequestBody ConfigurationsDto config) {
		try {
			casinoValidator.createConfig(config);
			ConfigurationsDto result = casinoService.createGameConfig(config);
			return new ResponseEntity<ConfigurationsDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/config/{gameTypeId}")
	public ResponseEntity<?> getSelectGameConfig(@PathVariable String gameTypeId) {
		try {
			List<ConfigurationsDto> result = casinoService.selectGameConfig(UUID.fromString(gameTypeId));
			return new ResponseEntity<List<ConfigurationsDto>>(result, HttpStatus.OK);
		} catch (CasinoException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/player")
	public ResponseEntity<?> addPlayerToPlatform(@RequestParam(value = "uuidProvider", required = true) String idProvider
			,@RequestParam(value = "amount", required = true) Double amount) {
		try {
			casinoValidator.addPlayerToPlatform(idProvider, amount);
			PlayersDto result = casinoService.addPlayerToPlatform(PlayersDto.builder()
					.balance(amount)
					.userProvider(UsersProvidersDto.builder()
							.id(UUID.fromString(idProvider))
							.build())
					.build());
			return new ResponseEntity<PlayersDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/player/{idPlayer}/config/{idConfig}")
	public ResponseEntity<?> playerStartGame(@PathVariable String idPlayer,@PathVariable String idConfig) {
		try {
			casinoValidator.playerStartGame(idPlayer, idConfig);
			RoundResponseDto resultRound = casinoService.startGame(RoundDto.builder()
					.userId(UUID.fromString(idPlayer))
					.gameConfigId(UUID.fromString(idConfig))
					.build());
			
			return new ResponseEntity<RoundResponseDto>(resultRound, HttpStatus.OK);
		} catch (CasinoException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/bet/{idBet}/moreAmount")
	public ResponseEntity<?> updateAmountBet(@PathVariable String idBet, @RequestParam(value = "amount", required = true) Double amount) {
		try {
			casinoValidator.updateAmountBet(idBet, amount);
			RoundResponseDto result = casinoService.bet(UUID.fromString(idBet), amount);
			return new ResponseEntity<RoundResponseDto>(result, HttpStatus.OK);
		} catch (CasinoException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/player/{idPlayer}/balance")
	public ResponseEntity<?> getBalance(@PathVariable String idPlayer) {
		try {
			Double resultBalance = casinoService.balance(UUID.fromString(idPlayer));
			return new ResponseEntity<Double>(resultBalance, HttpStatus.OK);
		} catch (CasinoException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
