package TFIDF.AnalysisResult;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TFIDFResult {
    Map<String, BigDecimal> tfidfValue=new HashMap<>();

    public Map<String, BigDecimal> getTfidfValue() {
        return tfidfValue;
    }

    public void setTfidfValue(Map<String, BigDecimal> tfidfValue) {
        this.tfidfValue = tfidfValue;
    }
}
