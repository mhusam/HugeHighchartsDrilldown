package com.jmco.chart.highchart;

import static com.jmco.chart.highchart.HighchartHashMap.getMap;
import com.jmco.model.Club;
import com.jmco.model.ClubGroup;
import com.jmco.model.ClubStatus2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Husam
 * @since  1.0.0
 * @datetime   Nov 11, 2017  1:01:51 PM
 */
public class HighchartGenerator {

    public static Highchart generate(List<Club> clubs) {
        Highchart highchart = new Highchart();
        
        Map<String, List<Object>> masterCriteria = new HashMap<>();
        masterCriteria.put("Passing", new ArrayList<>());
        masterCriteria.put("Shooting", new ArrayList<>());
        masterCriteria.put("Scoring", new ArrayList<>());
        masterCriteria.put("Trophies", new ArrayList<>());
        
        Map<String, List<Object>> lineMasterCriteria = new HashMap<>();
        lineMasterCriteria.put("rate", new ArrayList<>());
        
        for(final Club club : clubs){
            final String name = club.getName();
            final String nameComma = "'" + name + "'";
            
            // Master Prepartion
            masterCriteria.get("Shooting").add(getMap(name, club.getStatus2().intValue()));
            masterCriteria.get("Passing").add(getMap(name, club.getStatus1().intValue(), true));
            masterCriteria.get("Scoring").add(getMap(name, club.getStatus3().intValue()));
            masterCriteria.get("Trophies").add(getMap(name, club.getStatus4().intValue()));
            lineMasterCriteria.get("rate").add(getMap(name, club.getStatus1().intValue()));
            
            // Add Status for Master
            highchart.addStatus(nameComma, name + " _Status", ClubStatus2.getStatusData(club.getStatus1Details()));
            
            // Add Childs
            if(club.getChilds() != null){
                Map<String, List<String>> childMasterCriteria = new HashMap<>();
                childMasterCriteria.put("Passing", new ArrayList<String>());
                childMasterCriteria.put("Shooting", new ArrayList<String>());
                childMasterCriteria.put("Scoring", new ArrayList<String>());
                childMasterCriteria.put("Trophies", new ArrayList<String>());
                
                for(ClubGroup group : club.getChilds()){
                    group.addChildHighchart(nameComma, childMasterCriteria, highchart);
                }
                
                // Add Master Child
                for(String criterias : childMasterCriteria.keySet()){
                    List<String> value = childMasterCriteria.get(criterias);
                    highchart.addChilds(nameComma, criterias,     value);
                }
            }
        }
        
        // Add Master Child
        for(String criterias : masterCriteria.keySet()){
            List<Object> value = masterCriteria.get(criterias);
            highchart.addMaster(new HighchartMaster(criterias, value));
        }
        for(String criterias : lineMasterCriteria.keySet()){
            List<Object> value = lineMasterCriteria.get(criterias);
            highchart.addMaster(new HighchartMaster("line", criterias, value));
        }
        
        return highchart;
    }
}
