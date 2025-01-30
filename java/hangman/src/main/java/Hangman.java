import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hangman {
  public Observable<Output> play(Observable<String> words, Observable<String> letters) {
    List<Output> outputs = new ArrayList<>();
    Throwable throwable = new Throwable();

    words.subscribe(
        word -> {
          outputs.add(
              new Output(
                  word, "_".repeat(word.length()), Set.of(), Set.of(), List.of(), Status.PLAYING));
        });

    letters.subscribe(
        letter -> {
          Output prev = outputs.getLast();

          if (prev.guess.contains(letter) || prev.misses.contains(letter)) {
            throw new IllegalArgumentException("Letter %s was already played".formatted(letter));
          }

          String secret = prev.secret;
          String discovered = prev.discovered;
          Set<String> guess = new HashSet<>(prev.guess);
          Set<String> misses = new HashSet<>(prev.misses);
          List<Part> parts = new ArrayList<>(prev.parts);
          Status status = prev.status;

          if (secret.contains(letter)) {
            for (int i = 0; i < secret.length(); ++i) {
              if (secret.charAt(i) == letter.charAt(0)) {
                discovered = discovered.substring(0, i) + letter + discovered.substring(i + 1);
              }
            }

            guess.add(letter);

            if (!discovered.contains("_")) {
              status = Status.WIN;
            }
          } else {
            misses.add(letter);

            parts.add(Part.values()[parts.size()]);

            if (parts.size() == Part.values().length) {
              status = Status.LOSS;
            }
          }

          outputs.add(new Output(secret, discovered, guess, misses, parts, status));
        },
        error -> {
          if (throwable.getCause() == null) {
            throwable.initCause(error);
          }
        });

    if (throwable.getCause() != null) {
      return Observable.error(throwable.getCause());
    }

    return Observable.fromIterable(outputs);
  }
}
