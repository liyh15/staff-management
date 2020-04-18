package com.staff.common.request;

import lombok.Data;

@Data
public class EditWorkRequest {

    private String workId;

    private String workName;

    private String commont;
}
