import java.util.HashSet;
import java.util.Set;

public class TwoBucket {
  int bucketOneCap;
  int bucketTwoCap;
  int desiredLiters;
  String first;

  TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String first) {
    this.bucketOneCap = bucketOneCap;
    this.bucketTwoCap = bucketTwoCap;
    this.desiredLiters = desiredLiters;
    this.first = first;
  }

  Result getResult() {
    Bucket[] buckets = {new Bucket("one", bucketOneCap), new Bucket("two", bucketTwoCap)};
    if (first.equals("two")) {
      Bucket temp = buckets[0];
      buckets[0] = buckets[1];
      buckets[1] = temp;
    }

    int move = 0;
    String finalBucket;
    int otherBucket;
    Set<State> seen = new HashSet<>();
    while (true) {
      ++move;

      if (buckets[0].liter == 0) {
        buckets[0].liter = buckets[0].cap;
      } else if (buckets[1].cap == desiredLiters) {
        buckets[1].liter = buckets[1].cap;
      } else if (buckets[1].liter == buckets[1].cap) {
        buckets[1].liter = 0;
      } else {
        int transfer = Math.min(buckets[0].liter, buckets[1].cap - buckets[1].liter);
        buckets[0].liter -= transfer;
        buckets[1].liter += transfer;
      }

      if (buckets[0].liter == desiredLiters) {
        finalBucket = buckets[0].label;
        otherBucket = buckets[1].liter;

        break;
      }
      if (buckets[1].liter == desiredLiters) {
        finalBucket = buckets[1].label;
        otherBucket = buckets[0].liter;

        break;
      }

      State state = new State(buckets[0].liter, buckets[1].liter);
      if (seen.contains(state)) {
        throw new UnreachableGoalException();
      }
      seen.add(state);
    }

    return new Result(move, finalBucket, otherBucket);
  }
}

class Bucket {
  String label;
  int cap;
  int liter = 0;

  Bucket(String label, int cap) {
    this.label = label;
    this.cap = cap;
  }
}

record State(int liter1, int liter2) {}
