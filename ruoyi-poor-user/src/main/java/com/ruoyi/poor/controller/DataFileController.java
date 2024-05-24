package com.ruoyi.poor.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.poor.domain.DataFile;
import com.ruoyi.poor.dto.DataFileDto;
import com.ruoyi.poor.service.DataFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/poor/dataFile")
public class DataFileController extends BaseController {
    @Autowired
    private DataFileService dataFileService;


    @GetMapping("/list")
    public TableDataInfo list(DataFile dto) {
        startPage();
        List<DataFile> list = dataFileService.list();
        return getDataTable(list);
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        String filePath = FileUploadUtils.upload(RuoYiConfig.getUploadPath() + "/data_excel", file);

        /**
         * 读取sheet
         */
        ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build();
        List<String> sheetNames = excelReader.excelExecutor().sheetList().stream().map(sheet -> {
            return sheet.getSheetName();
        }).collect(Collectors.toList());

        return AjaxResult.success(new DataFileDto(filePath,file.getOriginalFilename(),sheetNames));
    }

    /**
     * 文件信息保存—+数据导入
     *
     * @param dataFile
     * @return
     * @throws IOException
     */
    @PostMapping("saveAndImport")
    public AjaxResult saveAndImport(@RequestBody  DataFile dataFile) {
        dataFileService.saveAndImport(dataFile);
        return AjaxResult.success(0);
    }

    /**
     * 文件下载
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @PostMapping("download/{id}")
    public void download(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        DataFile dataFile = dataFileService.getById(id);
        String path = dataFile.getFilePath().replaceFirst("/profile/upload", "");

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, dataFile.getFileName());
        FileUtils.writeBytes(RuoYiConfig.getUploadPath() + path, response.getOutputStream());
    }

    /**
     * 文件删除
     * @param id
     * @return
     */
    @DeleteMapping("delFileAndRecord/{id}")
    public AjaxResult delFileAndRecord(@PathVariable("id") Long id) {
        dataFileService.delFileAndRecord(id);
        return AjaxResult.success(0);
    }

    /**
     * 列出所有文件 供查询
     * @return
     */
    @GetMapping("/listAll")
    public List<DataFile> listAll() {
        QueryWrapper<DataFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","file_name");
        List<DataFile> list = dataFileService.list(queryWrapper);
        return list;
    }

}
