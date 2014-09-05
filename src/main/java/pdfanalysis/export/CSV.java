package pdfanalysis.export;

import pdfanalysis.model.PdfInfo;

import java.io.IOException;
import java.io.Writer;

public class CSV implements InfoWriter {

    private static final String SEPARATOR = "\t";
    private static final String NEWLINE = "\n";
    private static final String MISSING_VALUE = "";

    private Writer writer;

    public CSV(Writer writer) {
        this.writer = writer;
    }

    public void write(PdfInfo pdfInfo) throws IOException {
        write(pdfInfo.getFilename());
        write(SEPARATOR);
        write(pdfInfo.getCreationSW());
        write(SEPARATOR);
        write(pdfInfo.getVersion());
        write(SEPARATOR);
        write(pdfInfo.isValid());
        write(SEPARATOR);
        write(pdfInfo.getErrorGroup());
        write(SEPARATOR);
        write(pdfInfo.getErrorCode());
        write(SEPARATOR);
        write(pdfInfo.getErrorMessage());
        write(NEWLINE);
    }

    private void write(String string) throws IOException {
        if (string != null) {
            writer.write(string);
        }
        else {
            writer.write(MISSING_VALUE);
        }
    }

    private void write(Boolean bool) throws IOException {
        if (bool != null) {
            writer.write(Boolean.toString(bool));
        }
        else {
            writer.write(MISSING_VALUE);
        }
    }

    private void write(Integer integer) throws IOException {
        if (integer != null) {
            writer.write(integer);
        }
        else {
            writer.write(MISSING_VALUE);
        }
    }

    public void close() throws IOException {
        writer.close();
    }
}
