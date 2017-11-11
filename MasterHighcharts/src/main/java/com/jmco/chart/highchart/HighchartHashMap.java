package com.jmco.chart.highchart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Husam
 * @param <K>
 * @param <V>
 * @since 1.0.0
 * @datetime Nov 10, 2017 8:23:35 AM
 */
public class HighchartHashMap<K, V> extends HashMap<K, V> {

    public HighchartHashMap() {
    }
    
    public HighchartHashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }
    
    public static <K, V> Map<K, V> getMap(K[] keys, V[] values){
        HighchartHashMap<K, V> result = new HighchartHashMap<>();
        if(keys != null && values != null && 
           keys.length == values.length){
            for(int i = 0; i < keys.length; i++){
                result.put(keys[i], values[i]);
            }
        }
        return result;
    }
    
    public static Map<String, Object> getMap(String name, int y){
        return HighchartHashMap.getMap(new String[]{"name", "y"}, new Object[]{"'"+name+"'", y});
    }
    
    public static Map<String, Object> getMap(String name, int y, boolean drilldown){
        return HighchartHashMap.getMap(new String[]{"name", "y", "drilldown"}, new Object[]{"'"+name+"'", y, drilldown});
    }
    
    public static Map<String, Object> getMap(String name, int y, String type){
        return HighchartHashMap.getMap(new String[]{"name", "y", "type"}, new Object[]{"'"+name+"'", y, "'"+type+"'"});
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (K K : keySet()) {
            result.append(K).append(":").append(get(K)).append(",");
        }
        return result.substring(0, result.lastIndexOf(",")) + "}";
    }
}
