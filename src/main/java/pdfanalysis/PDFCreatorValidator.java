package pdfanalysis;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pdfanalysis.export.CSV;
import pdfanalysis.export.InfoWriter;
import pdfanalysis.model.PdfInfo;
import pdfanalysis.validator.PdfBoxValidation;
import pdfanalysis.validator.PdfValidation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PDFCreatorValidator {

    final Logger logger = LoggerFactory.getLogger(PDFCreatorValidator.class);

    public void main() throws IOException {
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
                List<PdfInfo> results = Collections.emptyList();
                try {
                    PdfInfo info = metadataReader.read(pdfFile);
                    results = pdfValidation.validate(pdfFile, info);
                }
                catch (Exception e) {
                    logger.error("Error analyzing " + pdfFile.getAbsolutePath(), e);
                }
                for (PdfInfo result : results) {
                    csv.write(result);
                }
            }
        }

        writer.close();
    }

    public static void main(String[] args) throws IOException {
        PDFCreatorValidator creatorValidator = new PDFCreatorValidator();
        creatorValidator.main();
    }

}
