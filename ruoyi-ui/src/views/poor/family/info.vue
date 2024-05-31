<template>
  <div class="app-container" v-loading="false">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增家庭成员</el-button>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <template v-for="userInfo in familyList">
        <el-col :span="6" :xs="24">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>{{ userInfo.name }}</span>
              <dict-tag :options="dict.type.relation_type" :value="userInfo.relationType" style="float: left;margin-right: 10px"/>

              <el-button @click="handleDel(userInfo.cardId)" type="danger" icon="el-icon-delete" circle
                         style="float: right;margin-left: 5px" size="mini" plain></el-button>
              <el-button @click="handleView(userInfo)" type="success" icon="el-icon-search" circle
                         style="float: right;margin-left: 5px" size="mini" plain></el-button>
              <el-button @click="handleEdit(userInfo)" type="primary" icon="el-icon-edit" circle style="float: right"
                         size="mini" plain></el-button>
            </div>
            <div>
              <ul class="list-group list-group-striped">
                <li class="list-group-item">
                  <svg-icon icon-class="user"/>
                  性别
                  <div class="pull-right">
                    <dict-tag :options="dict.type.sys_user_sex" :value="userInfo.sex"/>
                  </div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="phone"/>
                  身份证号
                  <div class="pull-right">{{ userInfo.cardId }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="tree"/>
                  是否残疾
                  <div class="pull-right">
                    <dict-tag :options="dict.type.yes_no" :value="userInfo.disability"/>
                  </div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="phone"/>
                  残疾证号
                  <div class="pull-right">{{ userInfo.disabilityId }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="phone"/>
                  手机号码
                  <div class="pull-right">{{ userInfo.phone }}</div>
                </li>
                <li class="list-group-item">
                  <svg-icon icon-class="tree"/>
                  是否死亡
                  <div class="pull-right">
                    <dict-tag :options="dict.type.yes_no" :value="userInfo.live"/>
                  </div>
                </li>
              </ul>
            </div>
          </el-card>
        </el-col>
      </template>

      <UserEdit :form="form" :open-view="open" :title="title" @success="handleSaveSuccess"
                @cancelOpt="handleSaveCancel"></UserEdit>


      <el-dialog :title="memDialog.title" :visible.sync="memDialog.open" width="500px" :close-on-click-modal="false"  append-to-body>
        <el-form ref="memForm" :model="memForm" :rules="memRules" label-width="100px">
          <el-form-item label="家庭成员" prop="cardId">
            <el-select v-model="memForm.cardId" placeholder="请选择" filterable clearable>
              <el-option
                v-for="item in userList"
                :key="item.cardId"
                :label="item.name"
                :value="item.cardId">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="与户主关系" prop="relationType">
            <el-select v-model="memForm.relationType" placeholder="请选择" clearable>
              <el-option
                v-for="item in dict.type.relation_type"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>

    </el-row>
  </div>
</template>

<script>

import {listAll, listFamily, saveOrUpdate} from "@/api/poor/user";
import UserEdit from "@/views/poor/user/UserEdit.vue";
import {addMember, delMember} from "@/api/poor/family";


export default {
  name: "Profile",
  components: {UserEdit},
  dicts: ['yes_no', 'sys_user_sex', 'relation_type'],
  data() {
    return {
      familyId: undefined,
      masterCardId: undefined,//户主身份证号
      familyList: [],
      form: {},
      title: '修改信息',
      userList: [],
      open: false,
      memDialog: {
        open: false,
        title: '家庭成员'
      },
      //家庭成员编辑
      memForm: {
        cardId: null,
        relationType: null
      },
      memRules: {
        cardId: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        relationType: [
          {required: true, message: "不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {
    this.familyId = this.$route.params.familyId
    this.masterCardId = this.$route.params.masterCardId
  },
  watch: {},
  mounted() {
    this.initData()
  },
  methods: {
    initData() {
      this.getFamliy()
      listAll().then(res => {
        this.userList = res
      })
    },
    getFamliy(){
      listFamily(this.familyId).then(res => {
        this.familyList = res
      })
    },
    handleSaveCancel() {
      this.open = false
    },
    handleView(row) {
      this.$router.push({
        path: "/poor/user/" + row.id + "/" + row.cardId,
      });
    },
    handleEdit(userInfo) {
      this.form = userInfo
      this.open = true

    },
    handleSaveSuccess() {
      this.open = false
      this.initData()
      this.$modal.msgSuccess("修改成功");
    },
    resetMemForm() {
      this.memForm = {
        cardId: null,
        relationType: null
      }
      this.resetForm("memForm");
    },
    handleAdd() {
      this.resetMemForm();
      this.memDialog.open=true
    },
    handleDel(cardId) {
      let familyId=this.familyId
      this.$modal.confirm('是否确认删除？').then(function() {
        return delMember({familyId:familyId,cardId:cardId});
      }).then(() => {
        this.getFamliy();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});

      /*this.$modal.confirm('是否确认删除？').then(function () {
        return delMember({familyId:this.familyId,cardId:cardId});
      }).then(() => {
        this.getFamliy();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {

      });*/
    },
    submitForm() {
      this.$refs["memForm"].validate(valid => {
        if (valid) {
          this.memForm.familyId=this.familyId
          addMember(this.memForm).then(response => {
            this.$modal.msgSuccess("操作成功")
            this.memDialog.open=false
            this.getFamliy()
          })
        }
      });
    },
    cancel() {

    }
  }
};
</script>
