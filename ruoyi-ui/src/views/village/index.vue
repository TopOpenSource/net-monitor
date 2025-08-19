<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column label="村名" width="150" align="center" prop="name">
        <template slot-scope="scope">
          <span @click="handleView(scope.row)" style="cursor: pointer;color: #409eff">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="人口数量" width="150" align="center" prop="villagerCount"/>
      <el-table-column label="支部书记" width="200" align="center" prop="admin"/>
      <el-table-column label="耕地面积" width="200" align="center" prop="landArea"/>
      <el-table-column label="特色产业" width="200" align="center" prop="industryCount"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
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

import {list, saveOrUpdate, del, getInfo} from "@/api/poor/user";
import {parseTime} from "@/utils/ruoyi";
export default {
  name: "PoorUser",
  components: {},
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
      // 岗位表格数据
      dataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      importDialog:{
        title:'导入人员',
        open:false
      },
      // 表单参数
      form: {},

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询岗位列表 */
    getList() {
      this.loading = true;
      // list(this.queryParams).then(response => {
      //   this.dataList = response.rows
      //   this.total = response.total
      //   this.loading = false;
      // })

      this.loading = false;

      this.dataList = [{
           name:'村庄1',
        villagerCount:5,
        admin:'张三',
        landArea:5,
        industryCount:5
      },
        {
          name:'村庄1',
          villagerCount:5,
          admin:'张三',
          landArea:5,
          industryCount:5
        },
        {
          name:'村庄2',
          villagerCount:5,
          admin:'张三',
          landArea:5,
          industryCount:5
        },
        {
          name:'村庄3',
          villagerCount:5,
          admin:'张三',
          landArea:5,
          industryCount:5
        },
        {
          name:'村庄4',
          villagerCount:5,
          admin:'张三',
          landArea:5,
          industryCount:5
        },
      ]
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
        path: "/village/village/"+row.id,
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
