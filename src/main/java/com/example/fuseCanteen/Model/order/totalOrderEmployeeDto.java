package com.example.fuseCanteen.Model.order;

import com.example.fuseCanteen.common.LocalDateTimeSerializerCs;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by aishwarya on 9/5/20.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class totalOrderEmployeeDto {

    private String userName;
    private String employeeNumber;
    private BigDecimal totalAmt;

    @JsonSerialize(using = LocalDateTimeSerializerCs.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderTime;

    private String paidStatus;
}
