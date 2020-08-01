package com.uwanyi.lottery_draw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.uwanyi.lottery_draw.mapper")
public class LotteryDrawApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryDrawApplication.class, args);
	}

}
