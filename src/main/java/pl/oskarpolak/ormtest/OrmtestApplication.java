package pl.oskarpolak.ormtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan(
		basePackageClasses = {OrmtestApplication.class, Jsr310JpaConverters.class}
)

@EnableAsync
public class OrmtestApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrmtestApplication.class, args);
	}
}
