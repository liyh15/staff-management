package com.staff.common.dto;

import com.staff.common.pojo.StaffTable;
import lombok.Data;

@Data
public class GetStaffDTO extends StaffTable {

    private String workName;
}
