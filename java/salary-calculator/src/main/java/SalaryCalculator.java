public class SalaryCalculator {
  public double multiplierPerDaysSkipped(int daysSkipped) {
    return (daysSkipped > 5) ? 0.85 : 1;
  }

  public int multiplierPerProductsSold(int productsSold) {
    return (productsSold > 20) ? 13 : 10;
  }

  public double bonusForProductSold(int productsSold) {
    return productsSold * multiplierPerProductsSold(productsSold);
  }

  public double finalSalary(int daysSkipped, int productsSold) {
    return Math.min(
        2000, 1000 * multiplierPerDaysSkipped(daysSkipped) + bonusForProductSold(productsSold));
  }
}
