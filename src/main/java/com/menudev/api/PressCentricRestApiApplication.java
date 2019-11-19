package com.menudev.api;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
public class PressCentricRestApiApplication {

    private final ScheduledExecutorService scheduledExecutorService =Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        SpringApplication.run(PressCentricRestApiApplication.class, args);
    }

//    @Bean
//    ApplicationRunner runner(MeterRegistry mr) {
//        return args -> {
//            this.scheduledExecutorService.scheduleWithFixedDelay(() -> mr.timer("timer-post-sample")
//					.record(Duration.ofMillis((long) (Math.random() * 1000))), 500, 500, TimeUnit.MILLISECONDS);
//            mr.counter("smapleCounter", "testTag", String.valueOf(Math.random()*1000));
//        };
//    }

	@Bean
	public MeterRegistry getMeterRegister(){
    	return new SimpleMeterRegistry();
	}


}
