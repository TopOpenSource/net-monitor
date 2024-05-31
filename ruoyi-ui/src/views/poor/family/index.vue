<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="身份证号" prop="cardId">
        <el-input v-model="queryParams.masterCardId" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="queryParams.masterName" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-upload2" size="mini" @click="handleImport">导入</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="familyList" @selection-change="handleSelectionChange">
      <el-table-column label="户编号" width="150" align="center" prop="familyNo"/>
      <el-table-column label="户主" width="200" align="center" prop="name">
        <template slot-scope="scope">
          <span @click="handleView(scope.row)" style="cursor: pointer;color: #409eff">{{ scope.row.master.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号" width="200" align="center" prop="name">
        <template slot-scope="scope">
          {{ scope.row.master.phone }}
        </template>
      </el-table-column>
      <el-table-column label="人数" width="150" align="center" prop="familyCount"/>
      <el-table-column label="村落" width="150" align="center" prop="village">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.village" :value="scope.row.village"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          <el-button size="mini" type="text" icon="el-icon-search" @click="handleView(scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <el-dialog :title="title" :visible.sync="open" width="800px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">

        <el-form-item label="户编号" prop="familyNo">
          <el-input v-model="form.familyNo"/>
        </el-form-item>

        <el-form-item label="户主" prop="masterCardId">
          <el-select v-model="form.masterCardId" placeholder="请选择" filterable  clearable>
            <el-option
              v-for="item in userList"
              :key="item.cardId"
              :label="item.name"
              :value="item.cardId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="村落" prop="village">
          <el-select v-model="form.village" placeholder="请选择" clearable>
            <el-option
              v-for="item in dict.type.village"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="importDialog.title" :visible.sync="importDialog.open" width="500px" :close-on-click-modal="false" append-to-body>
      <ExcelFileUpload @success="importSuccess"></ExcelFileUpload>
    </el-dialog>
  </div>
</template>

<script>

import {list, saveOrUpdate, del, getInfo} from "@/api/poor/family";
import {listAll} from "@/api/poor/user";
import ExcelFileUpload from "@/views/poor/family/ExcelFileUpload.vue";

export default {
  name: "PoorFamily",
  components: {ExcelFileUpload},
  dicts: ['yes_no', 'sys_user_sex', 'village'],
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
      //表格数据
      familyList: [],
      //用户候选项
      userList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        masterCardId: undefined,
        masterName: undefined,
      },
      importDialog:{
        title:'导入',
        open:false
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        masterCardId: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        familyNo: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        village: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        address: [
          {required: true, message: "不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {
    this.getList()
  },
  mounted() {
    this.initData()
  },
  methods: {
    initData(){
      listAll().then(res=>{
        this.userList=res
      })
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      list(this.queryParams).then(response => {
        this.familyList = response.rows
        this.total = response.total
        this.loading = false;
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
        id: null,
        masterCardId:null,
        familyNo:null,
        address:null,
        village:null,
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.postId)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /**导入**/
    handleImport(){
      this.importDialog.open=true;
    },
    /**导入成功刷新**/
    importSuccess(count){
      this.importDialog.open=false
      this.$modal.msgSuccess("成功导入:"+count+"条！");
      this.getList()
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加岗位";
    },
    handleView(row) {
      this.$router.push({
        path: "/poor/family/"+row.id+"/"+row.masterCardId,
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      getInfo(row.id).then(response => {
        this.form = response;
        this.open = true;
        this.title = "修改贫困人员";
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          saveOrUpdate(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          })
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除？').then(function () {
        return del(row.id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
  }
};
</script>
