package com.staff.common.response;

import com.staff.common.dto.GetStaffDTO;
import lombok.Data;

@Data
public class GetStaffDetailResponse extends BaseResponse<GetStaffDetailResponse> {

    private GetStaffDTO getStaffDTO;
}
