import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class AppointmentScheduler {
  public LocalDateTime schedule(String appointmentDateDescription) {
    return LocalDateTime.parse(
        appointmentDateDescription, DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss"));
  }

  public boolean hasPassed(LocalDateTime appointmentDate) {
    return appointmentDate.isBefore(LocalDateTime.now());
  }

  public boolean isAfternoonAppointment(LocalDateTime appointmentDate) {
    int hour = appointmentDate.getHour();

    return hour >= 12 && hour < 18;
  }

  public String getDescription(LocalDateTime appointmentDate) {
    return "You have an appointment on %s."
        .formatted(
            DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy, 'at' h:mm a").format(appointmentDate));
  }

  public LocalDate getAnniversaryDate() {
    return LocalDate.of(LocalDate.now().getYear(), 9, 15);
  }
}
