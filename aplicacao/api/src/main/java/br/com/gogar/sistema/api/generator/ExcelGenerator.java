package br.com.gogar.sistema.api.generator;

import br.com.gogar.sistema.api.domain.entity.ProposalFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelGenerator {

    public void generateExcelFile(String fileName, List<ProposalFile> file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(fileName)) {

            CreationHelper createHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("proposalFile");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Escreva cabeçalho da tabela
            Row headerRow = sheet.createRow(0);
            Cell headerCell = headerRow.createCell(0);

            headerCell.setCellValue("Id");
            headerCell.setCellStyle(headerCellStyle);
            headerCell = headerRow.createCell(1);

            headerCell.setCellValue("nameService");
            headerCell.setCellStyle(headerCellStyle);
            headerCell = headerRow.createCell(2);

            headerCell.setCellValue("amount");
            headerCell.setCellStyle(headerCellStyle);
            headerCell = headerRow.createCell(3);

            headerCell.setCellValue("priceProposal");
            headerCell.setCellStyle(headerCellStyle);
            headerCell = headerRow.createCell(4);

            headerCell.setCellValue("description");
            headerCell.setCellStyle(headerCellStyle);
            headerCell = headerRow.createCell(5);

            headerCell.setCellStyle(headerCellStyle);

            int rowNum = 1;
            for (ProposalFile files : file) {
                Row row = sheet.createRow(rowNum++);

                Cell idCell = row.createCell(0);
                idCell.setCellValue(files.getId());

                Cell nameServiceCell = row.createCell(1);
                nameServiceCell.setCellValue(files.getNameService());

                Cell amountCell = row.createCell(2);
                amountCell.setCellValue(files.getAmount());

                Cell priceProposalCell = row.createCell(3);
                priceProposalCell.setCellValue((RichTextString) files.getPriceProposal());

                Cell descriptionCell = row.createCell(4);
                descriptionCell.setCellValue(files.getDescription());
            }

            workbook.write(outputStream);
        }
    }
}