package com.zitro.test.casino.service;

import java.util.List;
import java.util.UUID;

import com.zitro.test.casino.exceptions.CasinoException;
import com.zitro.test.game.service.dto.ConfigurationsDto;
import com.zitro.test.game.service.dto.GamesDto;
import com.zitro.test.game.service.dto.PlayersDto;
import com.zitro.test.game.service.dto.RoundDto;
import com.zitro.test.game.service.dto.RoundResponseDto;

/**
 * Casino Service 
 */
public interface CasinoService {

    /**
     * Show available Game Types and ask for one of them.
     * Return the selected Game Type ID
     * @return
     * @throws Exception
     */
    public List<GamesDto> selectGameType() throws Exception;
    
    public GamesDto selectGameType(UUID gameType) throws CasinoException;
    public GamesDto selectGameType(String gameTypeName) throws CasinoException;
    
    
    public ConfigurationsDto createGameConfig(ConfigurationsDto config);

    /**
     * Show available Game Configs for the given Game Type
     * and ask for one of them.
     * Return the selected Game Config ID
     * @param gameTypeId
     * @return
     * @throws Exception
     */
    public List<ConfigurationsDto> selectGameConfig(UUID gameTypeId) throws CasinoException;
    
    public PlayersDto addPlayerToPlatform(PlayersDto player);

    /**
     * Start game with the given User ID and Game Config ID
     * @param userId
     * @param gameConfigId
     * @return
     * @throws Exception
     */
    public RoundResponseDto startGame(RoundDto round) throws CasinoException;

    /**
     * Ask for a new bet for the given Game ID
     * Return the updated Game after apply bet
     * @param roundId round associated to bet
     * @param amount credits to bet
     * @return
     * @throws Exception
     */
    public RoundResponseDto bet(UUID roundId, Double amount) throws CasinoException ;

    /**
     * Ask for user balance
     * Return the user balance
     * @param userId 
     * @return balance as a long
     * @throws Exception
     */
    public Double balance(UUID userId) throws CasinoException ;
}
