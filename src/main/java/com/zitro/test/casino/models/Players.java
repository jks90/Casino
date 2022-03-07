package com.zitro.test.casino.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PLAYERS")
public class Players implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
	
	private Double balance;
	private Long timeLimit;
	
	private Long timePlaying;
	
	@ManyToOne
	private UsersProviders userProvider;
	
	@OneToMany(mappedBy = "configurations")
    Set<Bet> playerBetConfig;
	
	
	@ManyToMany
	@JoinTable(
	  name = "player_prize", 
	  joinColumns = @JoinColumn(name = "players_id"), 
	  inverseJoinColumns = @JoinColumn(name = "prize_id"))
	private List<Prize> listadoPrices;

}
