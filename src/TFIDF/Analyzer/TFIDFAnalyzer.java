package TFIDF.Analyzer;

import TFIDF.AnalysisResult.TFIDFResult;
import TFIDF.AnalysysInput.TFIDFInput;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TFIDFAnalyzer {

    public TFIDFResult analyze(TFIDFInput input){
        TFIDFResult result=new TFIDFResult();
        Map<String,Long> wordlist=new HashMap<>();
        //TF値の計算
        long count=0L;
        //単語一覧の作成
        for(Integer no:input.getSentenceList().keySet()){
            for(String word:input.getSentenceList().get(no)){
                if(!wordlist.containsKey(word)){
                    wordlist.put(word,1L);
                }else{
                    wordlist.put(word,wordlist.get(word)+1);
                }
                count++;
            }
        }
        //IDF値の計算
        Map<String,Long> sentenceWordlist=new HashMap<>();
        for(Integer no:input.getSentenceList().keySet()){
            for(String searchWord:wordlist.keySet()) {
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
        for(String word:wordlist.keySet()){
            result.getTfidfValue().put(word, (
                    BigDecimal.valueOf(wordlist.get(word))
                            .divide(BigDecimal.valueOf(count))
            ).multiply(
                    BigDecimal.valueOf(Math.log(
                    BigDecimal.valueOf(input.getSentenceList().size())
                            .divide(BigDecimal.valueOf(sentenceWordlist.get(word))).doubleValue()
            )+1)));
        }
        return result;
    }

}
