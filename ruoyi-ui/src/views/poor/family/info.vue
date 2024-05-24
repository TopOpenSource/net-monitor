<template>
  <div class="app-container" v-loading="false">
    <el-row :gutter="20">
      <template v-for="userInfo in familyList">
      <el-col :span="6" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>{{userInfo.name}}</span>
            <el-button type="success"  style="margin-left: 20px" v-if="userInfo.cardId==masterCardId">户主</el-button>
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
    </el-row>
  </div>
</template>

<script>

import {listFamily} from "@/api/poor/user";
export default {
  name: "Profile",
  dicts: ['yes_no', 'sys_user_sex', 'village','subsidy_type'],
  data() {
    return {
      familyId:undefined,
      masterCardId:undefined,//户主身份证号
      familyList:[],
      userInfo:{}
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
  }
};
</script>
