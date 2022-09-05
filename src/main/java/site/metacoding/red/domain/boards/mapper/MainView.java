package site.metacoding.red.domain.boards.mapper;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.users.Users;

@Getter
@Setter
public class MainView {
	private Integer id;
	private String title;
	private String username;
}
