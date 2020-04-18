package com.staff.common.response;
import com.staff.common.dto.GetStaffDTO;
import lombok.Data;
import java.util.List;


@Data
public class GetStaffListResponse extends BaseResponse<GetStaffListResponse> {

    private Integer pageTotal;

    private List<GetStaffDTO> staffTableList;
}
