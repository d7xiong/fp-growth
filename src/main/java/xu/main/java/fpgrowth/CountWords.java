package xu.main.java.fpgrowth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 统计数据词频 为FPGrowhth计算未处理的频繁项集 Created by xu on 15-6-5.
 */
public class CountWords {

	public static void main(String args[]) {
		CountWords fpGrowth = new CountWords();
		fpGrowth.initData();
		// fpGrowth.printResult();

	}

	public CountWords() {
		this.dataList = createDefaultData();
	}

	public CountWords(List<List<String>> dataList) {
		this.dataList = dataList;
	}

	public void initData() {
		this.wordsFreqsMap = countWordFreq();
		setWords(wordsFreqsMap);
		sortWords();
	}

	public void printWords() {
		System.out.println("========== words start ==========");
		for (String word : words) {
			System.out.println(word);
		}
		System.out.println("========== words  end  ==========");
	}

	public void printWordsFreqs() {
		System.out.println("========== wordsFreqs start ==========");
		for (int freq : wordsFreqs) {
			System.out.println(freq);
		}
		System.out.println("========== wordsFreqa  end  ==========");
	}

	public void printWordsAndFreqs() {
		System.out.println("========== wordsAndFreqs start ==========");
		for (int wordsIndex = 0, len = words.length; wordsIndex < len; wordsIndex++) {
			System.out.print(words[wordsIndex]);
			System.out.print("\t");
			System.out.println(wordsFreqs[wordsIndex]);
		}
		System.out.println("========== wordsAndFreqs  end  ==========");
	}

	public void printDataList() {
		System.out.println("========== dataList start ==========");
		for (List<String> rowDataList : dataList) {
			for (String item : rowDataList) {
				System.out.print(item);
				System.out.print("\t");
			}
			System.out.println();
		}
		System.out.println("========== dataList  end ==========");
	}

	/**
	 * 统计出现频率
	 */
	private Map<String, Integer> countWordFreq() {

		Map<String, Integer> wordsMap = new HashMap<>();
		for (List<String> rowDataList : this.dataList) {
			for (String item : rowDataList) {

				Integer wordNum = wordsMap.get(item);
				wordsMap.put(item, null == wordNum ? 1 : wordNum + 1);

			}
		}
		return wordsMap;
	}

	/**
	 * 初始化类内属性 wordsNum words wordsFreqs wordsSet
	 * 
	 * @param wordsFreqMap
	 */
	private void setWords(Map<String, Integer> wordsFreqMap) {
		int wordsNum = wordsFreqMap.size();
		this.wordsNum = wordsNum;
		this.words = new String[wordsNum];
		this.wordsFreqs = new int[wordsNum];
		this.wordsSet = new HashSet<>();

		int wordIndex = 0;
		Set<Map.Entry<String, Integer>> wordsFreqSet = wordsFreqMap.entrySet();
		for (Iterator<Map.Entry<String, Integer>> it = wordsFreqSet.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			this.words[wordIndex] = entry.getKey();
			this.wordsFreqs[wordIndex] = entry.getValue();
			this.wordsSet.add(entry.getKey());
			wordIndex++;
		}
	}

	/**
	 * 出现词按词频排序 排序方式: 插入排序
	 */
	private void sortWords() {
		int insertNum;
		String insertStr;
		int[] array = this.wordsFreqs;
		String[] words = this.words;
		for (int index = 1; index < array.length; index++) {
			int currentIndex = index;
			insertNum = array[index];
			insertStr = this.words[index];
			while (currentIndex > 0 && array[currentIndex - 1] < insertNum) {
				array[currentIndex] = array[currentIndex - 1];
				words[currentIndex] = words[currentIndex - 1];
				currentIndex--;
			}
			array[currentIndex] = insertNum;
			words[currentIndex] = insertStr;
		}
	}

	public void insertSort(int[] array) {
		// printArray(array);
		int insertNum;
		for (int index = 1; index < array.length; index++) {
			int currentIndex = index;
			insertNum = array[index];
			for (; currentIndex > 0 && array[currentIndex - 1] < insertNum; currentIndex--) {
				array[currentIndex] = array[currentIndex - 1];
			}
			// while(currentIndex > 0 && array[currentIndex - 1] < insertNum){
			// array[currentIndex] = array[currentIndex - 1];
			// currentIndex--;
			// }
			array[currentIndex] = insertNum;

			// printArray(array);
		}
	}

	public void printArray(int[] array) {
		for (int item : array) {
			System.out.print(item);
		}
		System.out.println();
	}

	private List<List<String>> createDefaultData() {

		List<List<String>> dataList = new ArrayList<>(10);

		List<String> data1 = new ArrayList<>();
		data1.addAll(Arrays.asList("牛奶", "鸡蛋", "面包", "薯片"));

		List<String> data2 = new ArrayList<>();
		data2.addAll(Arrays.asList("鸡蛋", "爆米花", "薯片", "啤酒"));

		List<String> data3 = new ArrayList<>();
		data3.addAll(Arrays.asList("鸡蛋", "面包", "薯片"));

		List<String> data4 = new ArrayList<>();
		data4.addAll(Arrays.asList("牛奶", "鸡蛋", "面包", "爆米花", "薯片", "啤酒"));

		List<String> data5 = new ArrayList<>();
		data5.addAll(Arrays.asList("牛奶", "面包", "啤酒"));

		List<String> data6 = new ArrayList<>();
		data6.addAll(Arrays.asList("鸡蛋", "面包", "啤酒"));

		List<String> data7 = new ArrayList<>();
		data7.addAll(Arrays.asList("牛奶", "面包", "鸡蛋"));

		List<String> data8 = new ArrayList<>();
		data8.addAll(Arrays.asList("牛奶", "鸡蛋", "面包", "黄油", "薯片"));

		List<String> data9 = new ArrayList<>();
		data9.addAll(Arrays.asList("啤酒", "鸡蛋", "黄油", "薯片"));

		dataList.add(data1);
		dataList.add(data2);
		dataList.add(data3);
		dataList.add(data4);
		dataList.add(data5);
		dataList.add(data6);
		dataList.add(data7);
		dataList.add(data8);
		dataList.add(data9);

		return dataList;
	}

	public List<List<String>> getDataList() {
		return dataList;
	}

	public int getWordsNum() {
		return wordsNum;
	}

	public Set<String> getWordsSet() {
		return wordsSet;
	}

	public String[] getWords() {
		return words;
	}

	public int[] getWordsFreqs() {
		return wordsFreqs;
	}

	public Map<String, Integer> getWordsFreqsMap() {
		return wordsFreqsMap;
	}

	/* 输入数据 */
	private List<List<String>> dataList;

	private Map<String, Integer> wordsFreqsMap;

	/* 词语个数 */
	private int wordsNum;

	/* 出现的词语 */
	private Set<String> wordsSet;

	/* 排序后的词语 */
	private String[] words;

	/* 排序后词语对应的词频 */
	private int[] wordsFreqs;

}
