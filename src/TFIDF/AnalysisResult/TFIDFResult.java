package TFIDF.AnalysisResult;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class TFIDFResult {
    Map<Long, List<tfidfItem>> tfidfValue=new HashMap<>();

    public Map<Long, List<tfidfItem>> getTfidfValue() {
        return tfidfValue;
    }

    public void setTfidfValue(Map<Long, List<tfidfItem>> tfidfValue) {
        this.tfidfValue = tfidfValue;
    }

    public void addValue(Long no,String word,BigDecimal tfidfV){
        tfidfItem item=new tfidfItem();
        item.setWord(word);
        item.setTfidf(tfidfV);
        if(tfidfValue.containsKey(no)){
            tfidfValue.get(no).add(item);
        }else{
            List<tfidfItem> itemList=new ArrayList<>();
            itemList.add(item);
            tfidfValue.put(no,itemList);
        }
    }

    public void sort(){
        Comparator<tfidfItem> comparator = Comparator.comparing(tfidfItem::getTfidf);
        for(Long no:tfidfValue.keySet()){
            tfidfValue.put(no,tfidfValue.get(no).stream().sorted(comparator.reversed()).collect(Collectors.toList()));
        }
    }

    public class tfidfItem{
        private String word;
        private BigDecimal tfidf;

        public String getWord() {
            return word;
        }

        public BigDecimal getTfidf() {
            return tfidf;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public void setTfidf(BigDecimal tfidf) {
            this.tfidf = tfidf;
        }
    }
}
