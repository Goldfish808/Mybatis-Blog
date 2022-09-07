package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingDto {
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

}
