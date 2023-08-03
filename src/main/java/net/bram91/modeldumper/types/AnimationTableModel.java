package net.bram91.modeldumper.types;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;

public class AnimationTableModel extends AbstractTableModel {
    private Object[] data;
    private String name;
    private HashMap<Integer,String> modelExporterData;

    public AnimationTableModel(Object[] data, String name, HashMap<Integer,String> modelExporterData){
        super();
        this.data = data ;
        this.name = name;
        this.modelExporterData = modelExporterData;
   }

    public int getColumnCount() {
        return 1;
    }
    public int getRowCount() {
        return data.length;
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        if(data[row] instanceof Animation && ((Animation) data[row]).getName() == null && modelExporterData.containsKey(((Animation) data[row]).getId()))
        {
            ((Animation) data[row]).setName(modelExporterData.get(((Animation) data[row]).getId()));
        }
        return data[row];
    }

    public String getColumnName(int col) {
        return name;
    }

}