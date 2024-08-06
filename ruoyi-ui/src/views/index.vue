<template>
  <div class="app-container home">

    <el-row>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>各类补贴金额</span>
          </div>
          <div ref="subsidyYear" style="height: 300px"/>
        </el-card>
    </el-row>

    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>各类补贴覆盖人数</span>
        </div>
        <div ref="userCountYear" style="height: 300px"/>
      </el-card>
    </el-row>

    <el-row>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
          </div>

        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {selSubsidyGroupYearType, selUserCountGroupYearType} from "../api/poor/analysis";
import * as echarts from "echarts";

export default {
  data() {
    return {
      subsidyYear: null,//每年补助金额
      userCountYear:null,//每年补助人数
    };
  },
  methods:{
    subsidy() {
      selSubsidyGroupYearType().then(res => {
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

    },

    userCount() {
      selUserCountGroupYearType().then(res => {
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
            data: subType.userCountDataList
          },)
        })


        this.userCountYear = echarts.init(this.$refs.userCountYear);
        this.userCountYear.setOption({
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
  },
  mounted(){
    this.subsidy()
    this.userCount()
  }
};
</script>

<style scoped lang="scss">

</style>

