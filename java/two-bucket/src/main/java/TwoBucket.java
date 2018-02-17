public class TwoBucket {
	int move;
	String finalBucket;
	int otherBucket;

	TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String first) {
		Bucket[] buckets = { new Bucket("one", bucketOneCap), new Bucket("two", bucketTwoCap) };
		if (first.equals("two")) {
			Bucket temp = buckets[0];
			buckets[0] = buckets[1];
			buckets[1] = temp;
		}

		move = 0;
		while (true) {
			move++;

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
			} else if (buckets[1].liter == desiredLiters) {
				finalBucket = buckets[1].label;
				otherBucket = buckets[0].liter;
				break;
			}
		}
	}

	int getTotalMoves() {
		return move;
	}

	String getFinalBucket() {
		return finalBucket;
	}

	int getOtherBucket() {
		return otherBucket;
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