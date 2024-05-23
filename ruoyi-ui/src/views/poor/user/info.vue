<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="5" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>户籍信息</span>
          </div>
          <div>
            <div class="text-center">
              <userAvatar/>
            </div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <svg-icon icon-class="user"/>
                姓名
                <div class="pull-right">李宝库</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="user"/>
                性别
                <div class="pull-right">男</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="phone"/>
                身份证号
                <div class="pull-right">3714261988888888</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                是否残疾
                <div class="pull-right">是</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="phone"/>
                残疾证号
                <div class="pull-right">3714261988888888</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="phone"/>
                手机号码
                <div class="pull-right">1560000000</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                村落
                <div class="pull-right">象牙山</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="date"/>
                创建日期
                <div class="pull-right">2022-12-28</div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="19" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>补助发放统计-2024年</span>
            <el-date-picker style="float: right" type="year" v-model="year1" placeholder="选择年分"></el-date-picker>
          </div>

          <el-row>
            <el-col :span="8">
              <div ref="butie" style="height: 250px" />
            </el-col>

            <el-col :span="16">
              <div ref="yeartj" style="height: 250px" />
            </el-col>

          </el-row>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="残疾补贴" name="canji">
              <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="month" label="月份" width="180"></el-table-column>
                <el-table-column prop="money" label="金额（元）" width="180"></el-table-column>
                <el-table-column prop="remark" label="备注"></el-table-column>
              </el-table>
            </el-tab-pane>

            <el-tab-pane label="低保补贴" name="poor">
              <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="month" label="月份" width="180"></el-table-column>
                <el-table-column prop="money" label="金额（元）" width="180"></el-table-column>
                <el-table-column prop="remark" label="备注"></el-table-column>
              </el-table>
            </el-tab-pane>

            <el-tab-pane label="老年补贴" name="olde">
              <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="month" label="月份" width="180"></el-table-column>
                <el-table-column prop="money" label="金额（元）" width="180"></el-table-column>
                <el-table-column prop="remark" label="备注"></el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>

        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import userAvatar from "./userAvatar";
import * as echarts from "echarts";

export default {
  name: "Profile",
  components: {userAvatar},
  data() {
    return {
      year1: '',
      activeTab:'canji',
      tableData:[],
      butie:null,
      yeartj:null
    };
  },
  created() {
    this.initData()
  },
  mounted(){
    this.statistics()
    this.statistics2()
  },
  methods: {
    initData(){
      this.tableData=[
        {month:'2024-01',money:120,remark:'补贴xxx'},
        {month:'2024-02',money:120,remark:'补贴xxx'},
        {month:'2024-03',money:120,remark:'补贴xxx'},
        {month:'2024-04',money:120,remark:'补贴xxx'},
        {month:'2024-05',money:120,remark:'补贴xxx'},
      ]
    },
    statistics(){
      this.butie = echarts.init(this.$refs.butie);
      this.butie.setOption({
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
            data: [
              { value: 2000, name: '老年补贴' },
              { value: 3000, name: '伤残补贴' },
              { value: 500, name: '低保补贴' },
            ]
          }
        ]
      });
    },
    statistics2(){
      this.yeartj = echarts.init(this.$refs.yeartj);
      this.yeartj.setOption({
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
    }

  }
};
</script>
