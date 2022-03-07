package com.zitro.test.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.zitro.test.casino.exceptions.CasinoException;
import com.zitro.test.casino.models.Bet;
import com.zitro.test.casino.models.Configurations;
import com.zitro.test.casino.models.Games;
import com.zitro.test.casino.models.Players;
import com.zitro.test.casino.models.UsersProviders;
import com.zitro.test.casino.repositories.BetRepository;
import com.zitro.test.casino.repositories.ConfigurationsRepository;
import com.zitro.test.casino.repositories.PlayersRepository;
import com.zitro.test.casino.repositories.UsersProvidersRepository;
import com.zitro.test.casino.validators.CasinoValidator;
import com.zitro.test.casino.validators.impl.CasinoValidatorImpl;
import com.zitro.test.game.service.dto.ConfigurationsDto;
import com.zitro.test.types.RoundStatus;

@SpringBootTest
public class CasinoValidatorTest {
	
	@TestConfiguration
	static class CasinoValidatorTestContextConfiguration {
		
		@Bean 
		public CasinoValidator casinoValidator() {
			return new CasinoValidatorImpl();
		}
	}
	
	@Autowired
	private CasinoValidator casinoValidator;
	
	@MockBean
	private UsersProvidersRepository usersProvidersRepository;
	
	@MockBean
	private ConfigurationsRepository configurationsRepository;

	@MockBean
	private PlayersRepository playersRepository;
	
	@MockBean
	private BetRepository betRepository;
	
	@Test
	public void when_createConfig_then_Exception_cost_time_not_found() {
		
		ConfigurationsDto config = ConfigurationsDto.builder()
														.costTime(999L)
														.build();
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.createConfig(config));
        
        assertEquals("Min 1000 millis.", exception.getMessage());
	}
	
	@Test
	public void when_createConfig_then_Exception_max_bet_not_found() {
		
		ConfigurationsDto config = ConfigurationsDto.builder()
														.costTime(1001L)
														.maxBet(0.0)
														.build();
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.createConfig(config));
        
        assertEquals("Max bet cannot be 0.", exception.getMessage());
	}
	
	@Test
	public void when_createConfig_then_Exception_min_bet_not_found() {
		
		ConfigurationsDto config = ConfigurationsDto.builder()
														.costTime(1001L)
														.maxBet(100.0)
														.minBet(0.0)
														.build();
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.createConfig(config));
        
        assertEquals("Min bet cannot be 0.", exception.getMessage());
	}
	
	@Test
	public void when_createConfig_then_Exception_bet_not_found() {
		
		ConfigurationsDto config = ConfigurationsDto.builder()
														.costTime(1001L)
														.maxBet(5.0)
														.minBet(6.0)
														.build();
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.createConfig(config));
        
        assertEquals("Max bet can not be less than the min bet.", exception.getMessage());
	}
	
	@Test
	public void when_createConfig_then_Exception_prize_not_found() {
		
		ConfigurationsDto config = ConfigurationsDto.builder()
														.costTime(1001L)
														.maxBet(7.0)
														.minBet(6.0)
														.prize(0.0)
														.build();
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.createConfig(config));
        
        assertEquals("Prize cannot be 0.", exception.getMessage());
	}

	@Test
	public void when_createConfig_then_Exception_provability_not_found() {
		
		ConfigurationsDto config = ConfigurationsDto.builder()
													.costTime(1001L)
													.maxBet(7.0)
													.minBet(6.0)
													.prize(10.0)
													.provability(101.0)
													.build();
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.createConfig(config));
		
		assertEquals("The provability hat to be between 0 and 100.", exception.getMessage());
	}
	
	@Test
	public void when_addPlayerToPlatform_then_Exception_userProvider_not_found() {
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.addPlayerToPlatform("f297388b-9c3c-47da-9b9d-ff72f358dc45",50.0));
		
		assertEquals("The user provider not found.", exception.getMessage());
	}
	
	@Test
	public void when_addPlayerToPlatform_then_Exception_amount_not_found() {
		
		Mockito.when(usersProvidersRepository.findById(Mockito.any())).thenReturn(Optional.of(UsersProviders.builder()
																											.id(UUID.randomUUID())
																											.name("POKER START")
																											.build()));
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.addPlayerToPlatform("f297388b-9c3c-47da-9b9d-ff72f358dc45",0.0));
		
		assertEquals("Amount cannot be 0.", exception.getMessage());
	}
	
	@Test
	public void when_playerStartGame_then_Exception_amount_not_available() {
		
		Players player = Players.builder()
				.id(UUID.randomUUID())
				.balance(50.0)
				.listadoPrices(null)
				.build();

		Configurations config = Configurations.builder()
				.id(UUID.randomUUID())
				.minBet(60.0)
				.build();
		
		Mockito.when(playersRepository.findById(Mockito.any())).thenReturn(Optional.of(player));
		Mockito.when(configurationsRepository.findById(Mockito.any())).thenReturn(Optional.of(config));
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.playerStartGame("f297388b-9c3c-47da-9b9d-ff72f358dc45","f297388b-9c3c-47da-9b9d-ff72f358dc45"));
		
		assertEquals("Amount not available.", exception.getMessage());
	}
	
	@Test
	public void when_playerStartGame_then_Exception_player_not_found() {
		
		Configurations config = Configurations.builder()
				.id(UUID.randomUUID())
				.minBet(60.0)
				.build();
		
		Mockito.when(configurationsRepository.findById(Mockito.any())).thenReturn(Optional.of(config));
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.playerStartGame("f297388b-9c3c-47da-9b9d-ff72f358dc45","f297388b-9c3c-47da-9b9d-ff72f358dc45"));
		
		assertEquals("The player not found.", exception.getMessage());
	}
	
	@Test
	public void when_playerStartGame_then_Exception_configurations_not_found() {
		
		Players player = Players.builder()
				.id(UUID.randomUUID())
				.balance(50.0)
				.listadoPrices(null)
				.build();

		Mockito.when(playersRepository.findById(Mockito.any())).thenReturn(Optional.of(player));
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.playerStartGame("f297388b-9c3c-47da-9b9d-ff72f358dc45","f297388b-9c3c-47da-9b9d-ff72f358dc45"));
		
		assertEquals("The configuration not found.", exception.getMessage());
	}
	
	@Test
	public void when_updateAmountBet_then_Exception_player_config_not_found() {
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.updateAmountBet("f297388b-9c3c-47da-9b9d-ff72f358dc45",50.0));
		
		assertEquals("The bet not found.", exception.getMessage());
	}
	
	@Test
	public void when_updateAmountBet_then_Exception_bet_status_not_found() {
		
		Bet betTarget = Bet.builder()
				.id(UUID.randomUUID())
				.player(Players.builder()
						.id(UUID.randomUUID())
						.balance(50.0)
						.listadoPrices(null)
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

				.status(RoundStatus.FINISHED)
				.timeCreate(new Date())
				.listadoBetTraces(null)
				.build();
		
		Mockito.when( betRepository.findById( Mockito.any(UUID.class)  )  ).thenReturn( Optional.of(betTarget) );
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.updateAmountBet("f297388b-9c3c-47da-9b9d-ff72f358dc45",50.0));
		
		assertEquals("Bet finished.", exception.getMessage());
	}
	
	@Test
	public void when_updateAmountBet_then_Exception_amount_not_available() {
		
		Bet betTarget = Bet.builder()
				.id(UUID.randomUUID())
				.player(Players.builder()
						.id(UUID.randomUUID())
						.balance(5.0)
						.listadoPrices(null)
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
				.listadoBetTraces(null)
				.build();
		
		Mockito.when( betRepository.findById( Mockito.any(UUID.class)  )  ).thenReturn( Optional.of(betTarget) );
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.updateAmountBet("f297388b-9c3c-47da-9b9d-ff72f358dc45",50.0));
		
		assertEquals("Amount not available.", exception.getMessage());
	}
	
	@Test
	public void when_updateAmountBet_then_Exception_time_not_available() {
		
		Bet betTarget = Bet.builder()
				.id(UUID.randomUUID())
				.player(Players.builder()
						.id(UUID.randomUUID())
						.balance(500.0)
						.listadoPrices(null)
						.timeLimit(0L)
						.timePlaying(5L)
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
				.listadoBetTraces(null)
				.build();
		
		Mockito.when( betRepository.findById( Mockito.any(UUID.class)  )  ).thenReturn( Optional.of(betTarget) );
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.updateAmountBet("f297388b-9c3c-47da-9b9d-ff72f358dc45",50.0));
		
		assertEquals("Time not available.", exception.getMessage());
	}

	@Test
	public void when_updateAmountBet_then_Exception_amount_not_found() {
		
		Bet betTarget = Bet.builder()
				.id(UUID.randomUUID())
				.player(Players.builder()
						.id(UUID.randomUUID())
						.balance(1.0)
						.listadoPrices(null)
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
				.listadoBetTraces(null)
				.build();
		
		Mockito.when( betRepository.findById( Mockito.any(UUID.class)  )  ).thenReturn( Optional.of(betTarget) );
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.updateAmountBet("f297388b-9c3c-47da-9b9d-ff72f358dc45",0.0));
		
		assertEquals("Amount cannot be 0.", exception.getMessage());
	}
	
	@Test
	public void when_updateAmountBet_then_Exception_min_bet_amount_not_found() {
		
		Bet betTarget = Bet.builder()
				.id(UUID.randomUUID())
				.player(Players.builder()
						.id(UUID.randomUUID())
						.balance(1055.0)
						.listadoPrices(null)
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
						.minBet(61.0)
						.maxBet(1000.0)
						.prize(50.0)
						.build())

				.status(RoundStatus.ACTIVE)
				.timeCreate(new Date())
				.listadoBetTraces(null)
				.build();
		
		Mockito.when( betRepository.findById( Mockito.any(UUID.class)  )  ).thenReturn( Optional.of(betTarget) );
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.updateAmountBet("f297388b-9c3c-47da-9b9d-ff72f358dc45",50.0));
		
		assertEquals("Amount greater than min bet.", exception.getMessage());
	}
	
	@Test
	public void when_updateAmountBet_then_Exception_max_bet_amount_not_found() {
		
		Bet betTarget = Bet.builder()
				.id(UUID.randomUUID())
				.player(Players.builder()
						.id(UUID.randomUUID())
						.balance(1055.0)
						.listadoPrices(null)
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
						.minBet(61.0)
						.maxBet(1000.0)
						.prize(50.0)
						.build())

				.status(RoundStatus.ACTIVE)
				.timeCreate(new Date())
				.listadoBetTraces(null)
				.build();
		
		Mockito.when( betRepository.findById( Mockito.any(UUID.class)  )  ).thenReturn( Optional.of(betTarget) );
		
		CasinoException exception = assertThrows(CasinoException.class, () -> casinoValidator.updateAmountBet("f297388b-9c3c-47da-9b9d-ff72f358dc45",1050.0));
		
		assertEquals("Amount less than min bet.", exception.getMessage());
	}
}
