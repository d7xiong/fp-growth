package xu.main.java;

import java.util.List;
import java.util.Map;

import xu.main.java.fpgrowth.CountWords;
import xu.main.java.fpgrowth.DataResort;
import xu.main.java.fpgrowth.FPGrowth;
import xu.main.java.fpgrowth.TreeNode;

/**
 * Created by xu on 15-6-5.
 */
public class FPGrowthStart {

	public static void main(String args[]) {
		CountWords countWords = new CountWords();
		countWords.initData();

		// 临时数据
		List<List<String>> dataList = countWords.getDataList();
		Map<String, Integer> wordsFreqMap = countWords.getWordsFreqsMap();

		DataResort dataResort = new DataResort(dataList, wordsFreqMap, MIN_SUP);
		dataResort.wordsResort();

		countWords.printDataList();

		FPGrowth fpGrowth = new FPGrowth(MIN_SUP, countWords.getWords(), countWords.getWordsFreqs());
		for (List<String> rowDataList : dataList) {
			String[] dataArray = new String[rowDataList.size()];
			for (int rowIndex = 0, len = rowDataList.size(); rowIndex < len; rowIndex++) {
				dataArray[rowIndex] = rowDataList.get(rowIndex);
			}
			fpGrowth.addData(dataArray);
		}

		fpGrowth.printFpTree();
	}

	private static final int MIN_SUP = 2;

}
