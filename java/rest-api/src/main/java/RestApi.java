import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestApi {
  private static final double EPSILON = 1e-9;

  private Map<String, Map<String, Double>> userToLedger = new HashMap<>();

  public RestApi(User... users) {
    for (User user : users) {
      userToLedger.put(user.name(), new HashMap<>());
    }

    for (User user : users) {
      for (Iou iou : user.owes()) {
        updateRecord(iou.name, user.name(), iou.amount);
      }
    }
  }

  public String get(String url) {
    if (url.equals("/users")) {
      return getUsers(userToLedger.keySet().stream().sorted().toList());
    }

    throw new UnsupportedOperationException();
  }

  public String get(String url, JSONObject payload) {
    if (url.equals("/users")) {
      return getUsers(
          payload.getJSONArray("users").toList().stream().map(String::valueOf).toList());
    }

    throw new UnsupportedOperationException();
  }

  public String post(String url, JSONObject payload) {
    if (url.equals("/add")) {
      String user = payload.getString("user");
      userToLedger.put(user, new HashMap<>());

      return buildUserOutput(user).toString();
    }
    if (url.equals("/iou")) {
      String lender = payload.getString("lender");
      String borrower = payload.getString("borrower");
      double amount = payload.getDouble("amount");
      updateRecord(lender, borrower, amount);

      return getUsers(Stream.of(lender, borrower).sorted().toList());
    }

    throw new UnsupportedOperationException();
  }

  private String getUsers(List<String> users) {
    return new JSONObject()
        .put("users", new JSONArray(users.stream().map(this::buildUserOutput).toList()))
        .toString();
  }

  private void updateRecord(String lender, String borrower, double amount) {
    Map<String, Double> lenderLedger = userToLedger.get(lender);
    lenderLedger.put(borrower, lenderLedger.getOrDefault(borrower, 0.0) + amount);
    if (Math.abs(lenderLedger.get(borrower)) < EPSILON) {
      lenderLedger.remove(borrower);
    }

    Map<String, Double> borrowerLedger = userToLedger.get(borrower);
    borrowerLedger.put(lender, borrowerLedger.getOrDefault(lender, 0.0) - amount);
    if (Math.abs(borrowerLedger.get(lender)) < EPSILON) {
      borrowerLedger.remove(lender);
    }
  }

  private JSONObject buildUserOutput(String user) {
    Map<String, Double> ledger = userToLedger.get(user);

    return new JSONObject()
        .put("name", user)
        .put(
            "owes",
            new JSONObject(
                ledger.keySet().stream()
                    .filter(other -> ledger.get(other) < -EPSILON)
                    .collect(Collectors.toMap(Function.identity(), other -> -ledger.get(other)))))
        .put(
            "owedBy",
            new JSONObject(
                ledger.keySet().stream()
                    .filter(other -> ledger.get(other) > EPSILON)
                    .collect(Collectors.toMap(Function.identity(), other -> ledger.get(other)))))
        .put("balance", ledger.values().stream().mapToDouble(Double::doubleValue).sum());
  }
}
