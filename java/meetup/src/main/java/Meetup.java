import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Meetup {
	int month;
	int year;

	Meetup(int month, int year) {
		this.month = month;
		this.year = year;
	}

	LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule schedule) {
		LocalDate date = LocalDate.of(year, month, 1);
		if (schedule == MeetupSchedule.FIRST) {
			return date.with(TemporalAdjusters.firstInMonth(dayOfWeek));
		} else if (schedule == MeetupSchedule.SECOND) {
			return date.with(TemporalAdjusters.dayOfWeekInMonth(2, dayOfWeek));
		} else if (schedule == MeetupSchedule.THIRD) {
			return date.with(TemporalAdjusters.dayOfWeekInMonth(3, dayOfWeek));
		} else if (schedule == MeetupSchedule.FOURTH) {
			return date.with(TemporalAdjusters.dayOfWeekInMonth(4, dayOfWeek));
		} else if (schedule == MeetupSchedule.LAST) {
			return date.with(TemporalAdjusters.lastInMonth(dayOfWeek));
		} else {
			LocalDate result = date.with(TemporalAdjusters.firstInMonth(dayOfWeek));
			while (!(result.getDayOfMonth() >= 13 && result.getDayOfMonth() <= 19)) {
				result = result.with(TemporalAdjusters.next(dayOfWeek));
			}
			return result;
		}
	}
}