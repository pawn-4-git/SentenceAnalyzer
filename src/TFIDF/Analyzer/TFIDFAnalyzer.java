package TFIDF.Analyzer;

import TFIDF.AnalysisResult.TFIDFResult;
import TFIDF.AnalysysInput.TFIDFInput;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TFIDFAnalyzer {

    public TFIDFResult analyze(TFIDFInput input){
        TFIDFResult result=new TFIDFResult();
        List<String> wordList=new ArrayList<>();
        Map<Long,Map<String,Long>> tfWordCount=new HashMap<>();
        Map<Long,BigDecimal> sentenceCount=new HashMap<>();
        Map<String,Long> tfWordCountItem=null;
        for(Integer no:input.getSentenceList().keySet()){
            tfWordCountItem=new HashMap<>();
            BigDecimal count=BigDecimal.valueOf(0);
            for(String word:input.getSentenceList().get(no)){
                if(!tfWordCountItem.containsKey(word)){
                    tfWordCountItem.put(word,1L);
                }else{
                    tfWordCountItem.put(word,tfWordCountItem.get(word)+1);
                }
                count=count.add(BigDecimal.valueOf(1));
                //単語一覧作成
                if(!wordList.contains(word)) wordList.add(word);
            }
            sentenceCount.put(no.longValue(),count);
            tfWordCount.put(no.longValue(),tfWordCountItem);
        }
        Map<String,Long> sentenceWordlist=new HashMap<>();
        for(Integer no:input.getSentenceList().keySet()){
            for(String searchWord:wordList) {
                if (input.getSentenceList().get(no).contains(searchWord)) {
                    if(!sentenceWordlist.containsKey(searchWord)){
                        sentenceWordlist.put(searchWord,1L);
                    }else{
                        sentenceWordlist.put(searchWord,sentenceWordlist.get(searchWord)+1);
                    }
                }
            }
        }
        //TFIDF値計算
        for(Long no:tfWordCount.keySet()) {
            for (String word : tfWordCount.get(no).keySet()) {

                result.addValue(no,word,(
                        BigDecimal.valueOf(tfWordCount.get(no).get(word))
                                .divide(sentenceCount.get(no.longValue()),10, BigDecimal.ROUND_HALF_UP)
                ).multiply(
                        BigDecimal.valueOf(Math.log(
                                BigDecimal.valueOf(input.getSentenceList().size())
                                        .divide(BigDecimal.valueOf(sentenceWordlist.get(word)),10, BigDecimal.ROUND_HALF_UP).doubleValue()
                        )).add(input.getWeight())));
            }

        }
        result.sort();
        return result;
    }

}
