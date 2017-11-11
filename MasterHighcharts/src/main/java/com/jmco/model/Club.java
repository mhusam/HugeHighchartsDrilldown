package com.jmco.model;

import com.jmco.chart.highchart.Highchart;
import static com.jmco.chart.highchart.HighchartHashMap.getMap;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Husam
 * @since  1.0.0
 * @datetime   Nov 10, 2017  5:03:37 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    
    private BigDecimal id;
    private String name;
    
    private BigDecimal status1;
    private BigDecimal status2;
    private BigDecimal status3;
    private BigDecimal status4;
    
    private List<ClubStatus2> status1Details;
    private List<ClubGroup> childs;
    
    public void addChildHighchart(String parentNameId, Map<String, List<String>> masterCriteria, Highchart highchart){
        String nameComma = "'" + name + "'";
        
        masterCriteria.get("Shooting").add(getMap(name, getStatus2().intValue()).toString());
        masterCriteria.get("Passing").add(getMap(name, getStatus1().intValue(), true).toString());
        masterCriteria.get("Scoring").add(getMap(name, getStatus3().intValue()).toString());
        masterCriteria.get("Trophies").add(getMap(name, getStatus4().intValue()).toString());
        
        // Add Status
        highchart.addStatus(nameComma, name + " _Status", ClubStatus2.getStatusData(getStatus1Details()));
        
        if(childs != null){
            Map<String, List<String>> childMasterCriteria = new HashMap<>();
            childMasterCriteria.put("Passing", new ArrayList<String>());
            childMasterCriteria.put("Shooting", new ArrayList<String>());
            childMasterCriteria.put("Scoring", new ArrayList<String>());
            childMasterCriteria.put("Trophies", new ArrayList<String>());
            
            for(ClubGroup club : childs){
                club.addChildHighchart(nameComma, childMasterCriteria, highchart);
            }
            
            // Add Master Child
            for(String criterias : childMasterCriteria.keySet()){
                List<String> value = childMasterCriteria.get(criterias);
                // remove the brackets '[' and ']' to make data as list of objects
//                    value = value.substring(0, 1);
//                    value = value.substring(0, value.length() - 2);
                highchart.addChilds(nameComma, criterias,     value);
            }
        }
    }
}
