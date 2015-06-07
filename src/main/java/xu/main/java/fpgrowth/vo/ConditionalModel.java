package xu.main.java.fpgrowth.vo;

import java.util.List;

/**
 * 条件模式基
 * 
 * @author xu
 * 
 */
public class ConditionalModel {

	private String key;

	private List<String> valueList;

	private int valueFreq;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getValueList() {
		return valueList;
	}

	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	public int getValueFreq() {
		return valueFreq;
	}

	public void setValueFreq(int valueFreq) {
		this.valueFreq = valueFreq;
	}

	@Override
	public String toString() {
		return "ConditionalModel [key=" + key + ", valueList=" + valueList + ", valueFreq=" + valueFreq + "]";
	}
}
