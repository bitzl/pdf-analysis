package pdfanalysis.validator;

import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.exception.SyntaxValidationException;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import pdfanalysis.model.PdfInfo;

import javax.activation.FileDataSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfBoxValidation implements PdfValidation {


    @Override
    public List<PdfInfo> validate(File pdfFile, PdfInfo info) throws IOException {
        List<PdfInfo> result = new ArrayList<>();
        ValidationResult validationResult = null;
        FileDataSource fd = new FileDataSource(pdfFile);
        PreflightParser parser = new PreflightParser(fd);
        try {
            parser.parse();
            PreflightDocument document = parser.getPreflightDocument();
            if (document != null) {
                document.validate();
                validationResult = document.getResult();
                document.close();
            }
            else {
                System.out.println("PreflightDocument is null for file "  + pdfFile.getName());
            }
        }
        catch (SyntaxValidationException e) {
            validationResult = e.getResult();
        }
        if (validationResult != null) {
            if (validationResult.isValid()) {
                PdfInfo successInfo = new PdfInfo(info);
                successInfo.setValid(true);
                result.add(successInfo);
            } else {
                for (ValidationResult.ValidationError error : validationResult.getErrorsList()) {
                    String errorCode = error.getErrorCode();
                    int errorGroup = Integer.parseInt(errorCode.substring(0, 1));
                    PdfInfo errorInfo = new PdfInfo(info);
                    errorInfo.setErrorCode(errorCode);
                    errorInfo.setErrorMessage(error.getDetails());
                    errorInfo.setErrorGroup(errorGroup);
                    errorInfo.setValid(false);
                    result.add(errorInfo);
                }
            }
        }
        return result;
    }
}
