package br.com.gogar.sistema.api.domain.service;

import br.com.gogar.sistema.api.convert.WritableWorkbookWrapper;
import br.com.gogar.sistema.api.domain.dto.FileDTO;
import br.com.gogar.sistema.api.domain.entity.ProposalFile;
import br.com.gogar.sistema.api.domain.repository.ProposalFileRepository;
import br.com.gogar.sistema.api.generator.ExcelGenerator;
import jakarta.persistence.EntityNotFoundException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ProposalFileService {
    @Autowired
    ProposalFileRepository proposalFileRepository;

    private final ExcelGenerator excelGenerator;

    @Autowired
    public ProposalFileService(ExcelGenerator excelGenerator) {
        this.excelGenerator = excelGenerator;
    }



    //métado que gera o arquivo excel
    public void generateExcelFile() throws Exception {
        List<ProposalFile> proposalFileList = proposalFileRepository.findAll();
        try (FileOutputStream fos = new FileOutputStream("ProposalFile.xls");
             WritableWorkbookWrapper workbookWrapper = new WritableWorkbookWrapper(Workbook.createWorkbook(fos))) {

            WritableSheet sheet = workbookWrapper.getWorkbook().createSheet("File", 0);

            int row = 0;
            int col = 0;

            sheet.addCell(new Label(col++, row, "Id"));
            sheet.addCell(new Label(col++, row, "Name service"));
            sheet.addCell(new Label(col++, row, "Amount"));
            sheet.addCell(new Label(col++, row, "Price proposal"));
            sheet.addCell(new Label(col++, row, "Description"));

            for (ProposalFile files : proposalFileList) {
                row++;
                col = 0;

                sheet.addCell(new Label(col++, row, String.valueOf(files.getId())));
                sheet.addCell(new Label(col++, row, files.getNameService()));
                sheet.addCell(new Label(col++, row, String.valueOf(files.getPriceProposal())));
                sheet.addCell(new Label(col++, row, String.valueOf(files.getAmount())));
                sheet.addCell(new Label(col++, row, files.getDescription()));
            }
        } catch (IOException | WriteException e) {
            e.printStackTrace();
            throw new Exception("Erro ao gerar arquivo Excel", e);
        }
    }



    // metado para salvar
    public ProposalFile saveFile(FileDTO data){

        var fileSave = new ProposalFile(data);

        return proposalFileRepository.save(fileSave);
    }
    //metado para listar todos
    public List<ProposalFile> listAllFiles(){
        var lists = proposalFileRepository.findAll();
        return lists;
    }

    //metado para listar por id
    public ProposalFile listById(Long id){
        var list = proposalFileRepository.findById(id).get();
        return list;
    }

    //metado para atualizar parcialmente
    public ProposalFile updateAll(Long id, Map<String,Object> data) {
        Optional<ProposalFile> proposalFile = proposalFileRepository.findById(id);

        if (proposalFile.isPresent()) {
            ProposalFile update = proposalFile.get();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if ("nameService".equals(key) && value instanceof String) {
                    update.setNameService((String) value);
                } else if ("description".equals(key) && value instanceof String) {
                    update.setDescription((String) value);
                } else if ("priceProposal".equals(key) && value instanceof BigDecimal) {
                    update.setPriceProposal((BigDecimal) value);
                } else if ("amount".equals(key) && value instanceof Integer) {
                    update.setAmount((Integer) value);
                }
            }
            return proposalFileRepository.save(update);

        }else{
            throw new EntityNotFoundException();
        }
    }
    //metado para atualizar todos
    public ProposalFile updateAll(Long id, FileDTO data) {
        Optional<ProposalFile> optional = proposalFileRepository.findById(id);
        if (optional.isPresent()) {
            ProposalFile updates = optional.get();

            if (data.getNameService() != null) {
                updates.setNameService(data.getNameService());
            }
            if (data.getDescription() != null) {
                updates.setDescription(data.getDescription());
            }
            if (data.getAmount() != null) {
                updates.setAmount(data.getAmount());
            }
            if (data.getPriceProposal() != null) {
                updates.setPriceProposal(data.getPriceProposal());
            }
            return proposalFileRepository.save(updates);
        }else {
            throw new EntityNotFoundException();
        }
    }

    //metado para deletar por id
    public void deleteById(Long id){
        Optional<ProposalFile> optionalDelete = proposalFileRepository.findById(id);
        if(optionalDelete.isPresent()){
            ProposalFile delete = optionalDelete.get();
            proposalFileRepository.deleteById(id);
        }
    }
}
