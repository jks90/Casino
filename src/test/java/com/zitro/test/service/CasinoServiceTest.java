package com.zitro.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.zitro.test.casino.models.Bet;
import com.zitro.test.casino.models.BetTrace;
import com.zitro.test.casino.models.Configurations;
import com.zitro.test.casino.models.Games;
import com.zitro.test.casino.models.Players;
import com.zitro.test.casino.models.Prize;
import com.zitro.test.casino.models.UsersProviders;
import com.zitro.test.casino.repositories.BetRepository;
import com.zitro.test.casino.repositories.BetTraceRepository;
import com.zitro.test.casino.repositories.ConfigurationsRepository;
import com.zitro.test.casino.repositories.GamesRepository;
import com.zitro.test.casino.repositories.PlayersRepository;
import com.zitro.test.casino.repositories.PrizeRepository;
import com.zitro.test.casino.service.CasinoService;
import com.zitro.test.casino.service.impl.CasinoServiceImpl;
import com.zitro.test.game.service.dto.GamesDto;
import com.zitro.test.game.service.dto.PlayersDto;
import com.zitro.test.game.service.dto.RoundDto;
import com.zitro.test.game.service.dto.RoundResponseDto;
import com.zitro.test.game.service.dto.UsersProvidersDto;
import com.zitro.test.types.RoundStatus;

@SpringBootTest
public class CasinoServiceTest {

	@TestConfiguration
	static class CasinoServiceTestContextConfiguration {

		@Bean
		public CasinoService casinoService() {
			return new CasinoServiceImpl();
		}

	}

	@Autowired
	private CasinoService casinoService;

	@MockBean
	private GamesRepository gamesRepository;

	@MockBean
	private PlayersRepository playersRepository;
	
	@MockBean
	private ConfigurationsRepository configurationsRepository;
	
	@MockBean
	private BetRepository betRepository;

	@MockBean
	private PrizeRepository prizeRepository;
	
	@MockBean
	private BetTraceRepository betTraceRepository;


	@Test
	public void when_selectGameType_then_ok() throws Exception {

		// Prepare

		List<Games> listGames = new ArrayList<Games>();
		listGames.add(Games.builder().id(UUID.randomUUID()).name("SLOTS").build());

		Mockito.when(gamesRepository.findAll()).thenReturn(listGames);

		// Use

		List<GamesDto> result = casinoService.selectGameType();

		// Assert

		assertEquals(1, result.size());

	}

	@Test
	public void when_selectGameType_byGameTypeId_then_ok() throws Exception {
		// Prepare

		Mockito.when(gamesRepository.findById(Mockito.any()))
				.thenReturn(Optional.of(Games.builder().id(UUID.randomUUID()).name("SLOTS").build()));

		// Use

		GamesDto result = casinoService.selectGameType(UUID.randomUUID());

		// Assert

		assertEquals("SLOTS", result.getName());
	}

	@Test
	public void when_selectGameType_byGameTypeId_then_Exception() {

		// Use
		Exception exception = assertThrows(Exception.class, () -> casinoService.selectGameType(UUID.randomUUID()));

		// Assert
		assertEquals("Game not found.", exception.getMessage());
	}

	@Test
	public void when_addPlayerToPlatform_then_ok() throws Exception {

		// Prepare
		PlayersDto player = PlayersDto.builder()
									.id(UUID.randomUUID())
									.balance(50.0)
									.userProvider(UsersProvidersDto.builder()
																	.id(UUID.randomUUID())
																	.name("poker start")
																	.build())
									.build();

		Mockito.when(playersRepository.save(Mockito.any())).thenReturn(Players.builder().id(UUID.randomUUID()).balance(50.0).build());

		// Use

		PlayersDto result = casinoService.addPlayerToPlatform(player);

		// Assert

		assertEquals(50.0, result.getBalance());

	}
	
	@Test
	public void when_startGame_then_ok() throws Exception {

		// Prepare
		Players player = Players.builder()
								.id(UUID.randomUUID())
								.balance(50.0)
								.listadoPrices(null)
								.build();
		
		Configurations config = Configurations.builder()
								.id(UUID.randomUUID())
								.build();
		
		Bet betTarget = Bet.builder()
						.id(UUID.randomUUID())
						.player(Players.builder()
								.id(UUID.randomUUID())
								.balance(50.0)
								.listadoPrices(null)
								.build())
						.status(RoundStatus.ACTIVE)
						.configurations(Configurations.builder()
								.id(UUID.randomUUID())
								.build())
						.timeCreate(new Date())
						.listadoBetTraces(null)
						.build();
		
		Mockito.when( betRepository.save( Mockito.any(Bet.class) ) ).thenReturn(betTarget);

		Mockito.when( playersRepository.findById( Mockito.any() ) ).thenReturn(Optional.of(player));
		
		Mockito.when( configurationsRepository.findById( Mockito.any() ) ).thenReturn(Optional.of(config));
		

		// Use

		RoundResponseDto result = casinoService.startGame(RoundDto.builder()
																	.userId(player.getId())
																	.gameConfigId(config.getId())
																	.build());

		// Assert

		assertEquals(50.0, result.getBalance());

	}
	
	@Test
	public void when_startGame_then_Exception() {
		

		// Use
		Exception exception = assertThrows(Exception.class, () -> casinoService.startGame(RoundDto.builder()
				.userId(UUID.randomUUID())
				.gameConfigId(UUID.randomUUID())
				.build()));

		// Assert
		assertEquals("Game not found.", exception.getMessage());
	}
	
	@Test
	public void when_bet_then_ok() throws Exception {
		
		
		List<BetTrace> listBetTraces = new ArrayList<BetTrace>();
		
		listBetTraces.add(BetTrace.builder()
										.id(UUID.randomUUID())
										
										.build());
		
		Prize prize = Prize.builder()
				.id(UUID.randomUUID())
				.name("SLOTS")
				.build();
		
		List<Prize> listPrizes = new ArrayList<Prize>();
		
		listPrizes.add(prize);

		Bet betTarget = Bet.builder()
							.id(UUID.randomUUID())
							.player(Players.builder()
									.id(UUID.randomUUID())
									.balance(50.0)
									.listadoPrices(listPrizes)
									.timeLimit(250000L)
									.timePlaying(0L)
									.userProvider(UsersProviders.builder()
																	.id(UUID.randomUUID())
																	.name("POKER START")
																	.build())
									.build())
				
							.configurations(Configurations.builder()
									.id(UUID.randomUUID())
									.gameType(Games.builder()
											.id(UUID.randomUUID())
											.name("SLOTS")
											.build())
									.costTime(1000L)
									.provability(100.0)
									.minBet(1.0)
									.maxBet(1000.0)
									.prize(50.0)
									.build())

							.status(RoundStatus.ACTIVE)
							.timeCreate(new Date())
							.listadoBetTraces(listBetTraces)
							.build();

		BetTrace betTrace = BetTrace.builder().build();
		
		Mockito.when( betRepository.findById( Mockito.any(UUID.class)  )  ).thenReturn( Optional.of(betTarget) );
		Mockito.when( betRepository.save( Mockito.any(Bet.class)  )  ).thenReturn( betTarget );
		Mockito.when( prizeRepository.save( Mockito.any(Prize.class)  )  ).thenReturn( prize );
		Mockito.when( betTraceRepository.save( Mockito.any(BetTrace.class)  )  ).thenReturn( betTrace );
		
		// Use
		
		RoundResponseDto result = casinoService.bet(UUID.randomUUID(),50.0);
		
		// Assert

		assertEquals(100.0, result.getBalance());
	}
	
	@Test
	public void when_bet_provability_false_then_ok() throws Exception {
		
		
		List<BetTrace> listBetTraces = new ArrayList<BetTrace>();
		
		listBetTraces.add(BetTrace.builder()
										.id(UUID.randomUUID())
										
										.build());
		
		Prize prize = Prize.builder()
				.id(UUID.randomUUID())
				.name("SLOTS")
				.build();
		
		List<Prize> listPrizes = new ArrayList<Prize>();
		
		listPrizes.add(prize);

		Bet betTarget = Bet.builder()
							.id(UUID.randomUUID())
							.player(Players.builder()
									.id(UUID.randomUUID())
									.balance(50.0)
									.listadoPrices(listPrizes)
									.timeLimit(250000L)
									.timePlaying(0L)
									.userProvider(UsersProviders.builder()
																	.id(UUID.randomUUID())
																	.name("POKER START")
																	.build())
									.build())
				
							.configurations(Configurations.builder()
									.id(UUID.randomUUID())
									.gameType(Games.builder()
											.id(UUID.randomUUID())
											.name("SLOTS")
											.build())
									.costTime(1000L)
									.provability(1.0)
									.minBet(1.0)
									.maxBet(1000.0)
									.prize(50.0)
									.build())

							.status(RoundStatus.ACTIVE)
							.timeCreate(new Date())
							.listadoBetTraces(listBetTraces)
							.build();

		BetTrace betTrace = BetTrace.builder().build();
		
		Mockito.when( betRepository.findById( Mockito.any(UUID.class)  )  ).thenReturn( Optional.of(betTarget) );
		Mockito.when( betRepository.save( Mockito.any(Bet.class)  )  ).thenReturn( betTarget );
		Mockito.when( prizeRepository.save( Mockito.any(Prize.class)  )  ).thenReturn( prize );
		Mockito.when( betTraceRepository.save( Mockito.any(BetTrace.class)  )  ).thenReturn( betTrace );
		
		// Use
		
		RoundResponseDto result = casinoService.bet(UUID.randomUUID(),50.0);
		
		// Assert

		assertEquals(0.0, result.getBalance());
	}

	@Test
	public void when_bet_then_Exception() {
		

		// Use
		Exception exception = assertThrows(Exception.class, () -> casinoService.bet(UUID.randomUUID(),50.0));

		// Assert
		assertEquals("The bet not found.", exception.getMessage());
	}
	
}
