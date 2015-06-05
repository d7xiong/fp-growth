package xu.main.java.fpgrowth;

import java.util.ArrayList;
import java.util.List;

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
		if(null == rootHomonymNode){
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

	private final String FP_TREE_ROOT_NODE_NAME = "ROOT_NODE";

	private int minSup;

	private String[] words;

	private int[] wordsFreqs;

	private TreeNode[] wordsTreeNode;

	private TreeNode rootNode;

}
