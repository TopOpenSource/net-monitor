<template>
  <div class="app-container" v-loading="false">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>户籍信息</span>
            <el-button @click="handleEdit(userInfo)" type="primary" icon="el-icon-edit" circle style="float: right"
                       size="mini"></el-button>
          </div>
          <div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <svg-icon icon-class="user"/>
                姓名
                <div class="pull-right">{{ userInfo.name }}</div>
              </li>
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
                村落
                <div class="pull-right">
                  <dict-tag :options="dict.type.village" :value="userInfo.village"/>
                </div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                是否死亡
                <div class="pull-right">
                  <dict-tag :options="dict.type.yes_no" :value="userInfo.live"/>
                </div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="date"/>
                创建日期
                <div class="pull-right">{{ parseTime(userInfo.createTime, '{y}-{m}-{d}') }}</div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>补助发放统计</span>
          </div>
          <el-row>
            <div ref="subsidyYear" style="height: 250px"/>
          </el-row>

        </el-card>

        <el-card>
          <div slot="header" class="clearfix">
            <span>补助发放明细</span>
          </div>
          <el-row>
            <subsidy-user-table :card-id="cardId"></subsidy-user-table>
          </el-row>

        </el-card>
      </el-col>
    </el-row>

    <UserEdit :form="userInfo" :openView="open" :title="title" @success="handleSaveSuccess"
              @cancelOpt="handleSaveCancel"></UserEdit>
  </div>
</template>

<script>
import subsidyUserTable from "@/views/poor/user/subsidyUserTable.vue";
import * as echarts from "echarts";
import {getInfo} from "@/api/poor/user";
import {selCountBySubsidyType, selGroupYearType} from "../../../api/poor/subsidy";
import UserEdit from "@/views/poor/user/UserEdit.vue";

export default {
  name: "Profile",
  dicts: ['yes_no', 'sys_user_sex', 'village', 'subsidy_type'],
  components: {UserEdit, subsidyUserTable},
  data() {
    return {
      title: '信息修改',
      open: false,
      userId: undefined,
      cardId: undefined,
      userInfo: {},
      currentYear: new Date(),
      activeTab: '0',
      tableData: [],
      subsidyType: null,//当年类型统计
      subsidyYear: null, //个人按年统计
      subsidyTypeCount: null, //补助类型数量
    };
  },
  created() {
    this.userId = this.$route.params.userId
    this.cardId = this.$route.params.cardId
    this.initData()
  },
  watch: {
    currentYear(value, oldValue) {
      if (value != oldValue) {
        this.statistics()
      }
    }
  },
  mounted() {

  },
  methods: {
    handleEdit(userInfo) {
      this.open = true
    },
    handleSaveSuccess() {
      this.open = false
      this.$modal.msgSuccess("修改成功");
      this.initData()
    },
    handleSaveCancel() {
      this.open = false
    },
    initData() {
      //获取用户信息
      getInfo(this.userId).then(res => {
        this.userInfo = res
        //this.statistics()
        this.statistics2()
      })

      this.subsidyTypeCount = {}
      selCountBySubsidyType(this.cardId).then(res => {
        res.forEach(data => {
          this.subsidyTypeCount[data.subsidyType] = data.count
        })
      })
    },
    //补充subsidy 中文名称
    formartSubsidy(subsidyList) {
      subsidyList.forEach(subsidy => {
        subsidy.value = subsidy.money

        //补充subsidy 中文名称
        this.dict.type.subsidy_type.forEach(dict => {
          if (dict.value == subsidy.subsidyType) {
            subsidy.name = dict.label
          }
        })
      })
    },

    statistics2() {
      selGroupYearType({cardId: this.cardId}).then(res => {
        if (res == "") {
          return
        }

        let series = []
        res.subsidyDtos.forEach(subType => {
          series.push({
            name: subType.subsidyTypeCN,
            type: 'bar',
            stack: 'total',
            label: {
              show: true
            },
            emphasis: {
              focus: 'series'
            },
            data: subType.moneyDataList
          },)
        })


        this.subsidyYear = echarts.init(this.$refs.subsidyYear);
        this.subsidyYear.setOption({
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow' // 'shadow' as default; can also be 'line' or 'shadow'
            }
          },
          legend: {},
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'value'
          },
          yAxis: {
            type: 'category',
            data: res.years
          },
          series: series
        });
      })

    }

  }
};
</script>
