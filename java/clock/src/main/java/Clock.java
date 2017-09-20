import java.time.LocalTime;

public class Clock {
	LocalTime time;

	Clock(int hour, int minute) {
		time = LocalTime.MIDNIGHT;
		time = time.plusHours(hour);
		time = time.plusMinutes(minute);
	}

	void add(int minutesToAdd) {
		time = time.plusMinutes(minutesToAdd);
	}

	@Override
	public String toString() {
		return time.toString();
	}

	@Override
	public int hashCode() {
		return time.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return time.equals(((Clock) obj).time);
	}
}
