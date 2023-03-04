package ru.sfedu.fantazy.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.IOException;

@Slf4j
public class UploadUtil {
    public static String[] nameEditIndividual(String[] arr){
        String[] newArr = new String[12];
        boolean flag = false;
        StringBuilder name = new StringBuilder();
        name.append(arr[2]).append(" ").append(arr[3]);
        if(arr[5].length() == 3) {
            flag = true;
            name.append(" ").append(arr[4]);
        }

        for(int i = 0,j = 0; i < 12; i++) {
            if(i == 2) {
                newArr[i] = name.toString();
                if(flag) j+=2;
                else j+=1;
            }
            else newArr[i] = arr[j];
            j++;
        }

        return newArr;
    }

    public static void tournamentEdit(String[] arr){
        if(arr.length == 6) {
            arr[2] = arr[3];
            arr[3] = arr[1].concat("-").concat(arr[4]);
            arr[4] = arr[5];
        }
    }

    public static String[] nameEditSprint(String[] arr){
        String[] newArr = new String[9];
        boolean flag = false;
        StringBuilder name = new StringBuilder();
        name.append(arr[2]).append(" ").append(arr[3]);
        if(arr[5].length() == 3) {
            flag = true;
            name.append(" ").append(arr[4]);
        }

        for(int i = 0,j = 0; i < 9; i++) {
            if(i == 2) {
                newArr[i] = name.toString();
                if(flag) j+=2;
                else j+=1;
            }
            else newArr[i] = arr[j];
            j++;
        }

        return newArr;
    }

    public static String[] nameEditMassStart(String[] arr){
        String[] newArr = new String[10];
        boolean flag = false;
        StringBuilder name = new StringBuilder();
        name.append(arr[2]).append(" ").append(arr[3]);
        if(arr[5].length() == 3) {
            flag = true;
            name.append(" ").append(arr[4]);
        }

        for(int i = 0,j = 0; i < 10; i++) {
            if(i == 2) {
                newArr[i] = name.toString();
                if(flag) j+=2;
                else j+=1;
            }
            else newArr[i] = arr[j];
            j++;
        }

        return newArr;
    }

    public static String[] nameEditPursuit(String[] arr){
        String[] newArr = new String[9];
        boolean flag = false;
        StringBuilder name = new StringBuilder();
        name.append(arr[2]).append(" ").append(arr[3]);
        if(arr[5].length() == 3) {
            flag = true;
            name.append(" ").append(arr[4]);
        }

        for(int i = 0,j = 0; i < 9; i++) {
            if(i == 2) {
                newArr[i] = name.toString();
                if(flag) j+=2;
                else j+=1;
            }
            else newArr[i] = arr[j];
            j++;
        }

        return newArr;
    }

    public static boolean checkRowResults(String row){
        String[] arr = row.split("\\s");
        if(!row.contains(".")) return false;
        if(!Character.isDigit(arr[0].charAt(0)) || !Character.isDigit(arr[1].charAt(0))) return false;
        if(arr.length < 10) return false;
        return arr[4].length() == 3 || arr[5].length() == 3;

    }

    public static String convertPdfToString(byte[] arr) throws IOException {
        log.debug("start convertPdfToString[1]");
        String pdfFileInText = "";
        //try {
        PDDocument document = PDDocument.load(arr);
        document.getClass();

        if (!document.isEncrypted()) {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);

            PDFTextStripper tStripper = new PDFTextStripper();

            pdfFileInText = tStripper.getText(document);
//                String[] lines = pdfFileInText.split("\\r?\\n");
//                for (String line : lines) {
//                    System.out.println(line);
//                }
        }

        log.debug("pdf converted[2]");
//        } catch (IOException e) {
//            log.error("convertPdfToString Error");
//            log.error(e.getClass().getName() + ": " + e.getMessage());
//        }
        return pdfFileInText;
    }
}
