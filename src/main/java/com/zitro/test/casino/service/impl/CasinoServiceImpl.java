package com.zitro.test.casino.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zitro.test.casino.constants.Constants;
import com.zitro.test.casino.exceptions.CasinoException;
import com.zitro.test.casino.models.BetTrace;
import com.zitro.test.casino.models.Configurations;
import com.zitro.test.casino.models.Games;
import com.zitro.test.casino.models.Bet;
import com.zitro.test.casino.models.Players;
import com.zitro.test.casino.models.Prize;
import com.zitro.test.casino.repositories.BetTraceRepository;
import com.zitro.test.casino.repositories.ConfigurationsRepository;
import com.zitro.test.casino.repositories.GamesRepository;
import com.zitro.test.casino.repositories.BetRepository;
import com.zitro.test.casino.repositories.PlayersRepository;
import com.zitro.test.casino.repositories.PrizeRepository;
import com.zitro.test.casino.service.CasinoService;
import com.zitro.test.casino.transformer.ConfigurationsMapper;
import com.zitro.test.casino.transformer.GamesMapper;
import com.zitro.test.casino.transformer.PlayersMapper;
import com.zitro.test.casino.transformer.PrizeMapper;
import com.zitro.test.casino.transformer.UsersProvidersMapper;
import com.zitro.test.game.service.dto.ConfigurationsDto;
import com.zitro.test.game.service.dto.GamesDto;
import com.zitro.test.game.service.dto.PlayersDto;
import com.zitro.test.game.service.dto.RoundDto;
import com.zitro.test.game.service.dto.RoundResponseDto;
import com.zitro.test.types.RoundStatus;

@Service
public class CasinoServiceImpl implements CasinoService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CasinoServiceImpl.class);

	@Autowired
	private GamesRepository gamesRepository;

	@Autowired
	private GamesMapper gamesMapper;

	@Autowired
	private ConfigurationsMapper configurationsMapper;

	@Autowired
	private ConfigurationsRepository configurationsRepository;

	@Autowired
	private PlayersRepository playersRepository;

	@Autowired
	private PlayersMapper playersMapper;

	@Autowired
	private UsersProvidersMapper usersProvidersMapper;

	@Autowired
	private BetRepository betRepository;
	
	@Autowired
	private PrizeMapper prizeMapper;
	
	@Autowired
	private PrizeRepository prizeRepository;
	
	@Autowired
	private BetTraceRepository betTraceRepository;

	@Override
	public List<GamesDto> selectGameType() throws Exception {
		log.info("selectGameType");
		List<Games> listgames = gamesRepository.findAll();
		return listgames.stream().map(x -> gamesMapper.gamesToGamesDto(x)).collect(Collectors.toList());
	}

	@Override
	public GamesDto selectGameType(UUID gameType) throws CasinoException {
		log.info("selectGameType by uuid");
		Optional<Games> game = gamesRepository.findById(gameType);
		if (game.isPresent()) {
			return gamesMapper.gamesToGamesDto(game.get());
		} else {
			throw new CasinoException(Constants.GAME_NOT_FOUND);
		}
	}

	@Override
	public GamesDto selectGameType(String gameTypeName) throws CasinoException {
		log.info("selectGameType by name");
		Optional<Games> game = gamesRepository.findByName(gameTypeName);
		if (game.isPresent()) {
			return gamesMapper.gamesToGamesDto(game.get());
		} else {
			throw new CasinoException(Constants.GAME_NOT_FOUND);
		}
	}

	@Override
	public ConfigurationsDto createGameConfig(ConfigurationsDto config) {
		log.info("createGameConfig");
		Configurations configSaved = configurationsRepository
				.save(configurationsMapper.configurationsDtotoConfigurations(config));
		return configurationsMapper.configurationsToConfigurationsDto(configSaved);
	}

	@Override
	public List<ConfigurationsDto> selectGameConfig(UUID gameTypeId) throws CasinoException {
		log.info("selectGameConfig");
		Optional<List<Configurations>> listConfigurations = configurationsRepository.findByGameTypeId(gameTypeId);
		if (listConfigurations.isPresent()) {
			return configurationsMapper.configurationsListToConfigurationsDtoList(listConfigurations.get());
		} else {
			throw new CasinoException(Constants.CONFIGURATIONS_NOT_FOUND);
		}
	}

	@Override
	public PlayersDto addPlayerToPlatform(PlayersDto player) {
		log.info("addPlayerToPlatform");
		Players playerTarget = playersMapper.playersDtotoPlayers(player);
		//cada euro = 5 seg = 5000 millis
		playerTarget.setTimeLimit(player.getBalance().longValue() * Constants.EURO_BY_TIME);
		playerTarget.setTimePlaying(0L);
		playerTarget.setUserProvider(usersProvidersMapper.usersProvidersDtotoUsersProviders(player.getUserProvider()));
		
		Players playerSaved = playersRepository.save(playerTarget);
		return playersMapper.playersToPlayersDto(playerSaved);
	}

	@Override
	public RoundResponseDto startGame(RoundDto round) throws CasinoException {
		log.info("startGame");
		Optional<Players> playerTarget = playersRepository.findById(round.getUserId());
		Optional<Configurations> configTarget = configurationsRepository.findById(round.getGameConfigId());
		if (playerTarget.isPresent() && configTarget.isPresent()) {

			Bet betTarget = betRepository.save(Bet.builder()
					.player(playerTarget.get())
					.configurations(configTarget.get())
					.status(RoundStatus.ACTIVE)
					.timeCreate(new Date()).build());

			log.info("Game: " + betTarget.getId());
			return RoundResponseDto.builder()
					.id(betTarget.getId())
					.userId(betTarget.getPlayer().getId())
					.gameConfigId(betTarget.getConfigurations().getId())
					.status(betTarget.getStatus())
					.creationDate(betTarget.getTimeCreate())
					.balance(betTarget.getPlayer().getBalance())
					.prizes(prizeMapper.prizeListToPrizeDtoList(betTarget.getPlayer().getListadoPrices()))
					.playingTime(0L)
					.build();

		} else {
			throw new CasinoException(Constants.GAME_NOT_FOUND);
		}
	}

	@Override
	public RoundResponseDto bet(UUID roundId, Double amount) throws CasinoException {
		Optional<Bet> betTarget = betRepository.findById(roundId);
		if (betTarget.isPresent()) {
			log.info("bet: " + betTarget.get().getPlayer().getId().toString());
			betTarget.get().getPlayer().setTimePlaying(betTarget.get().getPlayer().getTimePlaying() + betTarget.get().getConfigurations().getCostTime());
			betTarget.get().getPlayer().setTimeLimit(betTarget.get().getPlayer().getTimeLimit() - betTarget.get().getConfigurations().getCostTime());
			
			List<BetTrace> listBetTraces = new ArrayList<BetTrace>();
			StringBuilder info = new StringBuilder();
			if(jugada(betTarget.get().getConfigurations())) {
				//toca
				betTarget.get().getPlayer().setBalance(betTarget.get().getPlayer().getBalance() + betTarget.get().getConfigurations().getPrize());
				betTarget.get().setStatus(RoundStatus.FINISHED);
				
				Prize prizeSaved = prizeRepository.save(Prize.builder()
											.name(betTarget.get().getConfigurations().getGameType().getName())
											.prize(betTarget.get().getConfigurations().getPrize().longValue())
											.build());
				List<Prize> listPrize = new ArrayList<Prize>();
				
				
				listPrize.addAll(betTarget.get().getPlayer().getListadoPrices());
				listPrize.add(prizeSaved);
				betTarget.get().getPlayer().setListadoPrices(listPrize);
				
				BetTrace betTrace = new BetTrace();
				betTrace.setTimeBetCreate(new Date());
				betTrace.setAmount(amount);
				betTrace.setResult(true);
				betTrace.setTotalPlayer(betTarget.get().getPlayer().getBalance());
				betTraceRepository.save(betTrace);
				
				listBetTraces.addAll(betTarget.get().getListadoBetTraces());
				listBetTraces.add(betTrace);
				
				info.append("PREMIO: ");
				info.append(betTarget.get().getPlayer().getId().toString());
				info.append(" GANA: ");
				info.append(betTarget.get().getConfigurations().getPrize());
				info.append(" TOTAL: ");
				info.append(betTarget.get().getPlayer().getBalance().toString());
				info.append(" GAME: ");
				info.append(betTarget.get().getConfigurations().getGameType().getName());
				info.append(" TIME PLAYING: ");
				info.append(betTarget.get().getPlayer().getTimePlaying());
				
			}else {
				//no toca
				betTarget.get().getPlayer().setBalance(betTarget.get().getPlayer().getBalance() - amount);
				
				BetTrace betTrace = new BetTrace();
				betTrace.setTimeBetCreate(new Date());
				betTrace.setAmount(amount);
				betTrace.setResult(false);
				betTrace.setTotalPlayer(betTarget.get().getPlayer().getBalance());
				betTraceRepository.save(betTrace);
				
				listBetTraces.addAll(betTarget.get().getListadoBetTraces());
				listBetTraces.add(betTrace);
				
				info.append("SIGUE JUGANDO: ");
				info.append(betTarget.get().getPlayer().getId().toString());
				info.append(" PIERDE: ");
				info.append(amount);
				info.append(" TOTAL: ");
				info.append(betTarget.get().getPlayer().getBalance().toString());
				info.append(" GAME: ");
				info.append(betTarget.get().getConfigurations().getGameType().getName());
				info.append(" TIME PLAYING: ");
				info.append(betTarget.get().getPlayer().getTimePlaying());
				
			}
			log.info(info.toString());
			
			betTarget.get().setListadoBetTraces(listBetTraces);
			betRepository.save(betTarget.get());
			
			return RoundResponseDto.builder()
					.id(betTarget.get().getId())
					.userId(betTarget.get().getPlayer().getId())
					.gameConfigId(betTarget.get().getConfigurations().getId())
					.status(betTarget.get().getStatus())
					.creationDate(betTarget.get().getTimeCreate())
					.balance(betTarget.get().getPlayer().getBalance())
					.playingTime(betTarget.get().getPlayer().getTimePlaying())
					.prizes(prizeMapper.prizeListToPrizeDtoList(betTarget.get().getPlayer().getListadoPrices()))
					.build();
		} else {
			throw new CasinoException(Constants.PLAYER_CONFIGURATIONS_NOT_FOUND);
		}
	}

	@Override
	public Double balance(UUID userId) throws CasinoException {
		Optional<Players> playerTarget = playersRepository.findById(userId);
		if (playerTarget.isPresent()) {
			return playerTarget.get().getBalance();
		} else {
			throw new CasinoException(Constants.PLAYER_NOT_FOUND);
		}
	}
	
	private Boolean jugada(Configurations config) {
		StringBuilder info = new StringBuilder();
		boolean result = false;
		int numero = (int)(Math.random() * (100/config.getProvability().intValue()) + 1);
		if(numero == 1) {
			result = true;
		}
		
		info.append("NUMERO: ");
		info.append(numero);
		info.append(" APUESTA: ");
		info.append(result);
		info.append(" PROVABILITY: ");
		info.append(config.getProvability().intValue());
		
		log.info(info.toString());
		return result;
	}

}
