<template>
  <div class="app-container" >
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-upload2" size="mini" @click="handleAdd">导入</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-upload2" size="mini" @click="handleViewData">原始数据查看</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tableData">
      <el-table-column label="文件名称" align="center" prop="fileName"/>
      <el-table-column label="所属月份" align="center" prop="subsidyDate"/>
      <el-table-column label="补贴类型" align="center" prop="subsidyType" width="200">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.subsidy_type" :value="scope.row.subsidyType"/>
        </template>
      </el-table-column>
      <el-table-column label="数据量" align="center" prop="dataCount" width="100"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="150">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-download" @click="handleDownload(scope.row)">下载</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>


    <el-dialog :title="title" :visible.sync="open" width="500px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" v-loading="loading" :model="form" :rules="rules" label-width="100px" >
        <el-form-item prop="fileName">
          <ExcelFileUpload v-model="excelDataFile"></ExcelFileUpload>
        </el-form-item>

        <el-form-item label="文件名称">
          <el-input v-model="excelDataFile.fileName"/>
        </el-form-item>

        <el-form-item label="sheet页" prop="sheetName">
          <el-select v-model="form.sheetName" placeholder="请选择sheet" clearable>
            <el-option
              v-for="item in excelDataFile.sheetNames"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>

<!--        <el-form-item label="开始行数" prop="dataIndex">
          <el-input-number v-model="form.dataIndex" :min="1"></el-input-number>
        </el-form-item>-->

        <el-form-item label="补贴类型" prop="subsidyType">
          <el-select v-model="form.subsidyType" placeholder="请选择sheet" clearable>
            <el-option
              v-for="item in dict.type.subsidy_type"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="补贴月份" prop="subsidyDate">
          <el-date-picker
            v-model="form.subsidyDate"
            type="month"
            value-format="yyyy-MM"
            placeholder="选择月">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

import {delFileAndRecord, list, saveAndImport} from "@/api/poor/dataFile";

import {parseTime} from "@/utils/ruoyi";
import ExcelFileUpload from "./ExcelFileUpload";

export default {
  name: "PoorUser",
  dicts: ['yes_no', 'subsidy_type'],
  components: {ExcelFileUpload},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      tableData: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cardId: undefined,
        name: undefined,
      },
      // 表单参数
      form: {},
      excelDataFile: {
        filePath: undefined,
        fileName: undefined,
        sheetNames: []
      },
      // 表单校验
      rules: {

        subsidyType: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        sheetName: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        /*dataIndex: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],*/
        subsidyDate: [
          {required: true, message: "不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询岗位列表 */
    getList() {
      this.loading = true;
      list().then(response => {
        this.tableData = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        fileName: undefined,
        filePath: undefined,
        subsidyType: undefined,
        sheetName: undefined,
        dataIndex: undefined,
        subsidyDate: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "导入excel";
    },

    handleViewData(){
      this.$router.push({
        path: "/poor/subsidy/edit",
      });
    },
    //下载
    handleDownload(row){
      this.download('poor/dataFile/download/'+row.id,{},row.fileName)
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.loading=true
          this.form.fileName = this.excelDataFile.fileName
          this.form.filePath = this.excelDataFile.filePath

          saveAndImport(this.form).then(response => {
            this.$modal.msgSuccess("操作成功");
            this.open = false;
            this.loading=false
            this.getList();
          });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除文件？，此操作同时删除对应的数据，请谨慎操作！').then(function () {
        return delFileAndRecord(row.id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/post/export', {
        ...this.queryParams
      }, `post_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
