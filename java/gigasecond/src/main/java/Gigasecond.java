import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class Gigasecond {
	LocalDateTime birthDateTime;

	Gigasecond(LocalDate birthDate) {
		birthDateTime = LocalDateTime.of(birthDate, LocalTime.MIDNIGHT);
	}

	Gigasecond(LocalDateTime birthDateTime) {
		this.birthDateTime = birthDateTime;
	}

	LocalDateTime getDate() {
		return birthDateTime.plusSeconds(1000000000);
	}

}
