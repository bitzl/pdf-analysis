package pdfanalysis.export;

import pdfanalysis.model.PdfInfo;

import java.io.IOException;

public interface InfoWriter {

    public void write(PdfInfo pdfInfo) throws IOException;

    public void close() throws IOException;

}
