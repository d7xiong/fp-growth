package xu.main.java.fpgrowth.vo;

import java.util.List;

public class TreeNode implements Comparable<TreeNode> {

	private String nodeName;

	private int count;

	private TreeNode parentNode;

	private List<TreeNode> children;

	/* 下一个同名节点 */
	private TreeNode nextHomonym;

	public TreeNode findChildByName(String nodeName) {
		List<TreeNode> children = this.getChildren();
		if (null == children || null == nodeName) {
			return null;
		}
		for (TreeNode child : children) {
			if (child.getNodeName().equals(nodeName)) {
				return child;
			}
		}
		return null;
	}

	public boolean isChildrenNull() {
		return null == children;
	}

	public void countPlusOne() {
		this.count++;
	}

	public void printChildrenName() {
		List<TreeNode> children = this.getChildren();
		if (null == children) {
			System.out.println("null");
			return;
		}
		for (TreeNode child : children) {
			System.out.println(child.getNodeName());
		}
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public TreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public TreeNode getNextHomonym() {
		return nextHomonym;
	}

	public void setNextHomonym(TreeNode nextHomonym) {
		this.nextHomonym = nextHomonym;
	}

	@Override
	public int compareTo(TreeNode treeNode) {
		int count0 = treeNode.getCount();
		// 跟默认的比较大小相反，导致调用Arrays.sort()时是按降序排列
		return count0 - this.count;
	}
}
