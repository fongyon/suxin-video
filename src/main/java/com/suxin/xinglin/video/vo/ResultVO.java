package com.suxin.xinglin.video.vo;

import java.util.List;

/**
 * 统一返回前端的结果
 */
public class ResultVO<T> {
	//统一编号
	/**
	 * 编号为0，代表成功
	 * 编号其他，代表失败
	 */
	private int errorCode;
	/**
	 * 返回前端的消息：既有成功，也失败
	 */
	private String message;
	
	//构造器私有化
	private ResultVO() {
		
	}
	/**
	 * 成功
	 */
	public static <T>  ResultVO<T>  success() {
		ResultVO<T> resultVO= new ResultVO<>();
		resultVO.setErrorCode(0);
		resultVO.setMessage("成功");
		return resultVO;
	}
	/**
	 * 失败
	 * @param <T>
	 * @param errorCode
	 * @return
	 */
	public static <T>  ResultVO<T>  failed(int errorCode ,String message) {
		ResultVO<T> resultVO= new ResultVO<>();
		resultVO.setErrorCode(errorCode);
		resultVO.setMessage(message);
		return resultVO;
	}
	/**
	 * 失败
	 * @param <T>
	 * @param errorCode
	 * @return
	 */
	public static <T>  ResultVO<T>  failed(int errorCode) {
		ResultVO<T> resultVO= new ResultVO<>();
		resultVO.setErrorCode(errorCode);
		return resultVO;
	}
	
	
	/**
	 * 返回相关对象：实体VO
	 */
	private T data;
	
	/**
	 * 总的记录数量
	 */
	private long total;
	
	/**
	 * 查询记录
	 */
	private List<T> rows;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	
	
}
