package pdfanalysis;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import pdfanalysis.model.PdfInfo;

import javax.activation.FileDataSource;
import java.io.File;
import java.io.IOException;

public class MetadataReader {

    public MetadataReader() {
    }

    public PdfInfo read(File pdfFile) throws IOException {
        PdfInfo result = new PdfInfo();

        FileDataSource fd = new FileDataSource(pdfFile);
        PreflightParser parser = new PreflightParser(fd);
        parser.parse();
        PDDocument document = parser.getPDDocument();
        String producer = document.getDocumentInformation().getProducer();
        document.close();
        if (producer != null) {
            result.setCreationSW(producer);
        }
        result.setFilename(pdfFile.getName());
        COSDocument cosDocument = parser.getDocument();
        result.setVersion(Float.toString(cosDocument.getVersion()));
        cosDocument.close();
        document.close();
        return result;
    }

}
