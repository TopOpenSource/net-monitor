<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="身份证号" prop="cardId">
        <el-input
          v-model="queryParams.cardId"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="补贴类型" prop="live">
        <el-select v-model="queryParams.subsidyType" clearable>
          <el-option
            v-for="dict in dict.type.subsidy_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="文件来源" prop="live">
        <el-select v-model="queryParams.subsidyType" clearable>
          <el-option
            v-for="dict in fileList"
            :key="dict.id"
            :label="dict.fileName"
            :value="dict.id"
          />
        </el-select>
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
        <el-button type="warning" plain icon="el-icon-upload2" size="mini" @click="">批量删除</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="姓名" width="100" align="center" prop="userName">
      </el-table-column>
      <el-table-column label="身份证号" width="200" align="center" prop="cardId"/>
      <el-table-column label="补贴类型" align="center" prop="subsidyType" width="200">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.subsidy_type" :value="scope.row.subsidyType"/>
        </template>
      </el-table-column>
      <el-table-column label="文件来源"  align="center" prop="fileName">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>

import {selSubsidyList} from "@/api/poor/subsidy";
import {listAll} from "@/api/poor/dataFile";
export default {
  name: "PoorUser",
  dicts: ['subsidy_type'],
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
      // 岗位表格数据
      dataList: [],
      fileList:[],
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

    };
  },
  created() {
    this.getList()
    this.initData()
  },
  methods: {
    initData(){
      listAll().then(res=>{
        this.fileList=res;
      })
    },
    /** 查询岗位列表 */
    getList() {
      this.loading = true;
      selSubsidyList(this.queryParams).then(response => {
        this.dataList = response.rows
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
        familyId: null,
        cardId: null,
        name: null,
        sex: null,
        birthday: null,
        disability: null,
        disabilityId: null,
        live: null,
        phone: null,
        address: null
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

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加岗位";
    },
    handleView(row) {
      this.$router.push({
        path: "/poor/user/"+row.id+"/"+row.cardId,
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
    handleSaveCancel(){
      this.open=false
      this.reset()
    },
    /**保存成功**/
    handleSaveSuccess(){
      this.open=false
      this.getList()
      this.$modal.msgSuccess("修改成功");
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
