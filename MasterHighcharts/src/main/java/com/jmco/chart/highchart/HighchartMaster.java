package com.jmco.chart.highchart;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Husam
 * @since 1.0.0
 * @datetime Nov 11, 2017 12:59:33 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighchartMaster {
    
    private String type;
    private String name;
    private List<Object> data;
    
    public HighchartMaster(String name, List<Object> data){
        this.name = name;
        this.data = data;
    }
    
    @Override
    public String toString() {
        String result = "{";
        if(type != null){
            result += "type:'" + type + "',";
        }
        return result + "name:'" + name + "', data:" + data + "}";
    }
}
