package com.staff.common.request;
import com.staff.common.pojo.StaffTable;
import lombok.Data;

@Data
public class RegisteRequest extends StaffTable{

    private Long work;
}
