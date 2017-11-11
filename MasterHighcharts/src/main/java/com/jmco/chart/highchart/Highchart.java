package com.jmco.chart.highchart;

import static com.jmco.chart.highchart.HighchartHashMap.getMap;
import com.jmco.model.Club;
import com.jmco.model.ClubGroup;
import com.jmco.model.ClubStatus2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Husam
 * @since  1.0.0
 * @datetime   Nov 10, 2017  7:36:51 AM
 */
public class Highchart {
    
    /** This is the series for Highcharts */
    private List<HighchartMaster> master;
    
    /** This is the drilldowns for Highcharts based on master */
    private Map<String, Map<String, List<HighchartDetail>>> detail; 
    
    public List<HighchartMaster> getMaster() {
        return master != null ? master : (master = new ArrayList<>());
    }

    public Map<String, Map<String, List<HighchartDetail>>> getDetail() {
        return detail != null ? detail : (detail = new HighchartHashMap<>());
    }
    
    public void addMaster(HighchartMaster... master){
        if(master != null && master.length > 0){
            getMaster().addAll(Arrays.asList(master));
        }
    }
    
    private void addDetail(String key, Map<String, List<HighchartDetail>> value){
        if(key != null && !key.trim().isEmpty() && value != null){
            getDetail().put(key, value);
        }
    }
    
    public void addStatus(String key, String name, Object data){
        Map<String, List<HighchartDetail>> keyDetail = getDetail().get(key);
        if(keyDetail == null){
            keyDetail = new HighchartHashMap<>();
            addDetail(key, keyDetail);
        }
        List<HighchartDetail> keyDetailStatus = getDetail().get(key).get("'status'");
        if(keyDetailStatus == null){
            keyDetailStatus = new ArrayList<>();
            getDetail().get(key).put("'status'", keyDetailStatus);
        }
        if(data != null){
            // only one item in status
            HighchartDetail statusDetail = new HighchartDetail(name, "pie", data);
            getDetail().get(key).get("'status'").add(statusDetail);
        }
    }
    
    public void addChilds(String key, String name, Object data){
        Map<String, List<HighchartDetail>> keyDetail = getDetail().get(key);
        if(keyDetail == null){
            keyDetail = new HighchartHashMap<>();
            addDetail(key, keyDetail);
        }
        List<HighchartDetail> keyDetailChilds = getDetail().get(key).get("'childs'");
        if(keyDetailChilds == null){
            keyDetailChilds = new ArrayList<>();
            getDetail().get(key).put("'childs'", keyDetailChilds);
        }
        // only one item in status
        if(data != null){
            HighchartDetail statusDetail = new HighchartDetail(name, "column", data);
            getDetail().get(key).get("'childs'").add(statusDetail);
        }
    }

    @Override
    public String toString() {
        return "{master:" + master + ", detail: " + detail + "}";
    }
    
    public static void addMaster(Highchart highchart, Map<String, List<Object>> masterCriteria){
        for(String criterias : masterCriteria.keySet()){
            List<Object> value = masterCriteria.get(criterias);
            highchart.addMaster(new HighchartMaster(criterias, value));
        }
    }
    
    public static void addMaster(Highchart highchart, String nameComma, Map<String, List<String>> childsCriteria){
        for(String criterias : childsCriteria.keySet()){
            List<String> value = childsCriteria.get(criterias);
            highchart.addChilds(nameComma, criterias,     value);
        }
    }
}
