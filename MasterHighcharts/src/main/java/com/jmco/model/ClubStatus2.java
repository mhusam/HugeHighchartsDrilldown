package com.jmco.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Husam
 * @since  1.0.0
 * @datetime   Nov 10, 2017  5:07:56 AM
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubStatus2 {
    private String name;
    private BigDecimal count;
    
    public static List<List<String>> getStatusData(List<ClubStatus2> statuses) {
        List<List<String>> result = new ArrayList<>();
        for(final ClubStatus2 status : statuses){
            result.add(new ArrayList<String>(){{add(String.format("'%s', %s", status.name, status.count));}});
        }
        System.out.println(result);
        return result;
    }
}
