package br.com.gogar.sistema.api.convert;

import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.Closeable;
import java.io.IOException;

public class WritableWorkbookWrapper implements Closeable {
    private final WritableWorkbook workbook;

    public WritableWorkbookWrapper(WritableWorkbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public void close() throws IOException {
        try {
            workbook.close();
        } catch (WriteException e) {
            throw new IOException("Erro ao fechar o WritableWorkbook", e);
        }
    }

    public WritableWorkbook getWorkbook() {
        return workbook;
    }
}