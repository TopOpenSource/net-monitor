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
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="是否残疾" prop="disability">
        <el-select v-model="queryParams.disability" clearable>
          <el-option
            v-for="dict in dict.type.yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="是否死亡" prop="live">
        <el-select v-model="queryParams.live" clearable>
          <el-option
            v-for="dict in dict.type.yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
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
        <el-button type="warning" plain icon="el-icon-upload2" size="mini" @click="handleImport">导入</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column label="姓名" width="80" align="center" prop="name">

      </el-table-column>
      <el-table-column label="手机号" width="200" align="center" prop="phone"/>
      <el-table-column label="身份证号" width="200" align="center" prop="cardId"/>
      <el-table-column label="出生日期" width="100" align="center" prop="birthday">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="村落" width="150" align="center" prop="village">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.village" :value="scope.row.village"/>
        </template>
      </el-table-column>
      <el-table-column label="是否残疾" width="80" align="center" prop="disability">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yes_no" :value="scope.row.disability"/>
        </template>
      </el-table-column>
      <el-table-column label="残疾证号" width="200" align="center" prop="disabilityId"/>
      <el-table-column label="是否死亡" width="80" align="center" prop="live">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yes_no" :value="scope.row.live"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleView(scope.row)">查看</el-button>
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


    <el-dialog :title="title" :visible.sync="open" width="500px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">

        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"/>
        </el-form-item>

        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择" clearable>
            <el-option
              v-for="item in dict.type.sys_user_sex"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="出生年月" prop="birthday">
          <el-date-picker
            v-model="form.birthday"
            type="date"
            value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="身份证号" prop="cardId">
          <el-input v-model="form.cardId"/>
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

        <el-form-item label="是否残疾" prop="disability">
          <el-select v-model="form.disability" placeholder="请选择" clearable>
            <el-option
              v-for="item in dict.type.yes_no"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="残疾证号" prop="disabilityId" v-if="form.disability=='1'">
          <el-input v-model="form.disabilityId"/>
        </el-form-item>

        <el-form-item label="是否死亡" prop="live">
          <el-select v-model="form.live" placeholder="请选择" clearable>
            <el-option
              v-for="item in dict.type.yes_no"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone"/>
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

import {list, saveOrUpdate, del, getInfo} from "@/api/poor/user";
import {parseTime} from "@/utils/ruoyi";
import ExcelFileUpload from "@/views/poor/user/ExcelFileUpload.vue";

export default {
  name: "PoorUser",
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
      // 岗位表格数据
      userList: [],
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
      importDialog:{
        title:'导入人员',
        open:false
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        sex: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        birthday: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        cardId: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        disability: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        disabilityId: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        live: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        village: [
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
      list(this.queryParams).then(response => {
        this.userList = response.rows
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
        path: "/poor/user/"+row.id,
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
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/post/export', {
        ...this.queryParams
      }, `post_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
