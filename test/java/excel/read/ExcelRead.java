package excel.read;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcelRead {

    /**
     * Return List of Objects, size depends on numberOfColumns, default , default -- first row is for header, double is default type for variables
     */
    public static List<String[]> readFromExcel(String pathToFile, String nameOfSheet, int numberOfColumns) throws IOException, BiffException {
        File inputWorkbook = new File(pathToFile);
        Workbook w;
        List<String[]> list = new ArrayList<String[]>();
        w = Workbook.getWorkbook(inputWorkbook);
        Sheet sheet = w.getSheet(nameOfSheet);
        String[] stringObject = new String[numberOfColumns];
        for (int i = 1; i < sheet.getRows(); i++) {
            for (int i1 = 0; i1 < stringObject.length; i1++) {
                stringObject[i1] = convert(sheet.getCell(i1, i).getContents());
            }
            if (stringObject[0].equals("")) {
                break;
            } else {
                String[] objects = new String [stringObject.length];
                for (int i1 = 0; i1 < stringObject.length; i1++) {
                    objects[i1] = (stringObject[i1]);
                }
                list.add(objects);
            }
        }

        return list;
    }

    /**
     * Return List of Objects, size depends on numberOfColumns, default -- first row is for header, double is default type for variables
     * Last column is for expected value, can be parametrized (String, Integer, Boolean, Double)
     */
    public static List<Object[]> readFromExcel(String pathToFile, String nameOfSheet, int numberOfColumns, String typeOfExpected) throws IOException, BiffException {
        File inputWorkbook = new File(pathToFile);
        Workbook w;
        List<Object[]> list = new ArrayList<Object[]>();
        w = Workbook.getWorkbook(inputWorkbook);
        Sheet sheet = w.getSheet(nameOfSheet);
        String[] stringObject = new String[numberOfColumns];
        for (int i = 1; i < sheet.getRows(); i++) {
            for (int i1 = 0; i1 < stringObject.length; i1++) {
                stringObject[i1] = convert(sheet.getCell(i1, i).getContents());
            }
            if (stringObject[0].equals("")) {
                break;
            } else {
                Object[] objects = new Object[stringObject.length];
                for (int i1 = 0; i1 < stringObject.length; i1++) {
                    if (i1 == stringObject.length - 1) {
                        if (typeOfExpected.equalsIgnoreCase("Double")) {
                            objects[i1] = Double.parseDouble(stringObject[i1]);
                            break;
                        } else if (typeOfExpected.equalsIgnoreCase("Integer")) {
                            objects[i1] = Integer.parseInt(stringObject[i1]);
                            break;
                        } else if (typeOfExpected.equalsIgnoreCase("String")) {
                            objects[i1] = stringObject[i1];
                            System.out.println(objects[i1]);
                            break;
                        } else if (typeOfExpected.equalsIgnoreCase("boolean")) {
                            objects[i1] = Boolean.parseBoolean(stringObject[i1]);
                            break;
                        }


                    }
                    objects[i1] = stringObject[i1];
                }

                list.add(objects);
            }
        }

        return list;
    }

    /**
     * convert String values to it double Analogs, for example Positive.Infinity to Double.POSITIVE_INFINITY
     */
    public static String convert(String value) {
        if (value.equals("Positive.Infinity")) {
            value = String.valueOf(Double.POSITIVE_INFINITY);
            return value;
        } else if (value.equals("NaN")) {
            value = String.valueOf(Double.NaN);
            return value;
        } else if (value.equals("null")) {
            value = null;
            return value;
        } else if (value.equals(" ")) {
            value = " ";
            return value;
        } else if (value.equals("")) {
            value = "";
            return value;
        } else {
            return value;
        }

    }
}
