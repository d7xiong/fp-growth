package xu.main.java.fpgrowth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 根据频繁项集重新排列输入数据
 * Created by xu on 15-6-5.
 */
public class DataResort {

    public DataResort(List<List<String>> dataList, Map<String, Integer> wordsFreqMap, int minSup) {
        this.dataList = dataList;
        this.wordsFreqMap = wordsFreqMap;
        this.minSup = minSup;
    }



    public void run(){

    }

    /**
     * 插入排序方式进行输入词重排
     */
    public void wordsResort(){
        for(List<String> rowDataList : dataList){

            String[] words = new String[this.maxRowDataLenth];
            int[] wordsFreqs = new int[this.maxRowDataLenth];
            int wordsIndex = 0;

            // 移除小于最小支持度的词条
            for(String item : rowDataList){
                Integer itemFreq = this.wordsFreqMap.get(item);
                if(null != itemFreq && itemFreq > this.minSup){
                    words[wordsIndex] = item;
                    wordsFreqs[wordsIndex] = itemFreq;
                    wordsIndex++;
                }
            }

            // 对新词条排序
            sortWords(words,wordsFreqs,wordsIndex);

            List<String> currentRow = buildSortedWordList(words, wordsIndex);

            // 替换原有词条数据
            rowDataList.clear();
            rowDataList.addAll(currentRow);

        }
    }

    private List<String> buildSortedWordList(String[] words, int wordsLength){
        List<String> currentRow = new ArrayList<>();
        for(int wordsIndex=0; wordsIndex < wordsLength; wordsIndex++){
            currentRow.add(words[wordsIndex]);
        }
        return currentRow;
    }

    private void sortWords(String[] words, int[] wordsFreq, int wordsNum){
//        printArray(wordsFreq);
        int insertNum;
        String insertStr;
        int[] array = wordsFreq;
        for(int index =1 ; index < wordsNum ; index++) {
            int currentIndex = index;
            insertNum = array[index];
            insertStr = words[index];
            while (currentIndex > 0 && array[currentIndex - 1] < insertNum) {
                array[currentIndex] = array[currentIndex - 1];
                words[currentIndex] = words[currentIndex - 1];
                currentIndex--;
            }
            array[currentIndex] = insertNum;
            words[currentIndex] = insertStr;

//            printArray(wordsFreq);
        }
    }

    public void printArray(int[] array){
        for(int item : array){
            System.out.print(item);
        }
        System.out.println();
    }

    /* 最小支持度 */
    private int minSup;

    private List<List<String>> dataList;

    private Map<String,Integer> wordsFreqMap;

    private int maxRowDataLenth = 20;
}
