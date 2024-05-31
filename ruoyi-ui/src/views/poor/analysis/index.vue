<template>
  <div class="app-container" v-loading="loading">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="统计单位" prop="unit">
        <el-radio-group v-model="queryParams.unit" size="mini">
          <el-radio :label="0" border>月</el-radio>
          <el-radio :label="1" border>年</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="身份证号" prop="cardId">
        <el-input
          v-model="queryParams.cardId"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

<!--      <el-form-item label="补贴类型" prop="subsidyType">
        <el-select v-model="queryParams.subsidyType" clearable>
          <el-option
            v-for="dict in dict.type.subsidy_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>-->

      <el-form-item label="时间范围" prop="dataRange">
        <el-date-picker
          v-model="queryParams.dateRange"
          format="yyyy-MM"
          value-format="yyyy-MM"
          type="monthrange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport">导出</el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="familyNo" label="户编号" width="150"></el-table-column>
      <el-table-column prop="userName" label="姓名" width="100"></el-table-column>
      <el-table-column prop="cardId" label="身份证号" width="180"></el-table-column>
      <el-table-column prop="relationType" label="与户主关系" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.relation_type" :value="scope.row.relationType"/>
        </template>
      </el-table-column>

      <el-table-column  prop="subsidyDate" label="日期" width="150"></el-table-column>

      <template  v-for="item in dict.type.subsidy_type">
        <el-table-column  :label="item.label" width="60">
           <template slot-scope="scope">
             {{formatTypeValue(scope.row,item.value)}}
           </template>
        </el-table-column>
      </template>

    </el-table>
  </div>
</template>
<script>
import {selSubsidyAnalysis} from "../../../api/poor/subsidy";

export default {
  name: "AnalysisIndex",
  dicts: ['subsidy_type','relation_type'],
  data() {
    return {
      loading:false,
      showSearch:true,
      queryParams:{
        unit:0
      },
      tableData: []
    }
  },
  mounted(){
    this.getList()
  },
  methods:{
    getList(){
      this.loading=true
      selSubsidyAnalysis(this.queryParams).then(res=>{
        this.loading=false
        this.tableData=res
      })
    },
    handleQuery(){
      this.getList()
    },
    resetQuery(){
      this.queryParams={
        unit:0
      }
    },
    /**
     * 格式化类型的值 循环查找
     * @param row
     * @param type
     */
    formatTypeValue(row,type){
        let money=0
        for(let i in row.subsidyList){
          if(row.subsidyList[i].subsidyType==type){
            money=row.subsidyList[i].money
          }
        }
        return money
    },
    handleExport(){
      this.download('poor/subsidy/analysisExport',this.queryParams,"人员清单.xlsx")
    }
  }
}
</script>
<style scoped lang="scss">

</style>
