package com.menudev.api.util;

public class ResponseDetail {
	
	private final int code;
	private final String description;

	public ResponseDetail(int i, String description) {
		super();
		this.code = i;
		this.description = description;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
