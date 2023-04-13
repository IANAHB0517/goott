package com.springproj.domain;

public class PagingInfo {
	private int totalPostCnt; // 전체 글의 개수
	private int viewPostCntPerPage; // 1페이지 당 보여줄 글의 개수
	private int pageNo; // 현재 페이지
	private int totalPageCnt; // 총 페이지 수
	private int startRowIndex; // x번 페이지에서 보여주기 시작할 row의 인덱스 번호

	private int pageCntPerBlock = 5; // 1개의 블럭에 몇개 페이지를 둘 것인가
	private int PageBlockOfCurrentPage; // 현재 페이지가 속한 페이징 블럭
	private int startNumOfCurrentPagingBlock; // 현재 페이징 블럭의 시작 번호
	private int endNumOfCurrentPagingBlock; // 현재 페이징 블럭의 끝 번호

	// -------------------------------------------페이징
	// 처리-----------------------------------------------------------------

	public int getViewPostCntPerPage() {
		return this.viewPostCntPerPage;
	}

	public void setViewPostCntPerPage(int viewPostCntPerPage) {
		this.viewPostCntPerPage = viewPostCntPerPage;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt(int totalPostCnt, int viewPostCntPerPage) { // 총 페이지의 수 : 게시판 수 / 하나의 페이지에 보여줄 글의 수 (나누어
																			// 떨어지지 않으면 + 1)

		if ((totalPostCnt % viewPostCntPerPage) == 0) {
			this.totalPageCnt = totalPostCnt / viewPostCntPerPage;

		} else {
			this.totalPageCnt = (totalPostCnt / viewPostCntPerPage) + 1;
		}

	}

	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}

	public int getStartRowIndex() {
		return startRowIndex;
	}

	public void setStartRowIndex(int pageNo) {
		// 보여주기 시작할 row index 번호 = (현재 페이지 번호-1) * 한페이지당 보여줄 글의 수
		this.startRowIndex = this.viewPostCntPerPage * (pageNo - 1);
	}

	public int getTotalPostCnt() {
		return totalPostCnt;
	}

	public void setTotalPostCnt(int totalPostCnt) {
		this.totalPostCnt = totalPostCnt;
		// 객체에 수를 넘겨주는 것
	}

	// -------------------------------------------페이징
	// 블럭처리-----------------------------------------------------------------

	public int getPageCntPerBlock() { // 1개의 블럭에 몇개 페이지를 둘 것인가
		return this.pageCntPerBlock;
	}

	public void setPageCntPerBlock(int pageCntPerBlock) {
		// 1개의 블럭에 몇개 페이지를 둘 것인가
		this.pageCntPerBlock = pageCntPerBlock;
	}

	public int getPageBlockOfCurrentPage() { // 현재 페이지가 속한 페이징 블럭
		return PageBlockOfCurrentPage;
	}

	public void setPageBlockOfCurrentPage(int pageNo) {
		// 현재 페이지가 속한 페이징 블럭 : 현재 페이지번호 / pageCntPerBlock
		if (pageNo % this.pageCntPerBlock == 0) {
			this.PageBlockOfCurrentPage = pageNo / this.pageCntPerBlock;
		} else {
			this.PageBlockOfCurrentPage = (int) (Math.ceil((double) pageNo / this.pageCntPerBlock)); // ceil-> 올림
		}
	}

	public int getStartNumOfCurrentPagingBlock() { // 현재 페이징 블럭의 시작 번호
		return startNumOfCurrentPagingBlock;
	}

	public void setStartNumOfCurrentPagingBlock(int PageBlockOfCurrentPage) {
		// 현재 페이징 블럭의 시작 번호 : (현재 페이징 블럭 -1) * pageCntPerBlock + 1
		this.startNumOfCurrentPagingBlock = ((PageBlockOfCurrentPage - 1) * this.pageCntPerBlock) + 1;
	}

	public int getEndNumOfCurrentPagingBlock() { // 현재 페이징 블럭의 끝 번호
		return endNumOfCurrentPagingBlock;
	}

	public void setEndNumOfCurrentPagingBlock(int StartNumOfCurrentPagingBlock) {
		// 현재 페이징 블럭의 끝 번호 : (현재 페이징 시작 번호 + pageCntPerBlock) - 1
		this.endNumOfCurrentPagingBlock = (StartNumOfCurrentPagingBlock + this.pageCntPerBlock) - 1;

		if (this.endNumOfCurrentPagingBlock > this.totalPageCnt) {
			this.endNumOfCurrentPagingBlock = this.totalPageCnt;
		}
	}

	@Override
	public String toString() {
		return "PagingInfo [totalPostCnt=" + totalPostCnt + ", viewPostCntPerPage=" + viewPostCntPerPage + ", pageNo="
				+ pageNo + ", totalPageCnt=" + totalPageCnt + ", startRowIndex=" + startRowIndex + ", pageCntPerBlock="
				+ pageCntPerBlock + ", PageBlockOfCurrentPage=" + PageBlockOfCurrentPage
				+ ", startNumOfCurrentPagingBlock=" + startNumOfCurrentPagingBlock + ", endNumOfCurrentPagingBlock="
				+ endNumOfCurrentPagingBlock + "]";
	}

}
