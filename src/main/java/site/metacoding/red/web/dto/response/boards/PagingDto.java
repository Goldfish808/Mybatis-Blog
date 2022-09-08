package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingDto {
	private String keyword;
	private Integer blockCount; // 상수 한페이지에 페이지 넘수 개수(5) 1-5, 6-10
	private Integer currentBlock; // 변수
	private Integer startPageNum; // 1 -> 6 -> 11
	private Integer lastPageNum; // 5-> 10 -> 15

	private Integer totalCount;
	private Integer totalPage;
	private Integer currentPage;

	// getter가 만들어지면 isLast/isFirst() 로 만들어짐 -> el 표현식에서는 last/first로 찾아짐
	private boolean isFirst;
	private boolean isLast;
	
	public void blockPoint(Integer page) {
		final int blockCount = 5;

		int currentBlock = page / blockCount;
		int startPageNum = 1 + blockCount * currentBlock; // 1 + 5 * 0 = 1 첫 Current 시작 : 1 / 6 / 11 / 16
		int lastPageNum = 5 + blockCount * currentBlock; // 5 + 5 * 0 = 5 첫 Currnet 끝 : 5 / 10 / 15 / 20
		//paging.setLastPageNum(paging.getBlockCount());
		this.lastPageNum = blockCount;
		if (this.totalPage < lastPageNum) {
			lastPageNum = this.totalPage;
		}
		this.blockCount = blockCount;
		this.currentBlock = currentBlock;
		this.startPageNum = startPageNum;
		this.lastPageNum = lastPageNum;
	}
}
