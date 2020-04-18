package com.staff.common.response;

import com.staff.common.pojo.WorkTable;
import lombok.Data;
import java.util.List;

@Data
public class GetWorkResponse extends BaseResponse<GetWorkResponse> {
    private List<WorkTable> workTableList;
}
