import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.reactivex.Observable;

public class Hangman {
    public Observable<Output> play(Observable<String> words, Observable<String> letters) {
        State state = new State();
        List<Output> outputs = new ArrayList<>();

        words.subscribe(word -> {
            state.init(word);

            outputs.add(state.toOutput());
        });

        letters.subscribe(letter -> {
            if (state.guess.contains(letter) || state.misses.contains(letter)) {
                state.errorLetter = letter;
            }

            if (state.secret.contains(letter)) {
                for (int i = 0; i < state.secret.length(); ++i) {
                    if (state.secret.charAt(i) == letter.charAt(0)) {
                        state.discovered = String.format("%s%s%s", state.discovered.substring(0, i), letter,
                                state.discovered.substring(i + 1));
                    }
                }

                state.guess.add(letter);

                if (!state.discovered.contains("_")) {
                    state.status = Status.WIN;
                }
            } else {
                state.misses.add(letter);

                state.parts.add(Part.values()[state.parts.size()]);

                if (state.parts.size() == 6) {
                    state.status = Status.LOSS;
                }
            }

            outputs.add(state.toOutput());
        });

        if (state.errorLetter != null) {
            return Observable.error(
                    new IllegalArgumentException(String.format("Letter %s was already played", state.errorLetter)));
        }

        return Observable.fromArray(outputs.toArray(new Output[0]));
    }
}

class State {
    String secret;
    String discovered;
    Set<String> guess;
    Set<String> misses;
    List<Part> parts;
    Status status;
    String errorLetter;

    void init(String word) {
        secret = word;
        discovered = IntStream.range(0, word.length()).mapToObj(i -> "_").collect(Collectors.joining());
        guess = new HashSet<>();
        misses = new HashSet<>();
        parts = new ArrayList<>();
        status = Status.PLAYING;
    }

    Output toOutput() {
        return new Output(secret, discovered, guess, misses, parts, status);
    }
}