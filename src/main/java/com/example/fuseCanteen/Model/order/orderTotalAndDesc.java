package com.example.fuseCanteen.Model.order;


import java.util.List;

/**
 * Created by aishwarya on 9/5/20.
 */

public class orderTotalAndDesc {
    private List<totalOrderEmployeeDto> TOTAL_CALCULATION;
    private List<orderDescEmployeeDto> ORDER_DESCRIPTION;

    public orderTotalAndDesc(List<totalOrderEmployeeDto>  TOTAL_CALCULATION, List<orderDescEmployeeDto> ORDER_DESCRIPTION) {
        this.TOTAL_CALCULATION = TOTAL_CALCULATION;
        this.ORDER_DESCRIPTION = ORDER_DESCRIPTION;
    }


    public List<totalOrderEmployeeDto> getTOTAL_CALCULATION() {
        return TOTAL_CALCULATION;
    }

    public void setTOTAL_CALCULATION(List<totalOrderEmployeeDto> TOTAL_CALCULATION) {
        this.TOTAL_CALCULATION = TOTAL_CALCULATION;
    }

    public List<orderDescEmployeeDto> getORDER_DESCRIPTION() {
        return ORDER_DESCRIPTION;
    }

    public void setORDER_DESCRIPTION(List<orderDescEmployeeDto> ORDER_DESCRIPTION) {
        this.ORDER_DESCRIPTION = ORDER_DESCRIPTION;
    }
}
