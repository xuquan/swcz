package com.jm.swcz.model;

import java.util.ArrayList;
import java.util.List;

public class TreeElement {
	private String id;
	private String outlineTitle;
	private boolean mhasParent;
	private boolean mhasChild;
	private TreeElement parent;
	private int level;
	private boolean expanded;
	private List<TreeElement> childList = new ArrayList<TreeElement>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOutlineTitle() {
		return outlineTitle;
	}

	public void setOutlineTitle(String outlineTitle) {
		this.outlineTitle = outlineTitle;
	}

	public boolean isMhasParent() {
		return mhasParent;
	}

	public void setMhasParent(boolean mhasParent) {
		this.mhasParent = mhasParent;
	}

	public boolean isMhasChild() {
		return mhasChild;
	}

	public void setMhasChild(boolean mhasChild) {
		this.mhasChild = mhasChild;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<TreeElement> getChildList() {
		return childList;
	}

	public TreeElement getParent() {
		return parent;
	}

	public void setParent(TreeElement parent) {
		this.parent = parent;
	}

	public void addChild(TreeElement c) {
		this.childList.add(c);
		this.mhasParent = false;
		this.mhasChild = true;
		c.parent = this;
		c.level = this.level + 1;

	}

	public TreeElement(String id, String title) {
		super();
		this.id = id;
		this.outlineTitle = title;
		this.level = 0;
		this.mhasParent = true;
		this.mhasChild = false;
		this.parent = null;
	}

	public TreeElement(String id, String outlineTitle, boolean mhasParent,
			boolean mhasChild, TreeElement parent, int level, boolean expanded) {
		super();
		this.id = id;
		this.outlineTitle = outlineTitle;
		this.mhasParent = mhasParent;
		this.mhasChild = mhasChild;
		this.parent = parent;
		if (parent != null) {
			this.parent.getChildList().add(this);
		}
		this.level = level;
		this.expanded = expanded;
	}

}