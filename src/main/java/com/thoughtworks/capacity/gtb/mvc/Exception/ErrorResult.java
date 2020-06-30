package com.thoughtworks.capacity.gtb.mvc.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {

    private Integer code;
    private String message;

}
