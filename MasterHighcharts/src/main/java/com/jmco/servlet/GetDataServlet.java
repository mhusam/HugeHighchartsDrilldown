package com.jmco.servlet;

import com.jmco.chart.highchart.Highchart;
import com.jmco.chart.highchart.HighchartGenerator;
import static com.jmco.chart.highchart.HighchartHashMap.*;
import com.jmco.chart.highchart.HighchartMaster;
import com.jmco.model.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Husam
 * @since 1.0.0
 * @datetime Nov 7, 2017 5:48:42 PM
 */
@WebServlet(urlPatterns = "/GetDataFromServer")
public class GetDataServlet extends HttpServlet {
    
    Map<String, Object> ctiriea1Data = getMap("Real Madrid", 33);
    Map<String, Object> ctiriea2Data = getMap("Real Madrid", 40, true);
    Map<String, Object> ctiriea3Data = getMap("Real Madrid", 77);
    Map<String, Object> ctiriea4Data = getMap("Real Madrid", 150);
    
    private Highchart getDummyData() {
        Highchart highchart = new Highchart();

        HighchartMaster critire1 = new HighchartMaster("Passing", new ArrayList<Object>(Arrays.asList(ctiriea1Data)));
        HighchartMaster critire2 = new HighchartMaster("Shooting", new ArrayList<Object>(Arrays.asList(ctiriea2Data)));
        HighchartMaster critire3 = new HighchartMaster("Scoring", new ArrayList<Object>(Arrays.asList(ctiriea3Data)));
        HighchartMaster critire4 = new HighchartMaster("Trophies", new ArrayList<Object>(Arrays.asList(ctiriea4Data)));

        highchart.addMaster(critire1, critire2, critire3, critire4);
        
        highchart.addStatus("'Real Madrid'", "Real Madrid Status", new ArrayList<List>(){{
            add(Arrays.asList("'status 1'", 55));
            add(Arrays.asList("'status 2'", 22));
            add(Arrays.asList("'status 3'", 88));
            add(Arrays.asList("'status 4'", 45));
        }});
        
        highchart.addChilds("'Real Madrid'", "Passing",     Arrays.asList(getMap("Ronaldo", 10), getMap("Husam", 10), getMap("Ahmad", 3), getMap("Ramos", 8)));
        highchart.addChilds("'Real Madrid'", "Shooting",    Arrays.asList(getMap("Ronaldo", 6), getMap("Husam", 9, true), getMap("Ahmad", 7), getMap("Ramos", 5)));
        highchart.addChilds("'Real Madrid'", "Scoring",     Arrays.asList(getMap("Ronaldo", 3), getMap("Husam", 9), getMap("Ahmad", 1), getMap("Ramos", 6)));
        highchart.addChilds("'Real Madrid'", "Trophies",    Arrays.asList(getMap("Ronaldo", 9), getMap("Husam", 8), getMap("Ahmad", 9), getMap("Ramos", 3)));
        
        
        
        highchart.addStatus("'Husam'", "Real Madrid Status", new ArrayList<List>(){{
            add(Arrays.asList("'status 1'", 55));
            add(Arrays.asList("'status 2'", 22));
            add(Arrays.asList("'status 3'", 88));
            add(Arrays.asList("'status 4'", 45));
        }});
        
        highchart.addChilds("'Husam'", "Passing",     Arrays.asList(getMap("Husam1", 10), getMap("Husam2", 10), getMap("Husam3", 3), getMap("Husam4", 8)));
        highchart.addChilds("'Husam'", "Shooting",    Arrays.asList(getMap("Husam1", 6), getMap("Husam2", 9, true), getMap("Husam3", 7), getMap("Husam4", 5)));
        highchart.addChilds("'Husam'", "Scoring",     Arrays.asList(getMap("Husam1", 3), getMap("Husam2", 9), getMap("Husam3", 1), getMap("Husam4", 6)));
        highchart.addChilds("'Husam'", "Trophies",    Arrays.asList(getMap("Husam1", 9), getMap("Husam2", 8), getMap("Husam3", 9), getMap("Husam4", 3)));
        
        
        System.out.println(highchart);
        return highchart;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
//        response.getWriter().print(getDummyData());
        response.getWriter().print(getHighchart());
        response.getWriter().flush();
    }
    
    public static Highchart getHighchart() {
        Highchart highchart = HighchartGenerator.generate(Data.CLUBS);
        System.out.println(highchart);
        return highchart;
    }
}
