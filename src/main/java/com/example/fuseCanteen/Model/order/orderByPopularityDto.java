package com.example.fuseCanteen.Model.order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by aishwarya on 9/5/20.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class orderByPopularityDto {

    private String foodName;
    private Integer totalQty;

}
