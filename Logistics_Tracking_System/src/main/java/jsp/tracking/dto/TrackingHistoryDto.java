package jsp.tracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingHistoryDto {
	 private Integer id;
	 private String location;
	 private String remark;
	 private Status status;

}
