package line;

import com.jmco.chart.highchart.*;
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
    
    public static <K> Map<String, HighchartMaster<K>> getDefaultCriteria(){
        Map<String, HighchartMaster<K>> criteria = new HighchartHashMap<>();
        criteria.put("Passing", new HighchartMaster<K>("column", "Passing", null));
        criteria.put("Shooting", new HighchartMaster<K>("column", "Shooting", null));
        criteria.put("Scoring", new HighchartMaster<K>("column", "Scoring", null));
        criteria.put("Trophies", new HighchartMaster<K>("column", "Trophies", null));
        criteria.put("line", new HighchartMaster<K>("line", "Rate", null));
        return criteria;
    }
    
    public static Map<String, List<HighchartMaster<String>>> getDefaultChildCriteria(){
        Map<String, List<HighchartMaster<String>>> criteria = new HighchartHashMap<>();
        criteria.put("Passing", new ArrayList<HighchartMaster<String>>());
        criteria.put("Shooting", new ArrayList<HighchartMaster<String>>());
        criteria.put("Scoring", new ArrayList<HighchartMaster<String>>());
        criteria.put("Trophies", new ArrayList<HighchartMaster<String>>());
        criteria.put("line", new ArrayList<HighchartMaster<String>>());
        return criteria;
    }
    
    public static Highchart generate(List<Club> clubs) {
        Highchart highchart = new Highchart();
        
        Map<String, HighchartMaster<Object>> masterCriteria = getDefaultCriteria();
        
        for(final Club club : clubs){
            final String name = club.getName();
            final String nameComma = "'" + name + "'";
            
            // Master Prepartion 
            masterCriteria.get("Shooting").setData(new ArrayList<Object>(){{add(getMap(name, club.getStatus1().intValue()));}});
            masterCriteria.get("Passing").setData(new ArrayList<Object>(){{add(getMap(name, club.getStatus2().intValue(), true));}});
            masterCriteria.get("Scoring").setData(new ArrayList<Object>(){{add(getMap(name, club.getStatus3().intValue()));}});
            masterCriteria.get("Trophies").setData(new ArrayList<Object>(){{add(getMap(name, club.getStatus4().intValue()));}});
            masterCriteria.get("line").setData(new ArrayList<Object>(){{add(getMap(name, club.getStatus1().intValue()));}});

            // Add Status for Master
            highchart.addStatus(nameComma, name + " _Status", ClubStatus2.getStatusData(club.getStatus1Details()));
            
            // Add Childs
            if(club.getChilds() != null){
                Map<String, List<HighchartMaster<String>>> childMasterCriteria = getDefaultChildCriteria();
                
                for(ClubGroup group : club.getChilds()){
                    //group.addChildHighchart(nameComma, childMasterCriteria, highchart);
                }
                
                // Add Master Child
                Highchart.addChilds(highchart, nameComma, childMasterCriteria);
            }
        }
        
        // Add Master Child
        Highchart.addMaster(highchart, masterCriteria);
        
        return highchart;
    }
}
