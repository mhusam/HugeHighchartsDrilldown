package line;

import com.jmco.chart.highchart.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Husam
 * @param <T>
 * @since 1.0.0
 * @datetime Nov 11, 2017 12:59:33 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighchartMaster<T> {

    private String type;
    private String name;
    private List<T> data;

    public HighchartMaster(String name, List<T> data) {
        this.name = name;
        this.data = data;
    }

    public HighchartMaster(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String result = "{";
        if (type != null) {
            result += "type:'" + type + "',";
        }
        return result + "name:'" + name + "', data:" + data + "}";
    }
}
