package com.zitro.test.casino.validators;

import com.zitro.test.casino.exceptions.CasinoException;
import com.zitro.test.game.service.dto.ConfigurationsDto;

public interface CasinoValidator {
	
	public void createConfig(ConfigurationsDto config) throws CasinoException;
	public void addPlayerToPlatform(String idProvider,Double amount) throws CasinoException;
	public void playerStartGame(String idPlayer,String idConfig) throws CasinoException;
	public void updateAmountBet(String idBet, Double amount) throws CasinoException;

}
