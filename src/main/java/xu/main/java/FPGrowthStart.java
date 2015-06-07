package xu.main.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import xu.main.java.fpgrowth.CountWords;
import xu.main.java.fpgrowth.DataResort;
import xu.main.java.fpgrowth.FPGrowth;
import xu.main.java.fpgrowth.vo.ConditionalModel;

/**
 * 
 * 条件模式基 条件FP树 产生频繁模式 Created by xu on 15-6-5.
 */
public class FPGrowthStart {

	public static void main(String[] args) {

		// 创建数据统计工具类,通过构造方法传入数据或无参创建默认数据集后续改为add方式
		CountWords countWords = new CountWords();
		countWords.initData();

		// 获取测试数据
		List<List<String>> dataList = countWords.getDataList();
		Map<String, Integer> wordsFreqMap = countWords.getWordsFreqsMap();

		// 数据集按出现频次重新排列
		DataResort dataResort = new DataResort(dataList, wordsFreqMap, MIN_SUP);
		dataResort.wordsResort();

		countWords.printDataList();

		// FPGrowth工具类 构建FP-Tree、频繁模式挖掘等
		FPGrowth fpGrowth = new FPGrowth(MIN_SUP, countWords.getWords(), countWords.getWordsFreqs());
		fpGrowth.addData(dataList);

		// fpGrowth.printFpTree();

		// 构建频繁模式基
		fpGrowth.buildConditionalModel();
		// fpGrowth.printConditionalModel();

		Map<String, List<ConditionalModel>> conModelMap = fpGrowth.getConditionalModelMap();
		Iterator<Entry<String, List<ConditionalModel>>> modelIt = conModelMap.entrySet().iterator();
		System.out.println(conModelMap.size());
		while (modelIt.hasNext()) {
			FPGrowth fpG = new FPGrowth(MIN_SUP, countWords.getWords(), countWords.getWordsFreqs());
			Entry<String, List<ConditionalModel>> entry = modelIt.next();
			for (ConditionalModel conditionalModel : entry.getValue()) {
				int valueFreq = conditionalModel.getValueFreq();
				for (int endIndex = 0; endIndex < valueFreq; endIndex++) {
					List<String> valueList = conditionalModel.getValueList();
					valueList.add(entry.getKey());
					for (String value : valueList) {
						System.out.print(value + "\t");
					}
					System.out.println();
					List<List<String>> fpD = new ArrayList<>();
					fpD.add(valueList);
					fpG.addData(fpD);
				}
			}
			fpG.printFpTree();
			// fpG.buildConditionalModel();
			// fpG.printConditionalModel();
		}
	}

	/* 最小置信度 */
	private static final int MIN_SUP = 2;

}
