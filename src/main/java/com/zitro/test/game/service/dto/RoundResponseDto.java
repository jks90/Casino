package com.zitro.test.game.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.zitro.test.types.RoundStatus;

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
@NoArgsConstructor
@AllArgsConstructor
public class RoundResponseDto implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private UUID id;
    private UUID userId;
    private UUID gameConfigId;
    private RoundStatus status;
    private Date creationDate;
    private Double balance;
    private List<PrizeDto> prizes;
    
    private Long playingTime;

}
