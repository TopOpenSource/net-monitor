<template>
  <div class="app-container home">
    <el-row>
       <el-col :span="8">
         <el-card class="box-card">
           <div slot="header" class="clearfix">
             <span>各村贫困人数</span>
           </div>
           <div ref="villageMap" style="height: 680px"/>
         </el-card>
       </el-col>

      <el-col :span="16">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>各类补贴金额</span>
          </div>
          <div ref="subsidyYear" style="height: 300px"/>
        </el-card>

        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>各类补贴覆盖人数</span>
          </div>
          <div ref="userCountYear" style="height: 300px"/>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {selSubsidyGroupYearType, selUserCountGroupYearType,selUserCountGroupByVillage} from "../api/poor/analysis";
import * as echarts from "echarts";
import yaozhan from '@/assets/geojson/yaozhan.json'
export default {
  data() {
    return {
      subsidyYear: null,//每年补助金额
      userCountYear:null,//每年补助人数
      villageMap:null,//村落地图
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

    },
    villageCount(){
      selUserCountGroupByVillage().then(res=>{
        let data=[]
        res.forEach(x=>{
          data.push({name: x.village,value: x.userCount})
        })

        this.villageMap = echarts.init(this.$refs.villageMap);
        echarts.registerMap('yaozhan', yaozhan);

        this.villageMap.setOption(
          {
            visualMap: {
              min: 0,
              max: 20,
              text: ['20', '0'],
              inRange: {
                color: ['yellow','red']
              }
            },
            series: [
              {
                type: 'map',
                map: 'yaozhan',
                label: {
                  show: true
                },
                data: data,
              }
            ]
          }
        )

      })


    }
  },
  mounted(){
    this.subsidy()
    this.userCount()
    this.villageCount()
  }
};
</script>

<style scoped lang="scss">

</style>

