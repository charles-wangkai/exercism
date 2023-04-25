class Darts {
  double x;
  double y;

  Darts(double x, double y) {
    this.x = x;
    this.y = y;
  }

  int score() {
    double distance = Math.sqrt(x * x + y * y);
    if (distance <= 1) {
      return 10;
    }
    if (distance <= 5) {
      return 5;
    }
    if (distance <= 10) {
      return 1;
    }

    return 0;
  }
}
