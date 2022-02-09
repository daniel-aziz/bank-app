package com.jb.bankapp.Beans.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum SysMsg {
    SUCCESSFUL ("SUCCESSFUL"),
    FAILED("FAILED"),
    NOT_FOUND("NOT_FOUND");

    private final String errorMassage;
}
