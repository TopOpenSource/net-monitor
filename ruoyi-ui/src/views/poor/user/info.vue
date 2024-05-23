<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>户籍信息</span>
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
            <span>补助发放统计-2024年</span>
            <el-date-picker style="float: right" type="year" v-model=" currentYear" placeholder="选择年分"></el-date-picker>
          </div>

          <el-row>
            <el-col :span="12">
              <div ref="subsidyType" style="height: 250px" />
            </el-col>

            <el-col :span="12">
              <div ref="subsidyYear" style="height: 250px" />
            </el-col>

          </el-row>
          <el-tabs v-model="activeTab">
            <template v-for="item in dict.type.subsidy_type">
              <el-tab-pane :label="item.label" :name="item.value">
                 <subsidy-table :card-id="userInfo.cardId" :subsidy-type="item.value" :year=" currentYear"></subsidy-table>
              </el-tab-pane>
            </template>
          </el-tabs>

        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import subsidyTable from "./subsidyTable.vue";
import * as echarts from "echarts";
import {getInfo} from "@/api/poor/user";
import {selGroupType, selGroupYearType} from "../../../api/poor/subsidy";
export default {
  name: "Profile",
  dicts: ['yes_no', 'sys_user_sex', 'village','subsidy_type'],
  components: {subsidyTable},
  data() {
    return {
      userId:undefined,
      userInfo:{

      },
      currentYear: (new Date()).getFullYear(),
      activeTab:'0',
      tableData:[],
      subsidyType:null,
      subsidyYear:null
    };
  },
  created() {
    this.userId=this.$route.params.userId
    this.initData()
  },
  mounted(){

  },
  methods: {
    initData(){
      //获取用户信息
      getInfo(this.userId).then(res=>{
        this.userInfo=res
        this.statistics()
        this.statistics2()
      })
    },
    //补充subsidy 中文名称
    formartSubsidy(subsidyList){
      subsidyList.forEach(subsidy=>{
        subsidy.value=subsidy.money

        //补充subsidy 中文名称
        this.dict.type.subsidy_type.forEach(dict=>{
          if(dict.value==subsidy.subsidyType){
            subsidy.name=dict.label
          }
        })
      })
    },
    statistics(){
      selGroupType({cardId:this.userInfo.cardId,year:this.currentYear}).then(res=>{
        this.formartSubsidy(res)

        this.subsidyType = echarts.init(this.$refs.subsidyType);
        this.subsidyType.setOption({
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '5%',
            left: 'center'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: 40,
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: res
            }
          ]
        });
      })

    },
    statistics2(){
      selGroupYearType({cardId:this.userInfo.cardId}).then(res=>{

        console.log(res)

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
            data: ['2021', '2022', '2023', '2024']
          },
          series: [
            {
              name: '老年补贴',
              type: 'bar',
              stack: 'total',
              label: {
                show: true
              },
              emphasis: {
                focus: 'series'
              },
              data: [100, 100, 120, 119]
            },
            {
              name: '伤残补贴',
              type: 'bar',
              stack: 'total',
              label: {
                show: true
              },
              emphasis: {
                focus: 'series'
              },
              data: [100, 150, 0, 150]
            },
            {
              name: '低保补贴',
              type: 'bar',
              stack: 'total',
              label: {
                show: true
              },
              emphasis: {
                focus: 'series'
              },
              data: [1000, 800, 1000, 1000]
            },

          ]
        });
      })

    }

  }
};
</script>
