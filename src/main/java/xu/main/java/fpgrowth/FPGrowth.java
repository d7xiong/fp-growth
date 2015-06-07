package xu.main.java.fpgrowth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import xu.main.java.fpgrowth.vo.ConditionalModel;
import xu.main.java.fpgrowth.vo.TreeNode;

public class FPGrowth {

	public FPGrowth(int minSup, String[] words, int[] wordsFreqs) {
		this.minSup = minSup;
		this.words = words;
		this.wordsFreqs = wordsFreqs;
		initWordsTreeNode(wordsFreqs.length);
		rootNode = createRootNode();
	}

	public void addData(String[] dataArray) {

		TreeNode currentNode = this.rootNode;
		for (String data : dataArray) {
			currentNode = addDataToFpTree(data, currentNode);
		}
	}

	public void addData(List<List<String>> dataList) {
		for (List<String> rowDataList : dataList) {
			String[] dataArray = new String[rowDataList.size()];
			for (int rowIndex = 0, len = rowDataList.size(); rowIndex < len; rowIndex++) {
				dataArray[rowIndex] = rowDataList.get(rowIndex);
			}
			this.addData(dataArray);
		}
	}

	public void buildConditionalModel() {
		this.conditionalModelMap = new HashMap<>();
		for (int wordsIndex = words.length - 1; wordsIndex >= 0; wordsIndex--) {
			// 过滤小于最小置信度的数据
			int wordsFreq = wordsFreqs[wordsIndex];
			if (minSup >= wordsFreq) {
				continue;
			}
			String key = words[wordsIndex];
			TreeNode keyNode = wordsTreeNode[wordsIndex];
			buildKeyConditionalModel(key, keyNode);
		}
	}

	private void buildKeyConditionalModel(String key, TreeNode dataFirstTreeNode) {
		TreeNode currentNode = dataFirstTreeNode;
		List<ConditionalModel> modelList = new ArrayList<>();
		while (null != currentNode) {
			List<String> valueList = new ArrayList<>();
			findConditionalValue(currentNode, valueList);
			if (0 == valueList.size()) {
				currentNode = currentNode.getNextHomonym();
				continue;
			}
			ConditionalModel conditionalModel = new ConditionalModel();
			conditionalModel.setKey(key);
			conditionalModel.setValueList(valueList);
			conditionalModel.setValueFreq(currentNode.getCount());
			modelList.add(conditionalModel);
			currentNode = currentNode.getNextHomonym();
		}
		this.conditionalModelMap.put(key, modelList);
	}

	private void findConditionalValue(TreeNode treeNode, List<String> valueList) {
		TreeNode parentNode = treeNode.getParentNode();
		if (FP_TREE_ROOT_NODE_NAME.equals(parentNode.getNodeName())) {
			return;
		}
		findConditionalValue(parentNode, valueList);
		valueList.add(parentNode.getNodeName());
	}

	private TreeNode addDataToFpTree(String data, TreeNode currentNode) {
		if (currentNode.isChildrenNull()) {
			List<TreeNode> children = new ArrayList<>();
			currentNode.setChildren(children);
		}
		TreeNode childNode = currentNode.findChildByName(data);
		if (null != childNode) {
			childNode.countPlusOne();
			return childNode;
		}

		childNode = createNode(data, currentNode);
		childNode.countPlusOne();
		currentNode.getChildren().add(childNode);
		int wordsTreeNodeIndex = findWordsTreeNodeIndexByNodeName(data);
		TreeNode rootHomonymNode = wordsTreeNode[wordsTreeNodeIndex];
		if (null == rootHomonymNode) {
			wordsTreeNode[wordsTreeNodeIndex] = childNode;
			return childNode;
		}
		TreeNode parentHomonymNode = findParentHomonymNode(data, rootHomonymNode);
		parentHomonymNode.setNextHomonym(childNode);
		return childNode;
	}

	private int findWordsTreeNodeIndexByNodeName(String nodeName) {
		int result = -1;
		for (int wordsIndex = 0, len = words.length; wordsIndex < len; wordsIndex++) {
			if (words[wordsIndex].equals(nodeName)) {
				result = wordsIndex;
				break;
			}
		}
		return result;
	}

	private TreeNode findParentHomonymNode(String nodeName, TreeNode rootHomonymNode) {

		if (null == rootHomonymNode) {
			return rootHomonymNode;
		}
		TreeNode currentNode = rootHomonymNode;
		while (true) {
			TreeNode nextNode = currentNode.getNextHomonym();
			if (null == nextNode) {
				return currentNode;
			}
			currentNode = nextNode;
		}
	}

	private TreeNode createNode(String nodeName, TreeNode parentNode) {
		TreeNode treeNode = new TreeNode();
		treeNode.setNodeName(nodeName);
		treeNode.setParentNode(parentNode);
		return treeNode;
	}

	private TreeNode createRootNode() {
		TreeNode rootNode = new TreeNode();
		rootNode.setNodeName(FP_TREE_ROOT_NODE_NAME);
		return rootNode;
	}

	private void initWordsTreeNode(int wordsTreeNodeLength) {
		this.wordsTreeNode = new TreeNode[wordsTreeNodeLength];
	}

	public void printConditionalModel() {
		System.out.println("========== ConditionalModel start ==========");
		Iterator<Entry<String, List<ConditionalModel>>> it = conditionalModelMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, List<ConditionalModel>> entry = it.next();
			for (ConditionalModel conditionalModel : entry.getValue()) {
				System.out.println(conditionalModel);
			}
		}

		System.out.println("========== ConditionalModel  end  ==========");
	}

	public void printFpTree() {
		System.out.println("========== FP-Tree start ==========");
		printNodes(this.rootNode, 1);
		System.out.println("========== FP-Tree  end  ==========");
	}

	private void printNodes(TreeNode currentNode, int deep) {
		for (int deepIndex = 0; deepIndex < deep; deepIndex++) {
			System.out.print("-----");
		}
		System.out.println(currentNode.getNodeName() + " " + currentNode.getCount() + " " + currentNode.getNextHomonym());
		if (currentNode.isChildrenNull()) {
			return;
		}
		List<TreeNode> children = currentNode.getChildren();
		for (TreeNode childNode : children) {
			printNodes(childNode, deep + 1);
		}
	}

	public TreeNode getRootNode() {
		return rootNode;
	}

	public Map<String, List<ConditionalModel>> getConditionalModelMap() {
		return conditionalModelMap;
	}

	private final String FP_TREE_ROOT_NODE_NAME = "ROOT_NODE";

	private int minSup;

	private String[] words;

	private int[] wordsFreqs;

	private TreeNode[] wordsTreeNode;

	private TreeNode rootNode;

	/* 条件模式基 */
	private Map<String, List<ConditionalModel>> conditionalModelMap;

}
