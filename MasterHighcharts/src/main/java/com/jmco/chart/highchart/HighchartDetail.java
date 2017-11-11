package com.jmco.chart.highchart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Husam
 * @since 1.0.0
 * @datetime Nov 11, 2017 12:58:53 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighchartDetail {

    private String name;
    private String type;
    private Object data;

    @Override
    public String toString() {
        return "{name:'" + name + "',type:'" + type + "', data:" + data + "}";
    }
}
