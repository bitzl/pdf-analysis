package pdfanalysis;

import org.apache.commons.io.FileUtils;
import pdfanalysis.export.CSV;
import pdfanalysis.export.InfoWriter;
import pdfanalysis.model.PdfInfo;
import pdfanalysis.validator.PdfBoxValidation;
import pdfanalysis.validator.PdfValidation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PDFCreatorValidator {

    public static void main(String[] args) throws IOException {
        String csvFilename = "data.csv";

        String sourcePath = PdfUtilities.chooseFolder();

        File sourceFolder = new File(sourcePath);
        String[] extensions = { "pdf" };
        Iterator<File> pdfFiles = FileUtils.iterateFiles(sourceFolder, extensions, true);


        FileWriter writer = new FileWriter(csvFilename);
        InfoWriter csv = new CSV(writer);

        MetadataReader metadataReader = new MetadataReader();
        PdfValidation pdfValidation = new PdfBoxValidation();

        while (pdfFiles.hasNext()) {
            File pdfFile = pdfFiles.next();
            if (!pdfFile.isDirectory()) {
                PdfInfo info = metadataReader.read(pdfFile);
                List<PdfInfo> results = pdfValidation.validate(pdfFile, info);
                for (PdfInfo result : results) {
                    csv.write(result);
                }
            }
        }

        writer.close();
    }

}
