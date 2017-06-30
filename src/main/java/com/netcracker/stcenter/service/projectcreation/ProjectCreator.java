package com.netcracker.stcenter.service.projectcreation;

import com.google.gson.JsonObject;
import com.netcracker.stcenter.common.ProjectCreationResultHolder;
import com.netcracker.stcenter.common.configuration.ProjectUserInfo;
import com.netcracker.stcenter.common.utils.dateconverter.DateFormat;
import com.netcracker.stcenter.common.utils.graphs.JsonSerializer;
import com.netcracker.stcenter.common.utils.xmlparser.XmlParser;
import com.netcracker.stcenter.common.utils.xmlparser.XmlParserImpl;
import com.netcracker.stcenter.project.dao.DAOBootstrapper;
import com.netcracker.stcenter.project.dao.ProjectCreationDAO;
import com.netcracker.stcenter.project.models.DataVisualizationProject;
import com.netcracker.stcenter.project.models.DataVisualizationProjectImpl;
import com.netcracker.stcenter.project.models.Graphic;
import com.netcracker.stcenter.project.models.GraphicDVImpl;
import com.netcracker.stcenter.service.AbstractServiceOperation;
import com.netcracker.stcenter.testrequest.GraphType;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProjectCreator {
    private static final Logger log = Logger.getLogger(AbstractServiceOperation.class);

    public static ProjectCreationResultHolder execute(ProjectCreationRequestAdapter requestAdapter) {
        ProjectCreationResultHolder projectCreationResultHolder = new ProjectCreationResultHolder();

        try {
            Path path = Paths.get(System.getProperty("java.io.tmpdir")).resolve(requestAdapter.getProjectCreationAdapter().getProjectName() + ".xml");
            Files.write(path, requestAdapter.getProjectCreationAdapter().getTableAdapter().getTableXml().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        DateFormat dateFormat = DateFormat.getDateFormatByString(requestAdapter.getProjectCreationAdapter().getDateFormat());


        File fileFromTomcat = new File(Paths.get(System.getProperty("java.io.tmpdir")).resolve(requestAdapter.getProjectCreationAdapter().getProjectName() + ".xml").toUri());


        DataVisualizationProject project = new DataVisualizationProjectImpl.Builder(
                requestAdapter.getProjectCreationAdapter().getProjectName(), new Date(), ProjectUserInfo.USER_ID, ProjectUserInfo.USER_FULL_NAME).buildProject();
        XmlParser parser = new XmlParserImpl();
        List<Map<String, Object>> dataFromTable = null;


        try {
            dataFromTable = parser.parseXmlFile(fileFromTomcat, dateFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.debug("started printing parsed data");
        for (Map<String, Object> stringObjectMap : dataFromTable) {
            for (Map.Entry<String, Object> stringObjectEntry : stringObjectMap.entrySet()) {
                log.debug("ParsedData k:" + stringObjectEntry.getKey() + " v:" + stringObjectEntry.getValue());
            }
        }
        log.debug("finished printing parsed data");

        GraphType[] graphs = requestAdapter.getProjectCreationAdapter().getGraphAdapter().getGraphs();
        List<Graphic> listOfGraphics = new ArrayList<>();
        for (int i = 0; i < graphs.length; i++) {
            String xAxis = graphs[i].getXAxis();
            String yAxis = graphs[i].getYAxis();
            String mathCol = graphs[i].getMathCol();
            JsonObject jsonGraph = JsonSerializer.serializeGraph(dataFromTable, xAxis, yAxis, mathCol);
            GraphicDVImpl graphic = new GraphicDVImpl.DVGraphBuilder()
                    .buildGraphicJSON(jsonGraph)
                    .buildName(project.getName() + " ws " + i)
                   /* .buildAverage(CalculationService.calculateAverage(dataFromTable, mathCol))
                    .buildDispersion(CalculationService.calculateDispersion(dataFromTable, mathCol))
                    .buildMathExpectation(CalculationService.calculationMathExpectation(dataFromTable, mathCol))
                    .buildOlympicAverage(CalculationService.calculateOlympicAverage(dataFromTable, mathCol))*/
                    .buildGraphic();
            listOfGraphics.add(graphic);
        }
        ((DataVisualizationProjectImpl) project).setGraphics(listOfGraphics);
        ProjectCreationDAO dao = new DAOBootstrapper().bootstrapDao();
        DataVisualizationProject dvFromDataBase = dao.saveProject(project);

        projectCreationResultHolder.setProjectName(requestAdapter.getProjectCreationAdapter().getProjectName());
        projectCreationResultHolder.setUserLogin(ProjectUserInfo.USER_EMAIL);
        projectCreationResultHolder.setUserPassword(ProjectUserInfo.USER_PASSWORD);
        if (dvFromDataBase == null) {
            projectCreationResultHolder.setStatus(true);
        } else {
            projectCreationResultHolder.setStatus(true);
        }


        return projectCreationResultHolder;
    }


}
