import java.util.HashMap;
import java.util.Hashtable;

public class Rates {
    private String base_code;
    private HashMap conversion_rates = new HashMap();

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public HashMap getRates() {
        return conversion_rates;
    }

    public void setRates(HashMap rates) {
        this.conversion_rates = rates;
    }
}
