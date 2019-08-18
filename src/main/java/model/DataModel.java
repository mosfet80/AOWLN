package model;

import interfaces.ModelObserver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


public class DataModel {

    // Eine (versteckte) Klassenvariable vom Typ der eigenen Klasse
    private static DataModel instance;
    // Verhindere die Erzeugung des Objektes über andere Methoden
    private DataModel () {}
    // Eine Zugriffsmethode auf Klassenebene, welches dir '''einmal''' ein konkretes
    // Objekt erzeugt und dieses zurückliefert.
    public static DataModel getInstance () {
        if (DataModel.instance == null) {
            DataModel.instance = new DataModel ();
        }
        return DataModel.instance;
    }

    private ArrayList<String> SWRLRulesAsString;
    private BufferedImage currentHead;
    private BufferedImage currentBody;
    private File graphVizEngine;

    private ArrayList<ModelObserver> observerList = new ArrayList<>();

    public void registerObserver(ModelObserver modelObserver) {
        observerList.add(modelObserver);
    }

    public void removeObserver(ModelObserver modelObserver) {
        observerList.remove(modelObserver);
    }

    public File getGraphVizEngine() {
        return graphVizEngine;
    }

    public void setGraphVizEngine(File graphVizEngine) {
        this.graphVizEngine = graphVizEngine;
    }

    public ArrayList<String> getSWRLRulesAsString() {
        return SWRLRulesAsString;
    }

    public void setSWRLRulesAsString(ArrayList<String> SWRLRulesAsString) {
        this.SWRLRulesAsString = SWRLRulesAsString;

        for (ModelObserver modelObserver : observerList) {
            modelObserver.ruleListHasChanged(SWRLRulesAsString);
        }
    }

    public BufferedImage getCurrentHead() {
        return currentHead;
    }

    public void setCurrentHead(BufferedImage currentHead) {
        this.currentHead = currentHead;
    }

    public BufferedImage getCurrentBody() {
        return currentBody;
    }

    public void setCurrentBody(BufferedImage currentBody) {
        this.currentBody = currentBody;
    }
}
