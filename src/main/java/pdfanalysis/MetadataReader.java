package pdfanalysis;

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
        String producer = parser.getPDDocument().getDocumentInformation().getProducer();
        if (producer != null) {
            result.setCreationSW(producer);
        }
        result.setFilename(pdfFile.getName());
        result.setVersion(Float.toString(parser.getDocument().getVersion()));
        return result;
    }

}
