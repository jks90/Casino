package com.zitro.test.casino.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.zitro.test.types.RoundStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BETS")
public class Bet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
	
	@ManyToOne
    @JoinColumn(name = "players_id")
	private Players player;
	
	@ManyToOne
    @JoinColumn(name = "configurations_id")
	private Configurations configurations;
	
	private Date timeCreate;
	
	@Enumerated(EnumType.ORDINAL)
	private RoundStatus status;
	
	@ManyToMany
	@JoinTable(
	  name = "bet_betTrace", 
	  joinColumns = @JoinColumn(name = "bet_id"), 
	  inverseJoinColumns = @JoinColumn(name = "betTrace_id"))
	private List<BetTrace> listadoBetTraces;

}
