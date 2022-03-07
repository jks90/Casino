package com.zitro.test.game.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

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
public class PrizeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long prize;
    private String name;

    public String getName() {
        return this.name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 

    public Long getPrize() {
        return prize;
    }

    public void setPrize(Long prize) {
        this.prize = prize;
    }
}
