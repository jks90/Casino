package com.zitro.test.casino.validators.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zitro.test.casino.constants.Constants;
import com.zitro.test.casino.exceptions.CasinoException;
import com.zitro.test.casino.models.Configurations;
import com.zitro.test.casino.models.PlayerConfig;
import com.zitro.test.casino.models.Players;
import com.zitro.test.casino.models.UsersProviders;
import com.zitro.test.casino.repositories.ConfigurationsRepository;
import com.zitro.test.casino.repositories.PlayerConfigRepository;
import com.zitro.test.casino.repositories.PlayersRepository;
import com.zitro.test.casino.repositories.UsersProvidersRepository;
import com.zitro.test.casino.validators.CasinoValidator;
import com.zitro.test.game.service.dto.ConfigurationsDto;
import com.zitro.test.types.RoundStatus;

@Service
public class CasinoValidatorImpl implements CasinoValidator{
	
	@Autowired
	private UsersProvidersRepository usersProvidersRepository;
	
	@Autowired
	private ConfigurationsRepository configurationsRepository;

	@Autowired
	private PlayersRepository playersRepository;
	
	@Autowired
	private PlayerConfigRepository playerConfigRepository;

	@Override
	public void createConfig(ConfigurationsDto config) throws CasinoException {
		
		if(config.getCostTime() < 1000) {
			throw new CasinoException(Constants.COST_TIME_NOT_FOUND);
		}
		
		if(config.getMaxBet() <= 0) {
			throw new CasinoException(Constants.MAX_BET_NOT_FOUND);
		}
		
		if(config.getMinBet() <= 0) {
			throw new CasinoException(Constants.MAX_BET_NOT_FOUND);
		}
		
		if(config.getMinBet() > config.getMaxBet()) {
			throw new CasinoException(Constants.BET_NOT_FOUND);
		}
		
		if(config.getPrize() <= 0) {
			throw new CasinoException(Constants.PRIZE_NOT_FOUND);
		}
		
		if(config.getProvability() <= 0 && config.getProvability() >= 100) {
			throw new CasinoException(Constants.PROVABILITY_NOT_FOUND);
		}
	}

	@Override
	public void addPlayerToPlatform(String idProvider, Double amount) throws CasinoException {
		Optional<UsersProviders> userTarget = usersProvidersRepository.findById(UUID.fromString(idProvider));
		
		if(!userTarget.isPresent()) {
			throw new CasinoException(Constants.USERPROVIDER_NOT_FOUND);
		}
		if(amount <= 0) {
			throw new CasinoException(Constants.AMOUNT_NOT_FOUND);
		}
	}

	@Override
	public void playerStartGame(String idPlayer, String idConfig) throws CasinoException {
		Optional<Players> playerTarget = playersRepository.findById(UUID.fromString(idPlayer));
		Optional<Configurations> configurationTarget = configurationsRepository.findById(UUID.fromString(idConfig));
		if(!playerTarget.isPresent()) {
			throw new CasinoException(Constants.PLAYER_NOT_FOUND);
		}
		if(!configurationTarget.isPresent()) {
			throw new CasinoException(Constants.CONFIGURATIONS_NOT_FOUND);
		}
		if(playerTarget.get().getBalance() <= configurationTarget.get().getMinBet()) {
			throw new CasinoException(Constants.AMOUNT_NOT_AVAILABLE);
		}
	}

	@Override
	public void updateAmountBet(String idBet, Double amount) throws CasinoException {
		Optional<PlayerConfig> betTarget = playerConfigRepository.findById(UUID.fromString(idBet));
		if(!betTarget.isPresent()) {
			throw new CasinoException(Constants.PLAYER_CONFIGURATIONS_NOT_FOUND);
		}else {
		
			if(betTarget.get().getStatus() == RoundStatus.FINISHED) {
				throw new CasinoException(Constants.BET_STATUS_NOT_FOUND);
			}
			
			if(amount >= betTarget.get().getPlayer().getBalance()) {
				throw new CasinoException(Constants.AMOUNT_NOT_AVAILABLE);
			}
			
			if(betTarget.get().getPlayer().getTimePlaying() >= betTarget.get().getPlayer().getTimeLimit()) {
				throw new CasinoException(Constants.TIME_NOT_AVAILABLE);
			}
			
			if(amount <= 0) {
				throw new CasinoException(Constants.AMOUNT_NOT_FOUND);
			}
			
			if(amount <= betTarget.get().getConfigurations().getMinBet()) {
				throw new CasinoException(Constants.MIN_BET_AMOUNT_NOT_FOUND);
			}
			
			if(amount >= betTarget.get().getConfigurations().getMaxBet()) {
				throw new CasinoException(Constants.MAX_BET_AMOUNT_NOT_FOUND);
			}
		}
	}

}
