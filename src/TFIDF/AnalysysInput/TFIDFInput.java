package TFIDF.AnalysysInput;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TFIDFInput {
    private long sentenceCounter=0;
    private Map<Integer, List<String>> sentenceList=new HashMap<>();
    private BigDecimal weight=BigDecimal.ONE;

    public long getSentenceCounter() {
        return sentenceCounter;
    }

    public Map<Integer, List<String>> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceCounter(long sentenceCounter) {
        this.sentenceCounter = sentenceCounter;
    }

    public void setSentenceList(Map<Integer, List<String>> sentenceList) {
        this.sentenceList = sentenceList;
    }

    public void addSentence(List<String> sentence){
        sentenceList.put(sentenceList.keySet().size()+1,sentence);

    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
