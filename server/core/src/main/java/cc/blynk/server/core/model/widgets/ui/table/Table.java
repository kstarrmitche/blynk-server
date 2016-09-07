package cc.blynk.server.core.model.widgets.ui.table;

import cc.blynk.server.core.model.enums.PinType;
import cc.blynk.server.core.model.widgets.OnePinWidget;
import cc.blynk.utils.ParseUtil;
import cc.blynk.utils.structure.TableLimitedQueue;

import static cc.blynk.utils.StringUtils.BODY_SEPARATOR_STRING;


/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 28.03.16.
 */
public class Table extends OnePinWidget {

    public Column[] columns;

    public TableLimitedQueue<Row> rows = new TableLimitedQueue<>();

    public int currentRowIndex;

    public boolean isReoderingAllowed;

    public boolean isClickableRows;

    @Override
    public boolean updateIfSame(byte pin, PinType type, String value) {
        if (isSame(pin, type)) {
            String[] values = value.split(BODY_SEPARATOR_STRING);
            if (values.length > 0) {
                String tableCommand = values[0];
                switch (tableCommand) {
                    case "clr" :
                        rows.clear();
                        currentRowIndex = 0;
                        break;
                    case "add" :
                        if (values.length > 3) {
                            int id = ParseUtil.parseInt(values[1]);
                            String rowName = values[2];
                            String rowValue = values[3];
                            rows.add(new Row(id, rowName, rowValue));
                        }
                        break;
                    case "pick" :
                        if (values.length > 1) {
                            currentRowIndex = Math.min(ParseUtil.parseInt(values[1]), rows.size() - 1);
                        }
                        break;
                    case "select" :
                        if (values.length > 1) {
                            selectRow(values[1], true);
                        }
                        break;
                    case "deselect" :
                        if (values.length > 1) {
                            selectRow(values[1], false);
                        }
                        break;
                    case "order" :
                        if (values.length > 2) {
                            int oldIndex = ParseUtil.parseInt(values[1]);
                            int newIndex = ParseUtil.parseInt(values[2]);
                            rows.order(oldIndex, newIndex);
                        }
                        break;
                }
            }
            return true;
        }
        return false;
    }

    private void selectRow(String indexString, boolean select) {
        int index = ParseUtil.parseInt(indexString);
        Row row = get(index);
        if (row != null) {
            row.isSelected = select;
        }
    }

    public Row get(int index) {
        for (Row row : rows) {
            if (index == row.id) {
                return row;
            }
        }
        return null;
    }

    @Override
    public String getModeType() {
        return "out";
    }

    @Override
    public int getPrice() {
        return 800;
    }

}