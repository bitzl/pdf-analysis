package pdfanalysis.validator;

import pdfanalysis.model.PdfInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PdfValidation {

    public List<PdfInfo> validate(File pdfFile, PdfInfo info) throws IOException;

}
