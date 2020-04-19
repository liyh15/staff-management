package com.staff.common.response;

import com.staff.common.pojo.SalaryTable;
import lombok.Data;

import java.util.List;

@Data
public class GetSalaryListResponse extends BaseResponse<GetSalaryListResponse> {

    private Integer pageTotal;

    private List<SalaryTable> salaryTableList;
}
