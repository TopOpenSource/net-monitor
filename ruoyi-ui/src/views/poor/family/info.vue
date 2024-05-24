<template>
  <div class="app-container" v-loading="false">
    <el-row :gutter="20">
      <template v-for="userInfo in familyList">
      <el-col :span="6" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>{{userInfo.name}}</span>
            <el-tag v-if="userInfo.cardId==masterCardId"  style="margin-left: 20px;" type="success">户主</el-tag>

            <el-button  @click="handleView(userInfo)" type="success" icon="el-icon-search" circle style="float: right;margin-left: 5px" size="mini"></el-button>
            <el-button  @click="handleEdit(userInfo)" type="primary" icon="el-icon-edit" circle style="float: right" size="mini"></el-button>
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

      <UserEdit :form="form" :open-view="open" :title="title" @success="handleSaveSuccess" @cancelOpt="handleSaveCancel"></UserEdit>
    </el-row>
  </div>
</template>

<script>

import {listFamily} from "@/api/poor/user";
import UserEdit from "@/views/poor/user/UserEdit.vue";
export default {
  name: "Profile",
  components: {UserEdit},
  dicts: ['yes_no', 'sys_user_sex','subsidy_type'],
  data() {
    return {
      familyId:undefined,
      masterCardId:undefined,//户主身份证号
      familyList:[],
      form:{},
      title:'修改信息',
      open:false,
    };
  },
  created() {
    this.familyId=this.$route.params.familyId
    this.masterCardId=this.$route.params.masterCardId
  },
  watch: {

  },
  mounted(){
    this.initData()
  },
  methods: {
    initData(){
      listFamily(this.familyId).then(res=>{
        this.familyList=res
      })
    },
    handleSaveCancel(){
      this.open=false
    },
    handleView(row) {
      this.$router.push({
        path: "/poor/user/"+row.id+"/"+row.cardId,
      });
    },
    handleEdit(userInfo){
       this.form=userInfo
       this.open=true

    },
    handleSaveSuccess(){
      this.open=false
      this.initData()
      this.$modal.msgSuccess("修改成功");
    },
  }
};
</script>
