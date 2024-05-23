<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="subsidyDate" label="月份" width="180">
      <template slot-scope="scope">
        <span>{{ parseTime(scope.row.subsidyDate, '{y}-{m}') }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="money" label="金额（元）" width="180"></el-table-column>
    <el-table-column prop="remark" label="备注"></el-table-column>
  </el-table>
</template>
<script>

import {list} from  "@/api/poor/subsidy";
export default {
  name: "SubsidyTable",
  props: {
    cardId: null,
    subsidyType: null,
    year:null
  },
  data() {
    return {
      tableData:[

      ]
    };
  },
  created() {

  },
  mounted(){
     this.initData()
  },
  methods: {
    initData(){
      list({cardId:this.cardId,subsidyType:this.subsidyType,year:this.year}).then(res=>{
        this.tableData=res
      })
    },
  }
};
</script>
<style scoped lang="scss">

</style>
